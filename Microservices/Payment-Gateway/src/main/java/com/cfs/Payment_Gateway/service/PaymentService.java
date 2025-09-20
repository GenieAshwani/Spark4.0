package com.cfs.Payment_Gateway.service;


import com.cfs.Payment_Gateway.entity.PaymentOrder;
import com.cfs.Payment_Gateway.repo.PaymentRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;


    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private EmailService emailService;


    public String createOrder(PaymentOrder orderDetails) throws RazorpayException {
        System.out.println("inside service..........");

        RazorpayClient client = new RazorpayClient(keyId,keySecret);

        //json
        JSONObject orderRequest=new JSONObject();
        orderRequest.put("amount",(int)(orderDetails.getAmount()*100));  //.10
        orderRequest.put("currency","INR");
        orderRequest.put("receipt","txn_"+ UUID.randomUUID());

        Order rozorpayOrder=client.orders.create(orderRequest);

        System.out.println(rozorpayOrder.toString());
        orderDetails.setOrderId(rozorpayOrder.get("id"));
        orderDetails.setStatus("CREATED");
        orderDetails.setCreatedAt(LocalDateTime.now());

        paymentRepo.save(orderDetails);
        return rozorpayOrder.toString();
    }

    public void updateOrderStatus(String paymentId, String orderId, String status) {

        PaymentOrder order=paymentRepo.findByOrderId(orderId);
        order.setPaymentId(paymentId);
        order.setStatus(status);
        paymentRepo.save(order);
        if("SUCCESS".equalsIgnoreCase(order.getStatus()))
        {
            emailService.sendEmail(order.getEmail(),order.getName(),order.getCourseName(), order.getAmount());

        }
        }
}
