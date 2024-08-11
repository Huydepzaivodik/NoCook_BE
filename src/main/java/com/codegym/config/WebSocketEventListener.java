package com.codegym.config;

import com.codegym.model.Notification;
import com.codegym.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.SessionEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageSendingOperations;

//    @Autowired
//    private UserService userService;
//
//    @EventListener
//    public void UserShipNotification(SessionDisconnectEvent event){
//        StompHeaderAccessor stompHeaders = StompHeaderAccessor.wrap(event.getMessage());
//        Long from = (Long) stompHeaders.getSessionAttributes().get("from");
//        Long to = (Long) stompHeaders.getSessionAttributes().get("to");
//        var notification = Notification.builder().date(new Date(System.currentTimeMillis())).from(userService.findById(from).get()).to(userService.findById(to).get());
//
//    }
}
