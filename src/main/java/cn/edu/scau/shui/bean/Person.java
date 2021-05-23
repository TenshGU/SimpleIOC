package cn.edu.scau.shui.bean;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2020/11/28
 */
public class Person implements Speakable{
    private String name;
    private Integer age;

    @Override
    public void speak() {
        System.out.println("我是" + name +"，我在说话！");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
