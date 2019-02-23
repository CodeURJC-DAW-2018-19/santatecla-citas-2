package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Quote;
import com.urjc.daw.practica.model.Text;
import com.urjc.daw.practica.model.Topic;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextRepositoryTest {

    @Autowired
    TextRepository texts;

    @Autowired
    TopicRepository topics;


    @Test
    public void test_saveText(){
        Text current= dummyText();

        Assertions.assertThat(texts.findAll()).extracting(Text::getId).contains(current.getId());
    }

    @Test
    public void test_findByTopic(){
        Text current= dummyText();
        Topic currentTop = current.getTopiccontaining();

        Assertions.assertThat(texts.findByTopic(currentTop)).extracting(Text::getTopiccontaining).containsOnly(currentTop);
    }

    @Test
    public void test_findByTopic_notFound(){
        Text currentText= dummyText();
        Assertions.assertThat(texts.findByTopic(dummyTopic())).isEmpty();
    }

    public Text dummyText(){
        Topic topic = dummyTopic();
        topic=topics.save(topic);
        Text currentText= new Text("TESTING TEXT",topic);

        currentText=texts.save(currentText);
        return currentText;
    }


    public Topic dummyTopic(){
        Topic topic = new Topic();
        topic = topics.save(topic);
        return topic;
    }
}
