package com.cfs.Payment_Gateway.controller;


import com.cfs.Payment_Gateway.entity.PaymentOrder;
import com.cfs.Payment_Gateway.service.PaymentService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody PaymentOrder order)
    {
        System.out.println("inside controller..........");
        try {
            String response= paymentService.createOrder(order);
            return ResponseEntity.ok(response);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating order");
        }
    }

    @PostMapping("/update-order")
    public ResponseEntity<String> updateOrderStatus(@RequestParam String paymentId,
                                                    @RequestParam String orderId,
                                                    @RequestParam String status)
    {
        paymentService.updateOrderStatus(paymentId,orderId,status);
        System.out.println("order place successfully....");
        return ResponseEntity.ok("Order updated Successfully...");
    }
}
