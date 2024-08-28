package com.app.shopping.ecommerce.services.impl;

import com.app.shopping.ecommerce.payload.ReportDto;
import com.app.shopping.ecommerce.services.PdfService;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.tomcat.util.log.SystemLogHandler;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(SpringExtension.class)
class PdfServiceImplTest {
    @Mock
    private ReportServiceImpl reportServiceImpl;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @Mock
    private Document documentMock;

    @InjectMocks
    private PdfServiceImpl pdfServiceImpl;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setErr(new PrintStream(errContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setErr(originalErr);
    }
    @Test
    void createPdf() {
        ReportDto reportDto = new ReportDto(1L,"Report Details","1","1","1","1","1","1",1L);
        when(reportServiceImpl.getReportByTitle(1L)).thenReturn(List.of(reportDto));
        assertNotNull(pdfServiceImpl.createPdf(1L));
    }


@Test
public void testGetInstanceThrowsDocumentException() throws Exception {
        ReportDto reportDto = new ReportDto(1L,"Report Details","1","1","1","1","1","1",1L);
        when(reportServiceImpl.getReportByTitle(1L)).thenReturn(List.of(reportDto));
//        Document document=Mockito.mock(Document.class);
//        doThrow(new DocumentException("Test exception")).when(document).add(any(Paragraph.class));
//        try(MockedConstruction<Document> documentMockedStatic = mockConstruction(Document.class)) {
//            documentMockedStatic.constructed().get(0).add(any(Paragraph.class));
//            String errorOutput = SystemLambda.tapSystemErr(() -> {
//                pdfServiceImpl.createPdf(1L);
//            });
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//    PdfWriter pdfWriterMock = mock(PdfWriter.class);

    // Mock the static method PdfWriter.getInstance
    try (MockedStatic<PdfWriter> mockedStatic = mockStatic(PdfWriter.class)) {
        mockedStatic.when(() -> PdfWriter.getInstance(documentMock, byteArrayOutputStream)).thenThrow(new DocumentException("Test exception"));

        // Mock methods of the Document class
//        doNothing().when(documentMock).add(any());
        String errorOutput = SystemLambda.tapSystemErr(() -> {
                pdfServiceImpl.createPdf(1L);
        });
        // Call the method under test
        assertEquals("Test exception", errorOutput);
    }
}
}