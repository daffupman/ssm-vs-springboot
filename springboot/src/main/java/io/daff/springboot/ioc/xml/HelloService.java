package io.daff.springboot.ioc.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/23
 */
@Component
public class HelloService {

    private Student student;
    // @Autowired
    // @Qualifier("cat")
    private Animal animal;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String hello() {
        return student.toString() + "\n" + animal.getName();
    }

    public String hi() {
        return animal.getName();
    }
}
