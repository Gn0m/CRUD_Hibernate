package com.example.crud.Database;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;

@Getter
@Setter
public class Connector implements IConnection {

    private final String url;
    private final String username;
    private final String password;

    public Connector() {
        url = "jdbc:mysql://app-db/myDB";
        //url = "jdbc:mysql://localhost:3306/myDB";
        username = "root";
        password = "password";
        //password = "G2712199188312g";
    }

    public ArrayList<Picture> select() {
        Picture picture;

        ArrayList<Picture> pictureList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM picture");
                while (resultSet.next()) {
                    picture = getPicture(resultSet);
                    pictureList.add(picture);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return pictureList;
    }

    private Picture getPicture(ResultSet resultSet) throws SQLException {
        Picture picture;

        int pictureId = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String author = resultSet.getString(3);
        int year = resultSet.getInt(4);
        String storage = resultSet.getString(5);
        double price = resultSet.getInt(6);
        String link = resultSet.getString(7);
        picture = new Picture(pictureId, name, author, year, storage, price, link);
        return picture;
    }

    public Picture selectOne(int id) {
        Picture picture = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM picture WHERE id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        picture = getPicture(resultSet);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return picture;
    }

    public int insert(Picture picture) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "INSERT INTO picture (Name,Author,Year,Storage,Price,Link) Values (?,?,?,?,?,?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, picture.getName());
                    preparedStatement.setString(2, picture.getAuthor());
                    preparedStatement.setInt(3, picture.getYear());
                    preparedStatement.setString(4, picture.getStorage());
                    preparedStatement.setDouble(5, picture.getPrice());
                    preparedStatement.setString(6, picture.getLink());

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public int update(Picture picture) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "UPDATE picture SET Name = ?,Author = ?,Year = ?,Storage = ?,Price = ?," +
                        "Link = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, picture.getName());
                    preparedStatement.setString(2, picture.getAuthor());
                    preparedStatement.setInt(3, picture.getYear());
                    preparedStatement.setString(4, picture.getStorage());
                    preparedStatement.setDouble(5, picture.getPrice());
                    preparedStatement.setString(6, picture.getLink());
                    preparedStatement.setInt(7, picture.getId());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public int delete(int id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "DELETE FROM picture WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }
}
