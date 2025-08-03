public class Car {
    Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive()
    {
        engine.start();
        System.out.println("car is moving....");
    }
}
