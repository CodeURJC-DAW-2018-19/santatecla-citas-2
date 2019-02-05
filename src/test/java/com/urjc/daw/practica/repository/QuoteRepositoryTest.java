package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Quote;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuoteRepositoryTest {

    @Autowired
    QuoteRepository quotes;

    @Test
    public void test_findById_found(){
        Quote currentQuote = new Quote("SampleText","dummyAuthor","testBook");
        currentQuote=quotes.save(currentQuote);

        Assertions.assertThat(quotes.findById(currentQuote.getId()).equals(currentQuote));
    }
}
