package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.QuoteController;
import com.urjc.daw.practica.model.Image;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.security.UserComponent;
import com.urjc.daw.practica.service.QuoteManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
    private static final Path FILES_FOLDER = Paths.get("/images");
    private AtomicInteger imageId = new AtomicInteger();
    private Map<Integer, Image> images = new ConcurrentHashMap<>();

    
    @Autowired
    QuoteManagementService quoteService;
    
    @Autowired
    UserComponent userComponent;
    
    @ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("role", userComponent.getLoggedUser().toString());
			model.addAttribute("username",userComponent.getLoggedUser().getName());
			if(userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN")){
				model.addAttribute("admin",userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
				model.addAttribute("user",userComponent.getLoggedUser().getRoles().contains("ROLE_USER"));
			}
		}
	}



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
        model.addAttribute("cod","creada");
        return "quoteCreated";
    }

    @Override
    @GetMapping("/editQuote/{id}")
    public String editQuote(Model model,@PathVariable long id , HttpServletResponse res) throws IOException {
        Optional<Quote> quote = quoteService.findOne(id);
        
        if(quote.isPresent()) {
        	model.addAttribute("quote",quote.get());
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
        return "quoteForm";
    }

    @Override
    @GetMapping("/deleteQuote/{id}")
    public String deleteQuote(Model model,@PathVariable long id) {
        quoteService.deleteQuote(id);
        model.addAttribute("cod","eliminada");
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


}
