package com.example.crud.Servlets;

import com.example.crud.Database.Connector;
import com.example.crud.Database.Picture;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@MultipartConfig
@WebServlet(name = "edit", value = "/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = -8102053329750483499L;
    private Picture picture;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            picture = new Connector().selectOne(id);
            if (picture != null) {
                request.setAttribute("picture", picture);
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
        File file;
        String pathOld;
        String path = request.getServletContext().getRealPath("") + "img" + File.separator;
        String link = picture.getLink();
        pathOld = path.concat(link);
        file = new File(pathOld);
        file.delete();

        try {
            file = getFile(request, file, path);

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            int year = Integer.parseInt(request.getParameter("year"));
            String storage = request.getParameter("storage");
            double price = Double.parseDouble(request.getParameter("price"));
            link = file.getName();
            Picture picture = new Picture(id, name, author, year, storage, price, link);
            new Connector().update(picture);
            response.sendRedirect(request.getContextPath() + "");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
        }
    }

    static File getFile(HttpServletRequest request, File file, String path) throws IOException, ServletException {
        Collection<Part> items = request.getParts();
        for (Part item : items) {
            if (item.getName().equals("file")) {
                File uploadDir = new File(path);
                file = File.createTempFile("img", ".png", uploadDir);
                item.write(String.valueOf(file));
            }
        }
        return file;
    }
}
