package com.mobileprovider.midterm4458.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usages")
public class Usage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subscriberNo;
    private Integer month;
    private Integer year;

    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    private Integer amount;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getter ve Setter'lar

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubscriberNo() { return subscriberNo; }
    public void setSubscriberNo(String subscriberNo) { this.subscriberNo = subscriberNo; }

    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public UsageType getUsageType() { return usageType; }
    public void setUsageType(UsageType usageType) { this.usageType = usageType; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
