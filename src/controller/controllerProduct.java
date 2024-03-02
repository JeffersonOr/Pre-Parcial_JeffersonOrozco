/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.sql.ResultSet;
import connetion.ConnectionMySQL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class controllerProduct {

    private final ConnectionMySQL conection;

    public controllerProduct() throws SQLException {
        this.conection = new ConnectionMySQL();
    }

    public void createProducts(int id, String nombre, String descripcion, String category, double price) throws SQLException {
        String createSQL = "INSERT INTO producto(nombre, descripcion, category, price ) VALUES( ?, ?, ?, ?)";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(createSQL)) {
            
            statement.setString(1, nombre);
            statement.setString(2, descripcion);
            statement.setString(3, category);
            statement.setDouble(4, price);
           

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insercion exitosa");
            } else {
                System.out.println("No se pudo insertar los datos");

            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al realizar la insercion en la base de datos");
            e.printStackTrace();
        }
    }

    public void readProducts(int id) throws  SQLException {
        String readSQL = "SELECT * FROM producto WHERE id = ?";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("price"));
                System.out.println(rs.getString("descripcion"));
                System.out.println(rs.getString("category"));
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }

    }

}
