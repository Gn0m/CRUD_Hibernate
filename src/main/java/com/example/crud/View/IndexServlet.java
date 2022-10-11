package com.example.crud.View;

import java.io.*;

import java.util.List;

import com.example.crud.Controller.ConnectorDAO;
import com.example.crud.Model.PictureEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "")
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -390558789393883197L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        List<PictureEntity> picture = new ConnectorDAO().selectAll();
        request.setAttribute("pictures", picture);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

}