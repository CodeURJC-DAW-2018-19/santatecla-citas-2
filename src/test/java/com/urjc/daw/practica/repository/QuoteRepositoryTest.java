package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Quote;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteRepositoryTest {

    @Autowired
    QuoteRepository quotes;


    @Test
    public void test_findById_found(){

        /*Quote currentQuote = dummyQuote();
        Optional<Quote> found = quotes.findQuoteById(currentQuote.getId());
        Assert.assertEquals(found.getId(),currentQuote.getId());*/
    }

    @Test
    public void test_findById_notFound(){
        /*dummyQuote();
        Optional<Quote> found = quotes.findQuoteById(10000000000L);
        Assert.assertNull(found);*/
    }


    @Test
    public void test_findByAuthor_found(){
        Quote currentQuote = dummyQuote();

        Assertions.assertThat(quotes.findByAuthor(currentQuote.getAuthor(), PageRequest.of(0,100))).extracting(Quote::getAuthor).contains(currentQuote.getAuthor());
    }

    @Test
    public void test_findByAuthor_notFound(){
        Quote currentQuote = dummyQuote();
        currentQuote=quotes.save(currentQuote);



        Assertions.assertThat(quotes.findByAuthor("",PageRequest.of(0,100))).extracting(Quote::getAuthor).doesNotContain(currentQuote.getAuthor());
    }

    public Quote dummyQuote(){
        Quote current = new Quote("SampleText","dummyAuthor","testBook");
        return quotes.save(current);
    }




}
