package com.product.star.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ContactConfiguration.class);
        ContactDao contactDao = applicationContext.getBean(ContactDao.class);
        ContactService contactService = applicationContext.getBean(ContactService.class);

        contactService.deleteAll();
        try {
            contactService.saveContacts(new ClassPathResource("contacts.csv").getFile().toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var contacts = contactDao.getAllContacts();
        System.out.println(contacts);
    }
}
