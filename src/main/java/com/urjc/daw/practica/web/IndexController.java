package es.codeurjc.daw.library.web;

import java.util.Optional;

import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.TextManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class BookWebController {

	@Autowired
    private QuoteManagementService quoteService;
    
    @Autowired
    private TextManagementService textService;
	
	@Autowired
	private User userComponent;

    //Login
	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			model.addAttribute("userName",userComponent.getLoggedUser().getName());
		}
	}
	
	@GetMapping("/")
	public String showQuotes(Model model) {

		model.addAttribute("quotes", service.findAll());
	
		return "quotes";
	}
	
	@GetMapping("/quotes/{id}")
	public String showQuote(Model model, @PathVariable long id) {
		
		Optional<Quote> quote = service.findOne(id);

		if(quote.isPresent()) {
			model.addAttribute("quote", quote.get());
		}

		return "quote";
	}
	
	@GetMapping("/newQuote")
	public String newQuote(Model model) {
		return "quoteForm";
	}
	
	@GetMapping("/editQuote/{id}")
	public String newQuote(Model model, @PathVariable long id) {
		
		Optional<Quote> quote = service.findOne(id);
		
		if(quote.isPresent()) {
			model.addAttribute("quote", quote.get());
		}
		
		return "quoteForm";
	}
	
	@PostMapping("/saveTopic")
	public String saveQuote(Model model, Quote quote) {
		
		service.save(quote);
		
		return "quoteCreated";
	}
	
	@GetMapping("/deleteQuote/{id}")
	public String deleteQuote(Model model, @PathVariable long id) {
		
		service.delete(id);
		
		return "quoteDeleted";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("hideLogin", true);
		return "login";
	}
	
	@GetMapping("/loginerror")
	public String loginError() {
		return "loginerror";
	}
}