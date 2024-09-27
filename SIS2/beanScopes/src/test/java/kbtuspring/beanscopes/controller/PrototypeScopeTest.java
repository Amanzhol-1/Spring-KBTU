package kbtuspring.beanscopes.controller;


import kbtuspring.beanscopes.model.Person;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrototypeScopeTest {

    @Test
    public void givenPrototypeScope_whenSetNames_thenDifferentNames() {

        ApplicationContext context = new ClassPathXmlApplicationContext("prototype-scopes.xml");

        Person person1 = context.getBean("personPrototype", Person.class);
        Person person2 = context.getBean("personPrototype", Person.class);

        person1.setName("John Smith");
        person2.setName("Anna Jones");

        Assert.assertNotSame(person1, person2);
        Assert.assertEquals("John Smith", person1.getName());
        Assert.assertEquals("Anna Jones", person2.getName());

        ((ClassPathXmlApplicationContext) context).close();

    }
}

