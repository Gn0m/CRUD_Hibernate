package com.example.crud.Servlets;

import com.example.crud.Database.Connector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "delete", value = "/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = null;
        String path = request.getServletContext().getRealPath("")+"img\\";
        try {
            String link = request.getParameter("link");
            path = path.concat(link);
            file = new File(path);
            file.delete();
            int id = Integer.parseInt(request.getParameter("id"));
            Connector.delete(id);
            response.sendRedirect(request.getContextPath() + "");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}
