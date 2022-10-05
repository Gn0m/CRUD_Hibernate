package com.example.crud.Servlets;

import com.example.crud.Database.Connector;
import com.example.crud.Database.Gallery;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@MultipartConfig
@WebServlet(name = "create", value = "/create")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = null;
        String appPath = request.getServletContext().getRealPath("");
        String path = appPath + "img\\";
        System.out.println(path);
        System.out.println(path);
        try {

            Collection<Part> items = request.getParts();

            for (Part item : items) {
                if (item.getName().equals("file")) {
                    File uploadDir = new File(path);
                    file = File.createTempFile("img", ".png", uploadDir);
                    item.write(String.valueOf(file));
                }
            }

            String name = request.getParameter("name");
            String author = request.getParameter("author");
            int year = Integer.parseInt(request.getParameter("year"));
            String storage = request.getParameter("storage");
            double price = Double.parseDouble(request.getParameter("price"));
            String link = file.getName();
            Gallery gallery = new Gallery(name, author, year, storage, price, link);
            Connector.insert(gallery);
            response.sendRedirect(request.getContextPath() + "");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
        }
    }
}
