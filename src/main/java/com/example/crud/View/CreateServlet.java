package com.example.crud.View;

import com.example.crud.Controller.ConnectorDAO;
import com.example.crud.Model.PictureEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

@MultipartConfig
@WebServlet(name = "create", value = "/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = -1158571200668059774L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = null;
        String appPath = request.getServletContext().getRealPath("");
        String path = appPath + "img" + File.separator;
        try {

            file = EditServlet.getFile(request, file, path);

            String name = request.getParameter("name");
            String author = request.getParameter("author");
            int year = Integer.parseInt(request.getParameter("year"));
            String storage = request.getParameter("storage");
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
            String link = file.getName();
            PictureEntity picture = new PictureEntity(name, author, year, storage, price, link);
            new ConnectorDAO().insert(picture);
            response.sendRedirect(request.getContextPath() + "");
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
        }
    }
}
