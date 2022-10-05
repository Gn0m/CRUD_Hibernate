package com.example.crud.Servlets;

import java.io.*;
import java.util.ArrayList;

import com.example.crud.Database.Connector;
import com.example.crud.Database.Gallery;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "")
public class IndexServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        ArrayList<Gallery> gallery = Connector.select();
        request.setAttribute("gallery", gallery);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

}