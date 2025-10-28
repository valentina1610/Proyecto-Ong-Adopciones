package Model.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Datos de conexión
    private static final String URL = "jdbc:mariadb://localhost:3306/refugiodb";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 
    
    private static Connection conexion = null;

    // Método para obtener la conexión
    public static Connection getConnection() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión establecida correctamente.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ No se encontró el driver de MariaDB: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    // Método opcional para cerrar conexión (si la querés usar)
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("🔒 Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
        }
    }
}