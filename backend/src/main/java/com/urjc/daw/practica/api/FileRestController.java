package com.urjc.daw.practica.api;

import com.urjc.daw.practica.exception.FileStorageException;
import com.urjc.daw.practica.exception.MyFileNotFoundException;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.QuoteImage;
import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.impl.QuoteImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@RestController
public class FileRestController {

    @Autowired
    private  QuoteImageStorageService imageService;

    @Autowired
    private QuoteManagementService quoteService;

    @GetMapping(value = "/api/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] get(@PathVariable Long id){

        try {
            return this.imageService.getFile(id).getImage();
        } catch (MyFileNotFoundException e) {
            System.out.println("F");
            return null;
        }
    }

    @PostMapping(value = "/api/images/{id}")
    public ResponseEntity<QuoteImage> post(@PathVariable Long id, @RequestBody MultipartFile file){
        Optional<Quote> optional = quoteService.findOne(id);
        if (optional.isPresent()){
            return new ResponseEntity<>(quoteService.saveQuoteImage(file,optional.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

