package com.mobileprovider.midterm4458.repository;

import com.mobileprovider.midterm4458.entity.Usage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageRepository extends JpaRepository<Usage, Long> {

    Page<Usage> findBySubscriberNoAndMonthAndYear(String subscriberNo, int month, int year, Pageable pageable);
}
