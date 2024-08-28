package com.app.shopping.ecommerce.payload;

import com.app.shopping.ecommerce.entity.ReportTitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto
{
    private Long id;
    private String name;
    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;
    private String column6;
    private Long reportTitleId;

    public ReportDto(long l, String reportDetails, long l1) {
        this.id = l;
        this.name = reportDetails;
        this.reportTitleId = l1;
    }
}
