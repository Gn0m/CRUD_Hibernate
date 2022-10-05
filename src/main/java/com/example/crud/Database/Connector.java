package com.example.crud.Database;

import java.sql.*;
import java.util.ArrayList;

public class Connector {

    private static String url = "jdbc:mysql://localhost:3306/gallery";
    private static String username = "root";
    private static String password = "G2712199188312g";

    public static ArrayList<Gallery> select() {

        ArrayList<Gallery> galleryList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM gallery");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String author = resultSet.getString(3);
                    int year = resultSet.getInt(4);
                    String storage = resultSet.getString(5);
                    double price = resultSet.getInt(6);
                    String link = resultSet.getString(7);
                    Gallery gallery = new Gallery(id, name, author, year, storage, price, link);
                    galleryList.add(gallery);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return galleryList;
    }

    public static Gallery selectOne(int id) {

        Gallery gallery = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM gallery WHERE id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int prodId = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        String author = resultSet.getString(3);
                        int year = resultSet.getInt(4);
                        String storage = resultSet.getString(5);
                        double price = resultSet.getInt(6);
                        String link = resultSet.getString(7);
                        gallery = new Gallery(prodId, name, author, year, storage, price, link);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return gallery;
    }

    public static int insert(Gallery gallery) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "INSERT INTO gallery (Name,Author,Year,Storage,Price,Link) Values (?,?,?,?,?,?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, gallery.getName());
                    preparedStatement.setString(2, gallery.getAuthor());
                    preparedStatement.setInt(3, gallery.getYear());
                    preparedStatement.setString(4, gallery.getStorage());
                    preparedStatement.setDouble(5, gallery.getPrice());
                    preparedStatement.setString(6, gallery.getLink());

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static int update(Gallery gallery) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "UPDATE gallery SET Name = ?,Author = ?,Year = ?,Storage = ?,Price = ?," +
                        "Link = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, gallery.getName());
                    preparedStatement.setString(2, gallery.getAuthor());
                    preparedStatement.setInt(3, gallery.getYear());
                    preparedStatement.setString(4, gallery.getStorage());
                    preparedStatement.setDouble(5, gallery.getPrice());
                    preparedStatement.setString(6, gallery.getLink());
                    preparedStatement.setInt(7, gallery.getId());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static int delete(int id) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "DELETE FROM gallery WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
}
