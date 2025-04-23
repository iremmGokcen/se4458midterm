package com.mobileprovider.midterm4458.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Request DTO for creating a new usage")
public class UsageRequestDTO {

    @Schema(description = "Subscriber number", example = "1001")
    private String subscriberNo;

    @Schema(description = "Usage type (CALL, DATA)", example = "CALL")
    private String usageType;

    @Schema(description = "Month of usage (1-12)", example = "4")
    private int month;

    @Schema(description = "Year of usage", example = "2025")
    private int year;

    @Schema(description = "Amount used", example = "15")
    private int amount;

    @Schema(description = "Date and time the usage was recorded", example = "2025-04-23T10:15:30")
    private LocalDateTime createdAt;

    public String getSubscriberNo() {
        return subscriberNo;
    }

    public void setSubscriberNo(String subscriberNo) {
        this.subscriberNo = subscriberNo;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
