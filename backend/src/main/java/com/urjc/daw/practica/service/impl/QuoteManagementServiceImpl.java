package com.urjc.daw.practica.service.impl;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.QuoteImage;
import com.urjc.daw.practica.repository.QuoteRepository;
import com.urjc.daw.practica.service.QuoteManagementService;
import com.urjc.daw.practica.service.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteManagementServiceImpl implements QuoteManagementService {

	private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/images/quote/");
	
    @Autowired
    QuoteRepository quotes;

    @Autowired
    TopicManagementService topicService;

    @Autowired
    QuoteImageStorageService imageStorageService;

    @Override
    public Optional<Quote> findOne(Long id) {
        return quotes.findById(id);
    }

    /*@Override
    public Page<Quote> findAll(int nPage, int nQuotes) {
        return quotes.findAll(PageRequest.of(nPage,nQuotes));
    }*/
    
    @Override
    public Page<Quote> findAll(int nPag, int quotesPerPage) {
        Page<Quote> list =   quotes.findAll(PageRequest.of(nPag,quotesPerPage));
        return list;
    }

    @Override
    public Quote save(Quote quote) {
    	return quotes.save(quote);
    }

    @Override
    public Quote editQuote(Quote quote) {
        return quotes.save(quote);
    }

    @Override
    public Quote deleteQuote(Long id) {
        Quote quote = quotes.findById(id).get();
        quotes.delete(quote);
        topicService.deleteReference(quote.getId());
        return quote;
    }

    @Override
    public List<Quote> findByIdDiferrentThan(List<Long> ids) {
        return quotes.findByIdNotIn(ids);
    }

    @Override
    public List<Quote> findByKeyword(String keyword) {
        List<Quote> quotesFound = new ArrayList<>();
        if(!CollectionUtils.containsAny(quotesFound,findByAuthor(keyword))){
            quotesFound.addAll(findByAuthor(keyword));
        }
        if(!CollectionUtils.containsAny(quotesFound,findByBook(keyword))){
            quotesFound.addAll(findByBook(keyword));
        }
        if(!CollectionUtils.containsAny(quotesFound,quotes.findByTextContaining(keyword))){
            quotesFound.addAll(quotes.findByTextContaining(keyword));
        }

        return quotesFound;
    }

    @Override
    public List<Quote> findByAuthor(String author) {
        return quotes.findByAuthorContaining(author);
    }

    @Override
    public List<Quote> findByBook(String book) {
        return quotes.findByBookContaining(book);
    }

    @Override
    public QuoteImage saveQuoteImage(MultipartFile file, Quote quote) {
        QuoteImage  storage = null;
    	if (file != null &&!file.isEmpty()) {
            String imageName = "image-" + UUID.randomUUID() + ".jpg";
            try {
                if(quote.getImageId()!=null){
                    imageStorageService.removeImage(quote.getImageId());
                }
                storage= new QuoteImage(imageName,file.getBytes());
                storage = imageStorageService.save(storage);
                quote.setImageId(storage.getId());
                quotes.save(quote);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return storage;
    }

    public boolean checkValidQuote(Quote quote){
        return quote != null;
    }

    public List<Quote> findAllUnpaged() {
        List<Quote> page = quotes.findAll();
        return page;
    }
}
