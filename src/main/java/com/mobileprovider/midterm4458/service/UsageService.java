package com.mobileprovider.midterm4458.service;


import com.mobileprovider.midterm4458.entity.Usage;
import com.mobileprovider.midterm4458.repository.UsageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsageService {

    private final UsageRepository usageRepository;

    public UsageService(UsageRepository usageRepository) {
        this.usageRepository = usageRepository;
    }

    public Usage saveUsage(Usage usage) {
        return usageRepository.save(usage);
    }

    public List<Usage> getAllUsages() {
        return usageRepository.findAll();
    }
}
