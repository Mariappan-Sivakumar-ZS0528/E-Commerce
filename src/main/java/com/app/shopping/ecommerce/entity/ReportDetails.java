package com.app.shopping.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reportDetails")
public class ReportDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String Column1;
    private String Column2;
    private String Column3;
    private String Column4;
    private String Column5;
    private String Column6;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_title_id")
    private ReportTitle reportTitle;

    public ReportDetails(long l, String name, ReportTitle reportTitle) {
        this.id = l;
        this.name = name;
        this.reportTitle = reportTitle;
    }
}
