package com.urjc.daw.practica.repository;

import com.urjc.daw.practica.model.Topic;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicRepositoryTest {

    @Autowired
    TopicRepository topics;

    @Test
    public void test_saveTopic(){

        Topic topic = dummyTopic();
        Assertions.assertThat(topics.findAll()).contains(topic);
    }

    @Test
    public void test_findById_found(){
        Topic topic = dummyTopic();
        Assertions.assertThat(topics.findTopicById(topic.getId())).isEqualTo(topic);
    }

    @Test
    public void test_findById_notFound(){
        
    }

    public Topic dummyTopic(){
       Topic topic = new Topic();
       return topics.save(topic);
    }
}
