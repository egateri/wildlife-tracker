package com.moringaschool.student.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Animal {
    public int id;
    public String name;
    public String type;
    public static final String ANIMAL_TYPE = "Non-endangered";

    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

    public Animal(String name) {
        if (name.isEmpty()) {
            logger.warn("Please enter the animal name");
            throw new IllegalArgumentException("Please enter an animal name.");
        }
        this.name = name;
        type = ANIMAL_TYPE;
    }
    public void setId(int id) {
        this.id = id;
    }
        public int getId () {
        return id;
    }

        public String getType () {
        return type;
    }
        public void setName (String name){
        this.name = name;
    }
    public String getName () {
        return name;
    }

        @Override
        public boolean equals (Object otherAnimal){
        if (otherAnimal instanceof Animal) {
            Animal newAnimal = (Animal) otherAnimal;
            return (this.getName().equals(newAnimal.getName()));
        }

        return false;
    }


}