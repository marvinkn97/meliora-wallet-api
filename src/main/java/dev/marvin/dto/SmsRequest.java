package dev.marvin.dto;

public record SmsRequest(String to, String from, String message) {
}