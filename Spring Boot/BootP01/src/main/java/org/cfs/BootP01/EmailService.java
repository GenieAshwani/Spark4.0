package org.cfs.BootP01;


import org.springframework.stereotype.Service;

@Service
public class EmailService implements MessageService{
    @Override
    public String sendMessage() {
        return "Email: you have got new message!";
    }
}
