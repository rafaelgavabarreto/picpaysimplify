package com.picpaysimplify.notification;

import com.picpaysimplify.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        try {
            restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);
        } catch (DataAccessException exception) {
            System.out.println("Notification service offline" + exception.getMessage());
        }
    }
}
