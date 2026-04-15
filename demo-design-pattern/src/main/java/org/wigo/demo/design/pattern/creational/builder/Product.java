package org.wigo.demo.design.pattern.creational.builder;

/**
 * @author wuwei31
 * @since 2021/5/14
 */
public class Product {

    private String partA;

    private String partB;

    private String partC;

    public String show() {
        return "Product{" +
                "partA='" + partA + '\'' +
                ", partB='" + partB + '\'' +
                ", partC='" + partC + '\'' +
                '}';
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }
}
