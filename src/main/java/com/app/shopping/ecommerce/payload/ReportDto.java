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
    private Long reportTitleId;
}
