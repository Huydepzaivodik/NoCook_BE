package com.codegym.service.impl;

import com.codegym.model.Notification;
import com.codegym.repository.NotificationRepository;
import com.codegym.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public List<Notification> findAll() {
        return null;
    }

    @Override
    public Notification findById(Long id) {
        return null;
    }

    @Override
    public void save(Notification notification) {

    }

    @Override
    public void remove(Long id) {

    }
}
