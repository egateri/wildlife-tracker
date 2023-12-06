package com.moringaschool.student;

import com.moringaschool.student.config.DBConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import static spark.Spark.*;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/public");
        Connection connection = DBConfig.getConnection();
        get("/", (request, response) -> {

            logger.info("requestRefId = requestID | statusCode = 200  | status = OK | message = visited home  |");
            return "Welcome to Wildlife Tracker App";
        });


    }
}