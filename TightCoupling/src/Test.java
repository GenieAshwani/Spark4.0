public class Test {
    public static void main(String[] args) {
        Engine engine =EngineFactory.getEngine("petrol");
        Car car = new Car(engine);
        car.drive();
    }
}
