package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.ReportDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportDetailsRepository extends JpaRepository<ReportDetails, Long>
{
    List<ReportDetails> findByReportTitleId(Long reportTitleId);
}
