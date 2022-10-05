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
@WebServlet(name = "edit", value = "/edit")
public class EditServlet extends HttpServlet {
    private Gallery gallery;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            gallery = Connector.selectOne(id);
            if (gallery != null) {
                request.setAttribute("gallery", gallery);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = null;
        String pathOld = null;
        String path = request.getServletContext().getRealPath("")+"img\\";
        String link = gallery.getLink();
        pathOld = path.concat(link);
        file = new File(pathOld);
        file.delete();

        try {
            Collection<Part> items = request.getParts();
            for (Part item : items) {
                if (item.getName().equals("file")) {
                    File uploadDir = new File(path);
                    file = File.createTempFile("img", ".png", uploadDir);
                    item.write(String.valueOf(file));
                }
            }

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            int year = Integer.parseInt(request.getParameter("year"));
            String storage = request.getParameter("storage");
            double price = Double.parseDouble(request.getParameter("price"));
            link = file.getName();
            Gallery gallery = new Gallery(id, name, author, year, storage, price, link);
            Connector.update(gallery);
            response.sendRedirect(request.getContextPath() + "");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}
