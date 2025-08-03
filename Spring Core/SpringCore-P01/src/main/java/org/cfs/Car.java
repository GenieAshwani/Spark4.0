package org.cfs;

public class Car {

    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public Car(Engine engine) {
        System.out.println("Car constructor....");
        this.engine = engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void drive()
    {
        int start = engine.start();
        if(start>=1)
        {
            System.out.println("Let's drive");
        }
        else
        {
            System.out.println("Engine not started yet....");
        }
    }
}
