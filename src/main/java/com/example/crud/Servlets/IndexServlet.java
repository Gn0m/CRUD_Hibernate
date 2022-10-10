package com.example.crud.Servlets;

import java.io.*;
import java.util.ArrayList;

import com.example.crud.Database.Connector;
import com.example.crud.Database.Picture;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "")
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -390558789393883197L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        ArrayList<Picture> picture = new Connector().select();
        request.setAttribute("pictures", picture);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

}