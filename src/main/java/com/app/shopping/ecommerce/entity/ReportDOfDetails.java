package com.app.shopping.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "report_d_of_details")
public class ReportDOfDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;
    private String column6;
    @ManyToOne(fetch = FetchType.LAZY)
    private ReportTitle reportTitle;
}
