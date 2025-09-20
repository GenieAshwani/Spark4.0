package com.cfs.Payment_Gateway.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail,String name,String course,double amount)
    {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Payment Successful - "+course);
        mailMessage.setText("Hi "+name +", \n\n"+
                "Thank you for enrolling in "+course+" .\n\n"+
                "we are looking forward to see you in live class"
                );
        javaMailSender.send(mailMessage);
    }
}
