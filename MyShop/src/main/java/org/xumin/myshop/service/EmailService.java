package org.xumin.myshop.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendMessage(String from, String subject, String body, String... to) throws MessagingException, UnsupportedEncodingException;
    void sendMessageWithAttachment(String from, String subject, String text, String attachmentPath, String... to) throws MessagingException, UnsupportedEncodingException;
}
