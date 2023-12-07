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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/public");

            get("/", (request, response) -> {
                HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
                Map<String, Object> payload = new HashMap<>();
                ModelAndView model = new ModelAndView(payload, "index.hbs");
                logger.info("requestRefId = requestID | statusCode = 200  | status = OK | message = visited home  |");
                return engine.render(model);
            });

            get("/animal/all", (request, response) -> {
                HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
                Map<String, Object> payload = new HashMap<>();
                try{
                    List<Animal> animals =AnimalService.getAll();
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


        }



}