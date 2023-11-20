/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.anadirjuegos;
import java.sql.*;
import java.util.Scanner;

public class AnadirJuegos {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "dam2";
    static final String PASS = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        addVideojuego(nombre, genero, fechaLanzamiento, compañia, precio);

        scanner.close();
    }

    public static void addVideojuego(String nombre, String genero, String fechaLanzamiento, String compañia, float precio) {
        String insertQuery = "INSERT INTO videojuegos (Nombre, Género, FechaLanzamiento, Compañía, Precio) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            
            pstmt.setString(1, nombre);
            pstmt.setString(2, genero);
            pstmt.setDate(3, java.sql.Date.valueOf(fechaLanzamiento));
            pstmt.setString(4, compañia);
            pstmt.setFloat(5, precio);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Un nuevo videojuego ha sido añadido con éxito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
