package org.cfs;

public class DieselEngine implements Engine{

    public DieselEngine() {
        System.out.println("diesel constructor...");
    }

    public int start() {
        return 1;
    }
}
