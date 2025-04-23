package com.mobileprovider.midterm4458.service;

import com.mobileprovider.midterm4458.entity.Bill;
import com.mobileprovider.midterm4458.entity.Usage;
import com.mobileprovider.midterm4458.repository.BillRepository;
import com.mobileprovider.midterm4458.repository.UsageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingService {

    private final UsageRepository usageRepository;
    private final BillRepository billRepository;

    public BillingService(UsageRepository usageRepository, BillRepository billRepository) {
        this.usageRepository = usageRepository;
        this.billRepository = billRepository;
    }

    public double calculateInternetBill(String subscriberNo, int month, int year) {
        List<Usage> internetUsages = usageRepository.findAll().stream()
                .filter(u -> u.getSubscriberNo().equals(subscriberNo) &&
                        u.getMonth() == month &&
                        u.getYear() == year &&
                        u.getUsageType().toString().equalsIgnoreCase("DATA"))
                .toList();

        int totalMb = internetUsages.stream().mapToInt(Usage::getAmount).sum();
        double totalGb = totalMb / 1024.0;

        if (totalGb <= 20) {
            return 50.0;
        } else {
            double extraGb = totalGb - 20;
            int extraBlocks = (int) Math.ceil(extraGb / 10);
            return 50.0 + (extraBlocks * 10.0);
        }
    }

    // ✅ NEW: Faturayı hesaplayıp veritabanına kaydetme
    public Bill calculateAndSaveInternetBill(String subscriberNo, int month, int year) {
        double amount = calculateInternetBill(subscriberNo, month, year);

        Bill bill = new Bill();
        bill.setSubscriberNo(subscriberNo);
        bill.setMonth(month);
        bill.setYear(year);
        bill.setTotalAmount(amount);
        bill.setPaid(false);
        bill.setCreatedAt(LocalDateTime.now());

        return billRepository.save(bill);
    }

    // ✅ Paging destekli detaylı kullanım sorgusu
    public Page<Usage> getDetailedUsages(String subscriberNo, int month, int year, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return usageRepository.findBySubscriberNoAndMonthAndYear(subscriberNo, month, year, pageable);
    }
    public List<Bill> getBill(String subscriberNo, int month, int year) {
        return billRepository.findBySubscriberNoAndMonthAndYear(subscriberNo, month, year);
    }
    public List<Bill> getBillSummary(String subscriberNo, int month, int year) {
        return billRepository.findBySubscriberNoAndMonthAndYear(subscriberNo, month, year);
    }
    public boolean payBill(String subscriberNo, int month, int year) {
        List<Bill> optionalBill = billRepository.findBySubscriberNoAndMonthAndYear(subscriberNo, month, year);
    
        if (!optionalBill.isEmpty()) {
            Bill bill = optionalBill.get(0);
            bill.setPaid(true);
            billRepository.save(bill);
            return true;
        }
    
        return false;
    }
    
}
