package org.cfs;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Test {
    public static void main(String[] args) {
        System.out.println("----------------BeanFactory Started------------------");
        //BeanFactory factory = new XmlBeanFactory(new ClassPathResource("your-beans.xml"));
        ApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
        System.out.println("----------------Bean file loaded------------------");

        System.out.println("sending request.....");
        System.out.println("--------------first call-----------------");
        Car car1=context.getBean(Car.class);

        System.out.println("--------------second call-----------------");
        Car car2=context.getBean(Car.class);
        System.out.println("same car object ? "+(car1==car2));

        System.out.println("--------------first call-----------------");
        PetrolEngine p1=context.getBean(PetrolEngine.class);

        System.out.println("--------------second call-----------------");
        PetrolEngine p2=context.getBean(PetrolEngine.class);

        System.out.println("same petrol engine object ? "+(p1==p2));



        //car.drive();
    }
}
