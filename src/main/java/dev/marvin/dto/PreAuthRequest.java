package dev.marvin.dto;

import jakarta.validation.constraints.Pattern;

public record PreAuthRequest(
        @Pattern(regexp = "254[0-9]{9}", message = "Provide a valid mobile number starting with 254 and followed by 9 digits")
        String mobile) {

    public boolean hasMobile() {
        return mobile != null && !mobile.isEmpty();
    }
}