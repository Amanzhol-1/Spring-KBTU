package kbtuspring.beanscopes.controller;

import kbtuspring.beanscopes.model.Person;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingletonScopeTest {

    private static final String NAME = "John Smith";

    @Test
    public void givenSingletonScope_whenSetName_thenEqualNames() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("scopes.xml");

        Person personSingletonA = (Person) applicationContext.getBean("personSingleton");
        Person personSingletonB = (Person) applicationContext.getBean("personSingleton");

        personSingletonA.setName(NAME);
        //Assert.assertEquals(NAME, personSingletonB.getName());

        Assert.assertSame(personSingletonA, personSingletonB);

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
