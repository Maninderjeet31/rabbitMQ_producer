package com.example.rabbitMQ.controller;

import com.example.rabbitMQ.model.User;
import com.example.rabbitMQ.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/api")
public class MessageController {

    private final MessageProducer messageProducer;

    @Autowired
    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/home")
    public String home(Model theModel){
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "home";
    }

    @GetMapping("/sendUser")
    public String sendUserJsonMessage(@ModelAttribute("user") User user){
        System.out.println("The user entered --->> " + user.toString());

        if (user.getFName() == null){
            user.setFName("Guest");
            user.setLName("Guest");
            user.setCity("N/A");
            user.setAge(18);
        }

        messageProducer.sendMessage(user);
        return "confirm";
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        messageProducer.sendMessage(user);
        return ResponseEntity.ok("User details sent... " +
                user.toString());
    }
}
