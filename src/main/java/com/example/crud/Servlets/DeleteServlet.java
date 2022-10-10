package com.example.crud.Servlets;

import com.example.crud.Database.Connector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "delete", value = "/delete")
public class DeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 7442436782466453398L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file;
        String path = request.getServletContext().getRealPath("") + "img" + File.separator;
        try {
            String link = request.getParameter("link");
            path = path.concat(link);
            file = new File(path);
            file.delete();
            int id = Integer.parseInt(request.getParameter("id"));
            new Connector().delete(id);
            response.sendRedirect(request.getContextPath() + "");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}
