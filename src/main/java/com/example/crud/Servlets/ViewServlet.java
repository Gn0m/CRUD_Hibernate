package com.example.crud.Servlets;

import com.example.crud.Database.Connector;
import com.example.crud.Database.Gallery;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "view", value = "/view")
public class ViewServlet extends HttpServlet {
    private Gallery gallery;
    private String path;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            gallery = Connector.selectOne(id);

            if (gallery != null) {
                request.setAttribute("gallery", gallery);
                getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
