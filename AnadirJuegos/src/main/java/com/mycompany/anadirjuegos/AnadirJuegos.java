/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.anadirjuegos;
import java.sql.*;
import java.util.Scanner;

public class AnadirJuegos {
    //declaramos la url, el usuario, la contraseña para conectarnos a la base de datos
    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "dam2";
    static final String PASS = "1234";

    public static void main(String[] args) {
        //creamos el metodo scanner para que el usuario introduzca los datos que quiera guardar
        Scanner scanner = new Scanner(System.in);
        //le pide al usuario que introduzca los datos del videojuego que quiere guardar
        //nombre, genero, fecha, compañia y precio
        System.out.println("Introduce el nombre del videojuego:");
        String nombre = scanner.nextLine();

        System.out.println("Introduce el género del videojuego:");
        String genero = scanner.nextLine();

        System.out.println("Introduce la fecha de lanzamiento (YYYY-MM-DD):");
        String fechaLanzamiento = scanner.nextLine();

        System.out.println("Introduce la compañía del videojuego:");
        String compañia = scanner.nextLine();

        System.out.println("Introduce el precio del videojuego:");
        float precio = scanner.nextFloat();
        //llama el metodo addvideojuego con los datos introducidos por el usuario
        addVideojuego(nombre, genero, fechaLanzamiento, compañia, precio);
        //cierra la clase scanner
        scanner.close();
    }

    public static void addVideojuego(String nombre, String genero, String fechaLanzamiento, String compañia, float precio) {
        //prepara una consulta sql para luego recoger los datos 
        String insertQuery = "INSERT INTO videojuegos (Nombre, Género, FechaLanzamiento, Compañía, Precio) "
                + "VALUES (?, ?, ?, ?, ?)";
        //abre una conexion a la base de datos con los datos introducidos anteriormente
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             //crea un preparedstatement para poder usarlo luego   
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            //asigna valores a los parametros del prepared statement de la consulta
            pstmt.setString(1, nombre);
            pstmt.setString(2, genero);
            pstmt.setDate(3, java.sql.Date.valueOf(fechaLanzamiento));
            pstmt.setString(4, compañia);
            pstmt.setFloat(5, precio);
            //ejecuta la consulta con los datos anteriores
            int affectedRows = pstmt.executeUpdate();
            //comprueba si se agrego el videojuego y muestra un mensaje de exito
            if (affectedRows > 0) {
                System.out.println("Un nuevo videojuego ha sido añadido con éxito.");
            }
        //captura la excepcion sql y la maneja y muestra la lista     
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            
        }
    }
}
