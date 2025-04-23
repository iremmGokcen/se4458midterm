package com.mobileprovider.midterm4458.controller;

import com.mobileprovider.midterm4458.entity.Bill;
import com.mobileprovider.midterm4458.entity.Usage;
import com.mobileprovider.midterm4458.service.BillingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bill")
@Tag(name = "Billing", description = "Fatura işlemleri (hesaplama, sorgulama, ödeme)")
public class BillController {

    private final BillingService billingService;

    public BillController(BillingService billingService) {
        this.billingService = billingService;
    }

    @Operation(summary = "Fatura hesapla ve kaydet", description = "Belirtilen kullanıcı, ay ve yıla göre internet kullanımı üzerinden fatura hesaplar ve veritabanına kaydeder.")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/calculate")
    public ResponseEntity<String> calculateBill(
            @RequestParam String subscriberNo,
            @RequestParam int month,
            @RequestParam int year) {

        Bill savedBill = billingService.calculateAndSaveInternetBill(subscriberNo, month, year);
        return ResponseEntity.ok("Fatura başarıyla hesaplandı ve kaydedildi. Tutar: $" + savedBill.getTotalAmount());
    }

    @Operation(summary = "Detaylı fatura bilgisi (paging)", description = "Belirtilen kullanıcı için detaylı DATA kullanımı bilgilerini sayfalı şekilde döner.")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/detailed")
    public ResponseEntity<Page<Usage>> getDetailedBill(
            @RequestParam String subscriberNo,
            @RequestParam int month,
            @RequestParam int year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Usage> detailedUsages = billingService.getDetailedUsages(subscriberNo, month, year, page, size);
        return ResponseEntity.ok(detailedUsages);
    }

    @Operation(summary = "Fatura sorgula", description = "Belirli bir kullanıcı, ay ve yıl için toplam fatura tutarını ve ödeme durumunu döner.")
    @GetMapping
    public ResponseEntity<?> queryBill(
            @RequestParam String subscriberNo,
            @RequestParam int month,
            @RequestParam int year) {

        List<Bill> billOpt = billingService.getBill(subscriberNo, month, year);

        if (!billOpt.isEmpty()) {
            Bill bill = billOpt.get(0);
            String status = bill.isPaid() ? "Ödendi" : "Ödenmedi";
            return ResponseEntity.ok("Tutar: $" + bill.getTotalAmount() + ", Ödeme durumu: " + status);
        } else {
            return ResponseEntity.status(404).body("Fatura bulunamadı.");
        }
    }

    @Operation(summary = "Fatura ödeme işlemi", description = "Belirtilen fatura ödenmiş olarak işaretlenir.")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/pay")
    public ResponseEntity<String> payBill(
            @RequestParam String subscriberNo,
            @RequestParam int month,
            @RequestParam int year) {

        boolean success = billingService.payBill(subscriberNo, month, year);

        if (success) {
            return ResponseEntity.ok("Fatura başarıyla ödendi.");
        } else {
            return ResponseEntity.status(404).body("Fatura bulunamadı.");
        }
    }
}
