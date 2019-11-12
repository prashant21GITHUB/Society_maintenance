package com.maintenance.controllers;

import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bajpai
 */
@Controller
@CrossOrigin
public class DownloadReportController {
    
    @RequestMapping(value = "/requests/report", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getNextPageRequests() throws IOException {

        ClassPathResource pdfFile = new ClassPathResource("HelloWorld.pdf");
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("content-disposition", "attachment; filename=Report.pdf");
//        header("Content-Disposition: attachment; filename=\"{$strFileName}\"");
//
//        header("Content-Transfer-Encoding: binary");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(pdfFile.contentLength())
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }
}
