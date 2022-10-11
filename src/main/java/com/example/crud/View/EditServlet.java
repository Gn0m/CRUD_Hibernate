package com.example.crud.View;

import com.example.crud.Controller.ConnectorDAO;
import com.example.crud.Model.PictureEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

@MultipartConfig
@WebServlet(name = "edit", value = "/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = -8102053329750483499L;
    private PictureEntity picture;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            picture = new ConnectorDAO().selectOne(id);
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
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
            link = file.getName();
            PictureEntity picture = new PictureEntity(id, name, author, year, storage, price, link);
            new ConnectorDAO().update(picture);
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
