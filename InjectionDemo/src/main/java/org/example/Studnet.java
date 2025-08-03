package org.example;

public class Studnet {

    public Course course; //Course type ka object
    //dependency injection
    //setter injection


    public Studnet() {
    }

    public Studnet(Course course) {
        this.course = course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void study()
    {
        int start = course.enroll();
        if(start>=1)
        {
            System.out.println("Journey started....");
        }
        else{
            System.out.println("Payment Failed....");
        }
    }

}
