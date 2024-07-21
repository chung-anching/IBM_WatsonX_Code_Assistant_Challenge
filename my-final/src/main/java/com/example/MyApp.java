// package my-final.src.main.java.com.example;

package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


// @WebServlet("/api/data")
// public class MyApp extends HttpServlet {

//    private static final long serialVersionUID = 1L;

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String json = new String(Files.readAllBytes(Paths.get("src/main/resources/data.json")));
//        ObjectMapper mapper = new ObjectMapper();
//        Object obj = mapper.readValue(json, Object.class);
//        response.setContentType("application/json");
//        response.getWriter().write(mapper.writeValueAsString(obj));
//    }

// }

@WebServlet("/api/data")
public class MyApp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/data.json");
        if (inputStream == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
            return;
        }

        String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(json, Object.class);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(obj));
    }

}
