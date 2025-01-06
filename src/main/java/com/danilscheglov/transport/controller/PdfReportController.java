//package com.danilscheglov.transport.controller;
//
//import com.danilscheglov.transport.util.PdfReportGenerator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/reports")
//@RequiredArgsConstructor
//public class PdfReportController {
//    private final PdfReportGenerator pdfReportGenerator;
//
//    @GetMapping("/pdf")
//    public void generatePdfReport(HttpServletResponse response) throws IOException {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
//        try {
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating PDF report");
//        }
//    }
//}
