package es.upm.oeg.tools.mappings;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.LiteralRequiredException;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.impl.LiteralImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

/**
 * Copyright 2014-2018 Ontology Engineering Group, Universidad Politécnica de Madrid, Spain
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Nandana Mihindukulasooriya
 * @since 1.0.0
 */
public class SparqlUtils {

    private static final Logger logger = LoggerFactory.getLogger(SparqlUtils.class);


    public static boolean ask(String queryString, String serviceEndpoint) {

        logger.debug("Executing query: {}", queryString);

        Query query = QueryFactory.create(queryString);

        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(serviceEndpoint, query)) {

            return qexec.execAsk();
        } catch (Exception e) {
            System.out.println("Query :" + queryString);
            throw e;
        }

    }
    public static List<Map<String, RDFNode>> executeQueryForListWithoutJena(String queryString, String serviceEndpoint, Set<String> vars) {
        logger.debug("Executing query without jena {}", queryString);
        queryString = URLEncoder.encode(queryString);
        StringBuilder result = new StringBuilder();
        List<Map<String, RDFNode>> resultsList = new ArrayList<>();

        try {
            URL url = new URL(serviceEndpoint+"?query="+queryString);
            logger.info("La URL es: {}", url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/csv");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            List<String> header = new ArrayList<>();
            while ((line = rd.readLine()) != null) {
                result.append(line);
                // Cabeceras del CSV -> nombre del nodo (clave del Map). Valor: es cada fila
                // TODO
                if (header.isEmpty()) {
                    String[] headerArray = line.split(",");
                    for (String head : headerArray) {
                        head = head.replaceAll("^\"|\"$", "");
                        header.add(head);
                    }
                } else {
                    String[] resultLineArray = line.split(",");
                    logger.info("ResultLineArray {}", resultLineArray);
                    Map<String, RDFNode> resultMap = new HashMap<>();
                    for (int i = 0; i < header.size(); i++) {
                        RDFNode node = null;
                        String field = resultLineArray[i];
                        field = field.replaceAll("^\"|\"$", "");
                        if (field.startsWith("http://") || field.startsWith("https://")) {
                            node = new ResourceImpl(field);
                        } else if (StringUtils.isNumeric(field)) {
                            node = new LiteralImpl(NodeFactory.createVariable(field), null);
//                            node = NodeFactory.createLiteralByValue(Integer.valueOf(field), )
                        } else {
                            node = new LiteralImpl(NodeFactory.createVariable(field), null);
                        }
                        logger.info("El valor {} se inserta {} en {}", field, node, header.get(i));
                        resultMap.put(header.get(i), node);
                    }
                    resultsList.add(resultMap);
                }
            }
            rd.close();
            String resultado = result.toString();
            logger.info("Resultado: "+resultado);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultsList;
    }
    public static List<Map<String, RDFNode>> executeQueryForListWithoutJena2(String queryString, String serviceEndpoint, Set<String> vars) {
        logger.debug("Executing query without jena {}", queryString);
        queryString = URLEncoder.encode(queryString);

        List<Map<String, RDFNode>> resultsList = new ArrayList<>();

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet sparqlget = new HttpGet(serviceEndpoint+"?query="+queryString);

        try {
            logger.info("Execute {}", sparqlget.getRequestLine());
            CloseableHttpResponse responseBody = httpclient.execute(sparqlget);
            logger.info(responseBody.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultsList;
    }

    public static List<Map<String, RDFNode>> executeQueryForList(String queryString, String serviceEndpoint, Set<String> vars) {

        logger.debug("Executing query: {}", queryString);

        List<Map<String, RDFNode>> resultsList = new ArrayList<>();
        try {
            Query query = QueryFactory.create(queryString);
            QueryEngineHTTP qexec = (QueryEngineHTTP) QueryExecutionFactory.sparqlService(serviceEndpoint, query);
            qexec.setTimeout(6000000l);
//            qexec.setSelectContentType("text/csv;charset=UTF-8");
//            qexec.setSelectContentType("application/sparql-results+xml");
            ResultSet results = qexec.execSelect();

            // collect all the values
            for (; results.hasNext(); ) {
                QuerySolution soln = results.nextSolution();
                Map<String, RDFNode> resultsMap = new HashMap<String, RDFNode>();

                for (String var : vars) {
                    if (soln.contains(var)) {
                        String res = soln.get(var).toString();
                        try {
                            logger.info("soln.get(var): "+res+", - "+soln.get(var).asLiteral().toString());
                        } catch (LiteralRequiredException lre) {
                            logger.info("soln.get(var): "+res+", - "+soln.get(var).asResource().toString());
                        }
                        if (res.startsWith("http://") || res.startsWith("https://")) {
                            resultsMap.put(var, new ResourceImpl(soln.get(var).toString()));
                        } else {
                            resultsMap.put(var, soln.get(var));
                        }

                    }
                }

                resultsList.add(resultsMap);
            }

            return resultsList;
        } catch (Exception exc) {
            logger.warn("Error is: "+exc.getMessage());
            logger.error("Found this error: ", exc);
            exc.printStackTrace();
            return null;
        }

    }


    /**
     * A utility method to extract results when there is only single result variable with multiple values
     * @param queryString
     * @param serviceEndpoint
     * @param var
     * @return
     */
    public static List<RDFNode> executeQueryForList(String queryString, String serviceEndpoint, String var) {

        logger.debug("Executing query: {}", queryString);

        List<RDFNode> resultsList = new ArrayList<>();

        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(serviceEndpoint, query)) {
            {
                ResultSet results = qexec.execSelect();

                // collect all the values
                for (; results.hasNext(); ) {
                    QuerySolution soln = results.nextSolution();
                    if (soln.contains(var)) {
                        resultsList.add(soln.get(var));
                    }
                }

                return resultsList;
            }
        }  catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /***
     * A utility method to execute SPARQL query when only single valued
     * @param queryString The SPARQL query string
     * @param serviceEndpoint The endpoint to run the query against
     * @param vars the variables from the result
     * @return Map of the the variable and the value from the solution
     */
    public static Map<String, RDFNode> executeQueryForMap(String queryString, String serviceEndpoint, Set<String> vars) {

        logger.debug("Executing query: {}", queryString);

        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(serviceEndpoint, query)) {
            {
                ResultSet results = qexec.execSelect();

                // We only return the first solutions, may be a map of map
                for (; results.hasNext(); ) {
                    QuerySolution soln = results.nextSolution();
                    Map<String, RDFNode> resultsMap = new HashMap<String, RDFNode>();

                    for (String var : vars) {
                        if (soln.contains(var)) {
                            resultsMap.put(var, soln.get(var));
                        }
                    }

                    return resultsMap;
                }
            }

            // No solutions found
            return Collections.EMPTY_MAP;
        }
    }
}
