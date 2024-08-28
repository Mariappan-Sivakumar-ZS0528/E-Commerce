package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.entity.ReportDetails;
import com.app.shopping.ecommerce.payload.ReportDto;
import com.app.shopping.ecommerce.services.PdfService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.mapping.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {
    @Autowired
    private ReportServiceImpl reportServiceImpl;
    @Override
    public byte[] createPdf(Long reportId) {
        List<ReportDto> reportDtos = reportServiceImpl.getReportByTitle(reportId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(new Paragraph("Report Details"));
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.addCell("Column 1");
            table.addCell("Column 2");
            table.addCell("Column 3");
            table.addCell("Column 4");
            table.addCell("Column 5");
            table.addCell("Column 6");
            for (ReportDto reportDto : reportDtos) {
                table.addCell(reportDto.getColumn1());
                table.addCell(reportDto.getColumn2());
                table.addCell(reportDto.getColumn3());
                table.addCell(reportDto.getColumn4());
                table.addCell(reportDto.getColumn5());
                table.addCell(reportDto.getColumn6());
            }
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
