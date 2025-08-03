package org.cfs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        Motor m=context.getBean(Motor.class);
        m.doWork();

        context.close();   //destroy-method
    }
}
