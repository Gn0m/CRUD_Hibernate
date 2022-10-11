package com.example.crud.View;

import com.example.crud.Controller.ConnectorDAO;
import com.example.crud.Model.PictureEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "view", value = "/view")
public class ViewServlet extends HttpServlet {
    private PictureEntity picture;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            picture = new ConnectorDAO().selectOne(id);

            if (picture != null) {
                request.setAttribute("picture", picture);
                getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }
}
