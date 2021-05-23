package cn.edu.scau.shui.test;


import cn.edu.scau.shui.bean.Family;
import cn.edu.scau.shui.bean.Speakable;
import cn.edu.scau.shui.factory.BeanFactory;
import cn.edu.scau.shui.factory.TXTContext;
import cn.edu.scau.shui.factory.XMLContext;
import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2020/11/28
 */
public class IOCTest {
    @Test
    public void testXMLContext() {
        BeanFactory beanFactory = new XMLContext("src\\beans.xml");
        Speakable person = (Speakable) beanFactory.getBean("person");
        System.out.println(person);
        person.speak();

        System.out.println("----------------------------------------");

        Family family = (Family) beanFactory.getBean("family");
        System.out.println(family);
        family.printInfo();
    }

    @Test
    public void testTXTContext() {
        BeanFactory beanFactory = new TXTContext("src\\beans.txt");
        Speakable person = (Speakable) beanFactory.getBean("person");
        System.out.println(person);
        person.speak();

        System.out.println("----------------------------------------");

        Family family = (Family) beanFactory.getBean("family");
        System.out.println(family);
        family.printInfo();
    }

}
