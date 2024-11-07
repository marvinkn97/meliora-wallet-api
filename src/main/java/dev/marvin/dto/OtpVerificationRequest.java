package dev.marvin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record OtpVerificationRequest(

        @Pattern(regexp = "254[0-9]{9}", message = "Provide a valid mobile number starting with 254 and followed by 9 digits")
        String mobile,

        @NotBlank(message = "OTP must be provided")
        @Min(value = 6, message = "6 Digits required")
        String otp

) {
    public boolean hasMobile() {
        return mobile != null && !mobile.isEmpty();
    }

    public boolean hasOtp() {
        return otp != null && !otp.isEmpty();
    }

    public boolean isValid(){
        return hasOtp() && hasMobile();
    }

}