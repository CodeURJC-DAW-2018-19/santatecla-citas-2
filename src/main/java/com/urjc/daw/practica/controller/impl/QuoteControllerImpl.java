package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.QuoteController;
import com.urjc.daw.practica.model.Image;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.service.QuoteManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class QuoteControllerImpl implements QuoteController {

    private static final int QUOTES_PER_PAGE=10;
    private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");
    private AtomicInteger imageId = new AtomicInteger();
    private Map<Integer, Image> images = new ConcurrentHashMap<>();

    @Autowired
    QuoteManagementService quoteService;



    @Override
    @RequestMapping(value = "/quote/@{id}",method = RequestMethod.GET)
    public String findOne() {
        //ToDo Pedir a quote service que devuelva el quote segun el id
        return "quoteForm";
    }

    @Override
    @RequestMapping(value = "/quote",method = RequestMethod.GET)
    public String findAll() {
        //quoteService.findAll(nPage,QUOTES_PER_PAGE);
        quoteService.findAll();
        
        return "quote";
    }

    @Override
    @RequestMapping(value = "/quote",method = RequestMethod.POST)
    public String postQuote(Model model,Quote quote) {
    	quoteService.save(quote);
        return "quoteCreated";
    }

    @Override
    @GetMapping("/editQuote/{id}")
    public String editQuote(Model model,@PathVariable long id) {
        Optional<Quote> quote = quoteService.findOne(id);
        
        if(quote.isPresent()) {
        	model.addAttribute("quote",quote.get());
        }
        return "quoteForm";
    }

    @Override
    @GetMapping("/deleteQuote/{id}")
    public String deleteQuote(Quote quote,@PathVariable long id) {
        quoteService.deleteQuote(id);
        return "quoteCreated";
    }
    @RequestMapping("/quoteForm")
    public String index(Model model) {

        model.addAttribute("images", images.values());

        return "quoteForm";
    }
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public String handleFileUpload(Model model, @RequestParam("imageTitle") String imageTitle, @RequestParam("file") MultipartFile file) {

        int id = imageId.getAndIncrement();

        String fileName = "image-" + id + ".jpg";

        if (!file.isEmpty()) {
            try {

                File uploadedFile = new File(FILES_FOLDER.toFile(), fileName);
                file.transferTo(uploadedFile);

                images.put(id, new Image(id, imageTitle));

                return "uploaded";

            } catch (Exception e) {

                model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());

                return "uploaded";
            }
        } else {

            model.addAttribute("error", "The file is empty");

            return "uploaded";
        }
    }

    @RequestMapping("/image/{id}")
    public void handleFileDownload(@PathVariable String id, HttpServletResponse res)
            throws IOException {

        String fileName = "image-" + id + ".jpg";

        Path image = FILES_FOLDER.resolve(fileName);

        if (Files.exists(image)) {
            res.setContentType("image/jpeg");
            res.setContentLength((int) image.toFile().length());
            FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());

        } else {
            res.sendError(404, "File" + fileName + "(" + image.toAbsolutePath() + ") does not exist");
        }
    }
}
