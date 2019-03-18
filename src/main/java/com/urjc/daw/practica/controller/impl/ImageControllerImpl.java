package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.exception.MyFileNotFoundException;
import com.urjc.daw.practica.model.QuoteImage;
import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.impl.QuoteImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImageControllerImpl {

    @Autowired
    private QuoteImageStorageService service;

    @GetMapping(value = "/images/{id}")
    public ResponseEntity<byte[]> get(@PathVariable Long id) throws MyFileNotFoundException {
        final QuoteImage quoteImage = this.service.getFile(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + quoteImage.getImageName() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return ResponseEntity.ok().headers(headers).body(quoteImage.getImage());
    }

}
