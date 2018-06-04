package es.upm.oeg.tools.mappings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

public class AddTBProps {
    static BufferedWriter writer;

    private static final DecimalFormatSymbols symbolsDE_DE = DecimalFormatSymbols.getInstance(Locale.US);
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###,###,##0.0000", symbolsDE_DE);

    public static void main(String[] args) {
        Path iPath = FileSystems.getDefault().getPath("/home/vfrico/anotaciones.csv");
        Path oPath = FileSystems.getDefault().getPath("/home/vfrico/en-es-lit.csv");

        try {
            writer = Files.newBufferedWriter(oPath, Charset.defaultCharset(),
                    StandardOpenOption.CREATE);

            BufferedReader br = Files.newBufferedReader(iPath);

            br.lines().forEach((line) -> {

                String[] campos = line.split(",");
                String templateA = campos[0];
                String templateB = campos[2];

                String attributeA = campos[1];
                String attributeB = campos[3];

                String propA = campos[4];
                String propB = campos[5];

                String sM1 = campos[19];
                String sM2 = campos[20];
                String sM3 = campos[21];
                String sM4a = campos[22];
                String sM4b = campos[23];
                String sM5a = campos[24];
                String sM5b = campos[25];

                String anotacion = campos[6];
                long m1 = 0;
                long m2 = 0;
                long m3 = 0;
                long m4a = 0;
                long m5a = 0;
                long m4b = 0;
                long m5b = 0;
                try {
                     m1 = Long.parseLong(sM1);
                     m2 = Long.parseLong(sM2);
                     m3 = Long.parseLong(sM3);
                     m4a = Long.parseLong(sM4a);
                     m4b = Long.parseLong(sM4b);
                     m5a = Long.parseLong(sM5a);
                     m5b = Long.parseLong(sM5b);
                } catch (NumberFormatException nfe) {
                    System.out.println("Error parsing "+sM1);
                }
                PropPair entry = new PropPair(templateA, templateB, attributeA, attributeB,
                        propA, propB, m1);
                entry.setAnotacion(anotacion);
                entry.setM2(m2);
                entry.setM3
            //                System.out.println(Arrays.toString(campos));
                System.out.println(entry);

            });



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCSVTitles() {
        try {
            writer.write("Template A"
                    + ", " + "Attribute A"
                    + ", " + "Template B"
                    + ", " + "Attribute B"
                    + ", " + "Property A"
                    + ", " + "Property B"
                    + ", " + "Anotacion"
                    + ", " + "Class A"
                    + ", " + "Class B"
                    + ", " + "Domain Property A"
                    + ", " + "Domain Property B"
                    + ", " + "Range Property A"
                    + ", " + "Range Property B"
                    + ", " + "M1/M4"
                    + ", " + "M2/M4"
                    + ", " + "M3a/M5a"
                    + ", " + "M3b/M5b"
                    + ", " + "M4"
                    + ", " + "M1"
                    + ", " + "M2"
                    + ", " + "M3a"
                    + ", " + "M3b"
                    + ", " + "M5a"
                    + ", " + "M5b"
                    + ", " + "TB1"
                    + ", " + "TB2"
                    + ", " + "TB3"
                    + ", " + "TB4"
                    + ", " + "TB5"
                    + ", " + "TB6"
                    + ", " + "TB7"
                    + ", " + "TB8"
                    + ", " + "TB9"
                    + ", " + "TB10"
                    + ", " + "TB11"
            );
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void escribeLinea(PropPair propPair) {
        // Configure TB properties
        InconsistentMappings.fillTBProperties(propPair);


            try {
                writer.write(propPair.getTemplateA()
                        + ", " + propPair.getAttributeA()
                        + ", " + propPair.getTemplateB()
                        + ", " + propPair.getAttributeB()
                        + ", " + propPair.getAnotacion()//Anotacion
                        + ", " + InconsistentMappings.getPrefixedProperty(propPair.getPropA())
                        + ", " + InconsistentMappings.getPrefixedProperty(propPair.getPropB())
                        + ", " + InconsistentMappings.getClass(InconsistentMappings.classGraph1, InconsistentMappings.infoboxPrefix1 + propPair.getTemplateA())
                        + ", " + InconsistentMappings.getClass(InconsistentMappings.classGraph2, InconsistentMappings.infoboxPrefix2 + propPair.getTemplateB())
                        + ", " + InconsistentMappings.getPrefixedProperty(DBO.getDomain(propPair.getPropA()))
                        + ", " + InconsistentMappings.getPrefixedProperty(DBO.getDomain(propPair.getPropB()))
                        + ", " + InconsistentMappings.getPrefixedProperty(DBO.getRange(propPair.getPropA()))
                        + ", " + InconsistentMappings.getPrefixedProperty(DBO.getRange(propPair.getPropB()))
                        + ", " + DECIMAL_FORMAT.format(((double) propPair.getM1()) / propPair.getM4())
                        + ", " + DECIMAL_FORMAT.format(((double) propPair.getM2()) / propPair.getM4())
                        + ", " + DECIMAL_FORMAT.format(((double) propPair.getM3a()) / propPair.getM5a())
                        + ", " + DECIMAL_FORMAT.format(((double) propPair.getM3b()) / propPair.getM5b())
                        + ", " + propPair.getM4()
                        + ", " + propPair.getM1()
                        + ", " + propPair.getM2()
                        + ", " + propPair.getM3a()
                        + ", " + propPair.getM3b()
                        + ", " + propPair.getM5a()
                        + ", " + propPair.getM5b()
                        + ", " + propPair.getTb1()
                        + ", " + propPair.getTb2()
                        + ", " + propPair.getTb3()
                        + ", " + propPair.getTb4()
                        + ", " + propPair.getTb5()
                        + ", " + propPair.getTb6()
                        + ", " + propPair.getTb7()
                        + ", " + propPair.getTb8()
                        + ", " + propPair.getTb9()
                        + ", " + propPair.getTb10()
                        + ", " + propPair.getTb11()
                );
                writer.newLine();
                writer.flush();
            } catch (Exception e) {
                System.out.println("Error serializing the results! " + e.getMessage()+ e);
            }

    }
}
