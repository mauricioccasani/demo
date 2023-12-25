package com.example.demo.controller;

import com.example.demo.service.TipoDocumentoReporteService;
import com.itextpdf.io.source.ByteArrayOutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Base64;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TipoDocumentoController {

    private final TipoDocumentoReporteService service;

    @GetMapping
    public ResponseEntity<byte[]> reporte() throws FileNotFoundException {
        ByteArrayOutputStream outputStream = service.documentoReporte();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("invoice.pdf").build());
       byte[] bytes= outputStream.toByteArray();
       // bytes=  Base64.getEncoder().encode(bytes);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

}
