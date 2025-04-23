package com.mobileprovider.midterm4458.repository;

import com.mobileprovider.midterm4458.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findBySubscriberNoAndMonthAndYear(String subscriberNo, int month, int year);

}
