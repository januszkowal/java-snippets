package org.blacksmith.jsnip.annotation;

public class EnableXyzAttributes {
    private final String prop1;
    private final boolean prop2;

    public EnableEsShellAttributes(String prop1, boolean isProp2) {
        this.prop1 = prop1;
        this.isProp2 = isProp2;
    }

    public String getProp1() {
        return prop1;
    }

    public boolean isProp2() {
        return prop2;
    }
}
