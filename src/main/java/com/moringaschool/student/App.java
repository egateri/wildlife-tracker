package com.moringaschool.student;

import com.moringaschool.student.models.Animal;
import com.moringaschool.student.service.AnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        //PORT
        port(8080);
        staticFileLocation("/public");

            //GET - Home page
            get("/", (request, response) -> {
                HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
                Map<String, Object> payload = new HashMap<>();
                ModelAndView model = new ModelAndView(payload, "index.hbs");
                logger.info("requestRefId = requestID | statusCode = 200  | status = OK | message = visited home  |");
                return engine.render(model);
            });

            //GET - Get ALL Animals
            get("/animal", (request, response) -> {
                HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
                Map<String, Object> payload = new HashMap<>();
                try{
                    List<Animal> animals = AnimalService.getAll();
                    payload.put("animals", animals);
                    ModelAndView model = new ModelAndView(payload, "index.hbs");
                    logger.info("requestRefId = requestID | statusCode = 200  | status = OK | message = All animals queries |"+animals);
                    return engine.render(model);
                }
                catch (Exception ex){
                    logger.error("requestRefId = requestID | statusCode = 500  | status = error | message = internal server error |"+ex);
                return "Encountered an error";
                }

            });

        //POST - Save One Animal.
        post("/animal/new", (request, response) -> {
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            Map<String, Object> payload = new HashMap<>();
            String name = request.queryParams("name");
            String type = request.queryParams("type");
            List<String> list = new ArrayList<>();
            try{
                AnimalService.saveAnimal(name,type);
                list.add(name);
                list.add(type);
                payload.put("animal", list);
                ModelAndView model = new ModelAndView(payload, "index.hbs");
                logger.info("requestRefId = requestID | statusCode = 201  | status = created | message = created |"+list);
                return engine.render(model);
            }
            catch (Exception ex){
                logger.error("requestRefId = requestID | statusCode = 500  | status = error | message = internal server error |"+ex);
                return "Encountered an error adding animal";
            }

        });

        //DELETE - Delete One Animal By ID.
        delete("/animal/:id", (request, response) -> {
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            Map<String, Object> payload = new HashMap<>();
            String idString = request.params("id");
            int id = Integer.parseInt(idString);
            try{
                AnimalService.deleteAnimal(id);

                payload.put("animalId", id);
                ModelAndView model = new ModelAndView(payload, "index.hbs");
                logger.info("requestRefId = requestID | statusCode = 200  | status = OK | message = Deleted Success |"+id);
                return engine.render(model);
            }
            catch (Exception ex){
                logger.error("requestRefId = requestID | statusCode = 500  | status = error | message = internal server error |"+ex);
                return "Encountered an error adding animal";
            }

        });

        //PUT - Update One Animal By ID.
        put("/animal/:id", (request, response) -> {
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            Map<String, Object> payload = new HashMap<>();
            String name = request.queryParams("name");
            String idString = request.params("id");
            int id = Integer.parseInt(idString);
            try{
                AnimalService.updateAnimal(id,name);

                payload.put("animalId", id);
                ModelAndView model = new ModelAndView(payload, "index.hbs");
                logger.info("requestRefId = requestID | statusCode = 200  | status = OK | message = updated Success |"+id);
                return engine.render(model);
            }
            catch (Exception ex){
                logger.error("requestRefId = requestID | statusCode = 500  | status = error | message = internal server error |"+ex);
                return "Encountered an error adding animal";
            }

        });

        //GET - Get One Animal By ID.
        get("/animal/:id", (request, response) -> {
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            Map<String, Object> payload = new HashMap<>();
            String idString = request.params("id");
            int id = Integer.parseInt(idString);
            try{
               Animal animal= AnimalService.getAnimalById(id);

                payload.put("animalId", animal);
                ModelAndView model = new ModelAndView(payload, "index.hbs");
                logger.info("requestRefId = requestID | statusCode = 200  | status = OK | message = updated Success |"+animal);
                return engine.render(model);
            }
            catch (Exception ex){
                logger.error("requestRefId = requestID | statusCode = 500  | status = error | message = internal server error |"+ex);
                return "Encountered an error adding animal";
            }

        });

        }



}