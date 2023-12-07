package com.moringaschool.student.service;

import com.moringaschool.student.config.DBConfig;
import com.moringaschool.student.models.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;

import java.util.List;



public class AnimalService {
    private static final Logger logger = LoggerFactory.getLogger(AnimalService.class);

    static Connection connection = DBConfig.getConnection();

    //GET All Animals
    public static List<Animal> getAll() {
        String sql = "SELECT * FROM animals;";
            return connection.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
    }
    //SAVE Animal
    public void saveAnimal (String name,String type) {
            String sql = "INSERT INTO animals (name, type) VALUES (:name, :type)";
            connection.createQuery(sql, true)
                    .addParameter("name", name)
                    .addParameter("type", type)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();

    }

    //GET Animal by ID.
    public Animal getAnimalById ( int id){
        String sql = "SELECT * FROM animals WHERE id = :id;";
        return connection.createQuery(sql)
                .addParameter("id", id)
                .throwOnMappingFailure(false)
                .executeAndFetchFirst(Animal.class);

    }
    //UPDATE Animal
    public void updateAnimal (int id,String name) {
        String sql = "UPDATE animals SET name = :name WHERE id = :id";

        connection.createQuery(sql)
                .addParameter("name", name)
                .addParameter("id", id)
                .throwOnMappingFailure(false)
                .executeUpdate();
    }

    //DELETE Animal
    public void deleteAnimal (int id) {

        String sql = "DELETE from animals WHERE id = :id";
        connection.createQuery(sql)
                .addParameter("id", id)
                .throwOnMappingFailure(false)
                .executeUpdate();
        String sql2 = "DELETE from sightings WHERE animal_id = :id";
        connection.createQuery(sql2)
                .addParameter("id", id)
                .throwOnMappingFailure(false)
                .executeUpdate();

    }
}
