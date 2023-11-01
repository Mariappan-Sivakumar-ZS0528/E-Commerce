package com.app.shopping.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "report")
public class ReportTitle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @OneToMany(mappedBy = "reportTitle",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ReportDetails> reportDetails;
    @OneToMany(mappedBy = "reportTitle",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReportDOfDetails> reportDOfDetails;
}
