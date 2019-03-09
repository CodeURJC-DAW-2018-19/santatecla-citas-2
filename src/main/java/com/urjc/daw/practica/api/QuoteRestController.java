package com.urjc.daw.practica.api;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.service.impl.QuoteManagementServiceImpl;

@RestController
@RequestMapping("/api/quote")
public class QuoteRestController {

	private static final int QUOTES_PER_PAGE = 10;

	@Autowired
	QuoteManagementServiceImpl quoteService;

	@GetMapping("/{id}")
	public Optional<Quote> findOne(@PathVariable long id) {
		return quoteService.findOne(id);
	}

	@GetMapping("/nPag")
	public List<Quote> findAll(@PathVariable int nPag) {
		return quoteService.findAll(nPag, QUOTES_PER_PAGE);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Quote postQuote(@RequestBody Quote quote, MultipartFile file) {
		quoteService.save(quote);
		quoteService.saveQuoteImage(file, quote);
		return quote;
	}

	@PutMapping("/{id}")
	public Quote editQuote(@PathVariable long id,@RequestBody Quote quote) {
		quoteService.findOne(id).get();
		quote.setId(id);
		quoteService.save(quote);
		return quote;
	}

	@DeleteMapping("/{id}")
	public Optional<Quote> deleteQuote(@PathVariable long id) {
		Optional<Quote> deleted = quoteService.findOne(id);
		quoteService.deleteQuote(id);
		return deleted;
	}

	@GetMapping("/")
	public List<Quote> findByKeyword(@RequestBody String keyword, Model model) {
		return quoteService.findByKeyword(keyword);
	}

}
