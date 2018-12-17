package com.example.javatest.main;

import com.example.javatest.Animal;
import com.example.javatest.Cat;
import com.example.javatest.Dog;

/**
 * Created by 梁明伟 on 2018/12/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class JavaStudyMain {
    public  static void main(String[] args){

        Animal<Cat> cat = new Animal<Cat>();
        Animal<Dog> dog = new Animal<Dog>();

        cat.setT(new Cat("mmm","white"));
        dog.setT(new Dog("www","black"));

        cat.getClass();


        System.out.println(cat.getClass());
        System.out.println(dog.getClass());
        System.out.println(cat.getClass().equals(dog.getClass()));
        cat.getT().voice();
        dog.getT().voice();

    }
}
