package es.upm.oeg.tools.mappings;

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
public class PropPair {

    private String templateA;
    private String templateB;
    private String attributeA;
    private String attributeB;
    private String propA;
    private String propB;

    private String anotacion;

    private long m1;
    private long m2;
    private long m3;
    private long m4a;
    private long m4b;
    private long m5a;
    private long m5b;

    private int tb1;
    private int tb2;
    private int tb3;
    private int tb4;
    private int tb5;
    private int tb6;
    private int tb7;
    private int tb8;
    private int tb9;
    private int tb10;
    private int tb11;

    public PropPair(String propA, String propB) {
        this.propA = propA;
        this.propB = propB;
    }

    public PropPair(String propA, String propB, long m1) {
        this.propA = propA;
        this.propB = propB;
        this.m1 = m1;
    }

    public PropPair(String templateA, String templateB, String attributeA, String attributeB,
                    String propA, String propB, long m1) {
        this.templateA = templateA;
        this.templateB = templateB;
        this.attributeA = attributeA;
        this.attributeB = attributeB;
        this.propA = propA;
        this.propB = propB;
        this.m1 = m1;
    }

    public String getPropA() {
        return propA;
    }

    public void setPropA(String propA) {
        this.propA = propA;
    }

    public String getPropB() {
        return propB;
    }

    public void setPropB(String propB) {
        this.propB = propB;
    }

    public String getTemplateA() {
        return templateA;
    }

    public void setTemplateA(String templateA) {
        this.templateA = templateA;
    }

    public String getTemplateB() {
        return templateB;
    }

    public void setTemplateB(String templateB) {
        this.templateB = templateB;
    }

    public String getAttributeA() {
        return attributeA;
    }

    public void setAttributeA(String attributeA) {
        this.attributeA = attributeA;
    }

    public String getAttributeB() {
        return attributeB;
    }

    public void setAttributeB(String attributeB) {
        this.attributeB = attributeB;
    }

    public long getM1() {
        return m1;
    }

    public void setM1(long m1) {
        this.m1 = m1;
    }

    public long getM2() {
        return m2;
    }

    public long getM3() {
        return m3;
    }

    public void setM3(long m3) {
        this.m3 = m3;
    }

    public long getM4a() {
        return m4a;
    }

    public void setM4a(long m4a) {
        this.m4a = m4a;
    }

    public void setM2(long m2) {
        this.m2 = m2;
    }

    public long getM4b() {
        return m4b;
    }

    public void setM4b(long m4b) {
        this.m4b = m4b;
    }

    public long getM5a() {
        return m5a;
    }

    public void setM5a(long m5a) {
        this.m5a = m5a;
    }

    public long getM5b() {
        return m5b;
    }

    public void setM5b(long m5b) {
        this.m5b = m5b;
    }

    public int getTb1() {
        return tb1;
    }

    public void setTb1(int tb1) {
        this.tb1 = tb1;
    }

    public void setTb1(boolean tb1) {
        if (tb1)
            setTb1(1);
        else
            setTb1(0);
    }

    public int getTb2() {
        return tb2;
    }

    public void setTb2(int tb2) {
        this.tb2 = tb2;
    }

    public void setTb2(boolean tb2) {
        if (tb2)
            setTb2(1);
        else
            setTb2(0);
    }

    public int getTb3() {
        return tb3;
    }

    public void setTb3(int tb3) {
        this.tb3 = tb3;
    }

    public void setTb3(boolean tb3) {
        if (tb3)
            setTb3(1);
        else
            setTb3(0);
    }

    public int getTb4() {
        return tb4;
    }

    public void setTb4(int tb4) {
        this.tb4 = tb4;
    }

    public void setTb4(boolean tb4) {
        if (tb4)
            setTb4(1);
        else
            setTb4(0);
    }

    public int getTb5() {
        return tb5;
    }

    public void setTb5(int tb5) {
        this.tb5 = tb5;
    }

    public void setTb5(boolean tb5) {
        if (tb5)
            setTb5(1);
        else
            setTb5(0);
    }

    public int getTb6() {
        return tb6;
    }

    public void setTb6(int tb6) {
        this.tb6 = tb6;
    }

    public void setTb6(boolean tb6) {
        if (tb6)
            setTb6(1);
        else
            setTb6(0);
    }

    public int getTb7() {
        return tb7;
    }

    public void setTb7(int tb7) {
        this.tb7 = tb7;
    }

    public void setTb7(boolean tb7) {
        if (tb7)
            setTb7(1);
        else
            setTb7(0);
    }

    public int getTb8() {
        return tb8;
    }

    public void setTb8(int tb8) {
        this.tb8 = tb8;
    }

    public void setTb8(boolean tb8) {
        if (tb8)
            setTb8(1);
        else
            setTb8(0);
    }

    public int getTb9() {
        return tb9;
    }

    public void setTb9(int tb9) {
        this.tb9 = tb9;
    }

    public void setTb9(boolean tb9) {
        if (tb9)
            setTb9(1);
        else
            setTb9(0);
    }

    public int getTb10() {
        return tb10;
    }

    public void setTb10(int tb10) {
        this.tb10 = tb10;
    }

    public void setTb10(boolean tb10) {
        if (tb10)
            setTb10(1);
        else
            setTb10(0);
    }

    public int getTb11() {
        return tb11;
    }

    public void setTb11(int tb11) {
        this.tb11 = tb11;
    }

    public void setTb11(boolean tb11) {
        if (tb11)
            setTb11(1);
        else
            setTb11(0);
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof PropPair) {

            if (propA == null || propB == null) {
                return false;
            }

            if (propA.equals(((PropPair) obj).getPropA())
                    && propB.equals(((PropPair) obj).getPropB())) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public String toString() {
        return templateA+"/"+propA+" -> "+templateB+"/"+propB+" ["+m1+"]";
    }
}

