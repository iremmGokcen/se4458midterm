package com.mobileprovider.midterm4458.controller;

import com.mobileprovider.midterm4458.entity.Usage;
import com.mobileprovider.midterm4458.entity.UsageType;
import com.mobileprovider.midterm4458.service.UsageService;
import com.mobileprovider.midterm4458.dto.UsageRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usages")
@Tag(name = "Usage", description = "Kullanım verileri ekleme ve listeleme işlemleri")
public class UsageController {

    private final UsageService usageService;

    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }

    @Operation(summary = "Yeni kullanım verisi ekle", description = "Kullanıcının belirli bir ay/yıl için kullanım verisini (DATA) ekler.")
    @PostMapping
    public Usage createUsage(@RequestBody UsageRequestDTO usageRequest) {
        Usage usage = new Usage();
        usage.setSubscriberNo(usageRequest.getSubscriberNo());
        usage.setUsageType(UsageType.valueOf(usageRequest.getUsageType().toUpperCase()));
        usage.setMonth(usageRequest.getMonth());
        usage.setYear(usageRequest.getYear());
        usage.setAmount((int) usageRequest.getAmount());
        usage.setCreatedAt(usageRequest.getCreatedAt());

        return usageService.saveUsage(usage);
    }

    @Operation(summary = "Tüm kullanım verilerini getir", description = "Veritabanındaki tüm kullanım verilerini getirir.")
    @GetMapping
    public List<Usage> getAllUsages() {
        return usageService.getAllUsages();
    }
}
