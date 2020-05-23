package io.daff.springboot.ioc.xml;

/**
 * @author daffupman
 * @since 2020/5/23
 */
public class AnimalFactory {

    public static final String TYPE_DOG = "dog";
    public static final String TYPE_CAT = "cat";

    public Animal getAnimal(String type) {
        if (TYPE_DOG.equalsIgnoreCase(type)) {
            return new Dog();
        } else if(TYPE_CAT.equalsIgnoreCase(type)) {
            return new Cat();
        } else {
            throw new RuntimeException("NOT SUPPORT ANIMAL TYPE : " + type);
        }
    }
}
