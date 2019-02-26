package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.TopicController;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import com.urjc.daw.practica.security.UserComponent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.TopicManagementService;
import com.urjc.daw.practica.service.impl.DocumentGenerationService;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TopicControllerImpl implements TopicController {


	@Autowired
    private TopicManagementService topicService;

	@Autowired
    private QuoteManagementService quoteService;


    @Autowired
    private UserComponent userComponent;


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
    public Topic getTopic() {
        return null;
    }

    @Override
    public List<Topic> getAllTopics() {
        return null;
    }

    @Override
    @PostMapping("/createTopic")
    public String saveTopic(Model model, Topic topic){
        topicService.save(topic);

        model.addAttribute("cod","El tema ha sido creado");
        return "created";
    }

    @Override
    @GetMapping("/topic/{id}")
    public String editTopic(Model model, @PathVariable long id) {
        Optional<Topic> topic = topicService.findOne(id);

        if(topic.isPresent()) {
            Topic currentTopic = topic.get();
            List<Quote> quotesReferenced = topicService.getReferencedQuotes(currentTopic);
            model.addAttribute("name",currentTopic.getName());
            model.addAttribute("quoteReference",quotesReferenced);
            model.addAttribute("textReference",currentTopic.getTexts());
            model.addAttribute("topic",currentTopic);
            if(((List<Quote>)model.asMap().get("quoteReference")).isEmpty()){
                model.addAttribute("quote",quoteService.findAll());
            }else {
                model.addAttribute("quote", quoteService.findByIdDiferrentThan(currentTopic.getQuoteIds()));
            }
        }
        return "topicForm";
    }


    @Override
    @GetMapping("/deleteTopic/{id}")
    public String deleteTopic(Model model, @PathVariable Long id) {
        Optional<Topic> current = topicService.findOne(id);
        if (current.isPresent()){
            topicService.deleteTopic(current.get());
            model.addAttribute("cod","El tema ha sido eliminado");
            return "created";
        }else{
            model.addAttribute("cod","error al borrar, no encontrado");
            return "created";
        }

    }

    @Override
    @GetMapping("/searchTopic")
    public String findByKeyword(@RequestParam(value = "keyword") String keyword, Model model) {
        model.addAttribute("quote",quoteService.findAll());
        model.addAttribute("topic",topicService.findByKeyword(keyword));
        return "index";
    }
    
    @Autowired
    DocumentGenerationService dgs;
    
    @GetMapping("/generatePDF/{id}")
    public byte[] generatePDF(Model model,@PathVariable Long id) {
        Map<String,Object> map = new HashMap<String,Object>();
        Topic topic = topicService.findOne(id).get();
        Iterator it = topic.getQuoteIds().iterator();
        String quotes = "";
        while(it.hasNext()){
            Quote q = quoteService.findOne((long)it.next()).get();
            quotes += q.getText() + "/" + q.getAuthor() + "/" + q.getBook() + ";";
        }

        it = topic.getTexts().iterator();
        String texts = "";
        while(it.hasNext()){
            texts += it.next() + ";";
        }

        map.put("Quotes", (String)quotes);
        map.put("Texts", (String)texts);
    	
        byte[] document = null;
    	try {
			document = dgs.generateDocument("src/main/resources/static/template.odt", TemplateEngineKind.Velocity, map, null, true);
		} catch (IOException | XDocReportException e) {
			e.printStackTrace();
		}
    	
    	return document;
    }
}
