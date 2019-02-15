package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Topic;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteRepositoryTest {

    @Autowired
    QuoteRepository quotes;

    @Autowired
    TopicRepository themes;

    @Test
    public void test_findById_found(){

        Quote currentQuote = dummyQuote();
        Quote found = quotes.findQuoteById(currentQuote.getId());
        Assert.assertEquals(found.getId(),currentQuote.getId());
    }

    @Test
    public void test_findById_notFound(){
        dummyQuote();
        Quote found = quotes.findQuoteById(10000000000L);
        Assert.assertNull(found);
    }



    @Test
    public void test_findByAuthor_found(){
        Quote currentQuote = dummyQuote();

        Assertions.assertThat(quotes.findByAuthor(currentQuote.getAuthor())).extracting(Quote::getAuthor).containsOnly(currentQuote.getAuthor());
    }

    @Test
    public void test_findByAuthor_notFound(){
        Quote currentQuote = dummyQuote();
        currentQuote=quotes.save(currentQuote);

        Assertions.assertThat(quotes.findByAuthor("")).extracting(Quote::getAuthor).doesNotContain(currentQuote.getAuthor());
    }

    public Quote dummyQuote(){
        Quote current = new Quote("SampleText","dummyAuthor","testBook");

        return quotes.save(current);


    }



}
