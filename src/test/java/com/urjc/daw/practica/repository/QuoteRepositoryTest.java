package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Theme;
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

    @Autowired
    ThemeRepository themes;

    @Test
    public void test_findById_found(){

        Quote currentQuote = dummyQuote(dummyTheme());
        Assertions.assertThat(quotes.findById(currentQuote.getId()).equals(currentQuote));
    }

    @Test
    public void test_findById_notFound(){
        Quote currentQuote = dummyQuote(dummyTheme());


        Assertions.assertThat(!quotes.findById(100L).equals(currentQuote));
    }

    @Test
    public void test_findByTheme_found(){
        Theme newTheme = dummyTheme();
        Quote currentQuote = dummyQuote(newTheme);

        Assertions.assertThat(quotes.findByThemesContains(newTheme).contains(currentQuote));
    }

    @Test
    public void test_findByTheme_notFound(){
        Theme newTheme = dummyTheme();
        Quote currentQuote = dummyQuote(newTheme);

        Assertions.assertThat(quotes.findByThemesContains(newTheme).contains(currentQuote));
    }

    @Test
    public void test_findByAuthor_found(){
        Quote currentQuote = dummyQuote(dummyTheme());

        Assertions.assertThat(quotes.findByAuthor(currentQuote.getAuthor()).contains(currentQuote));
    }

    @Test
    public void test_findByAuthor_notFound(){
        Quote currentQuote = dummyQuote(dummyTheme());
        currentQuote=quotes.save(currentQuote);

        Assertions.assertThat(!quotes.findByAuthor("").contains(currentQuote));
    }

    public Quote dummyQuote(Theme theme){
        Quote current = new Quote("SampleText","dummyAuthor","testBook");
        current.addTheme(theme);
        quotes.save(current);
        return current;

    }

    public Theme dummyTheme(){
        Theme current= new Theme();
        themes.save(current);
        return current;
    }

}
