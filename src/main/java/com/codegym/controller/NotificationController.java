package com.codegym.controller;

import com.codegym.model.Notification;
import com.codegym.service.impl.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {
       @Autowired
       private NotificationService notificationService;

       @MessageMapping("/notification.send")
       @SendTo("/topic/public")
       public Notification sendNotification(@Payload Notification notification){

              return notification;
       }

       @MessageMapping("/notification.add")
       @SendTo("/topic/public")
       public Notification addNotification(@Payload Notification notification, SimpMessageHeaderAccessor messageHeaderAccessor){
              messageHeaderAccessor.getSessionAttributes().put("name",notification.getContent());
              return notification;
       }
}
