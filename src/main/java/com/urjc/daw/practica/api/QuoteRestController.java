package com.urjc.daw.practica.api;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.service.impl.QuoteManagementServiceImpl;

@RestController
@RequestMapping("/api/quotes")
public class QuoteRestController {

	private static final int QUOTES_PER_PAGE = 10;

	@Autowired
	QuoteManagementServiceImpl quoteService;

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Quote>> findOne(@PathVariable long id) {
		Optional<Quote> found = quoteService.findOne(id);
		if(found.isPresent()) {
			return new ResponseEntity<>(found,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(params = {"page"}) 
	public ResponseEntity<Page<Quote>> findAll(@RequestParam("page") int nPag) {
		Page<Quote> pageable = quoteService.findAll(nPag, QUOTES_PER_PAGE);
		if(pageable.hasContent()) {
			return new ResponseEntity<>(pageable,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Quote postQuote(@RequestBody Quote quote) {
		quoteService.save(quote);
		return quote;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Quote> editQuote(@PathVariable long id,@RequestBody Quote quote) {
		Quote found = quoteService.findOne(id).get();
		if(found != null) {
			quote.setId(id);
			quoteService.save(quote);
			return new ResponseEntity<>(found,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Optional<Quote>> deleteQuote(@PathVariable long id) {
		Optional<Quote> deleted = quoteService.findOne(id);
		if(deleted.isPresent()) {
			quoteService.deleteQuote(id);
			return new ResponseEntity<>(deleted,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public List<Quote> findByKeyword(@RequestBody String keyword) {
		return quoteService.findByKeyword(keyword);
	}

}
