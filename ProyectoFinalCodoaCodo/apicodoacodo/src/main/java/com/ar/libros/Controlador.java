package com.ar.libros;

// Importaciones necesarias para la clase Controlador
import java.io.IOException; // Importación de ObjectMapper de Jackson para convertir objetos Java a JSON y viceversa
import java.sql.Connection; // Importación de ServletException para manejar excepciones relacionadas con Servlets
import java.sql.PreparedStatement; // Importación de WebServlet para la anotación que mapea este servlet a una URL específica
import java.sql.ResultSet; // Importación de HttpServlet para extender esta clase y manejar peticiones HTTP
import java.sql.SQLException; // Importación de HttpServletRequest para manejar las solicitudes HTTP
import java.sql.Statement; // Importación de HttpServletResponse para manejar las respuestas HTTP
import java.util.ArrayList;
import java.util.List; // Importación de IOException para manejar excepciones de entrada/salida

import javax.servlet.ServletException; // Importación de todas las clases JDBC para operaciones de base de datos
import javax.servlet.annotation.WebServlet; // Importación de ArrayList para manejar listas dinámicas de objetos
import javax.servlet.http.HttpServlet; // Importación de List para manejar colecciones de elementos
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

// Clase Controlador: Maneja las peticiones HTTP para insertar y recuperar libros.
@WebServlet("/libros") // Anotación que mapea este servlet a la URL "/libros"
public class Controlador extends HttpServlet { // Declaración de la clase Controlador que extiende HttpServlet
 
    // Método POST para insertar una nueva película desde una solicitud JSON
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configurar cabeceras CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir acceso desde cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "*"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Cabeceras permitidas
        Conexion conexion = new Conexion();  // Crear una nueva conexión a la base de datos
        Connection conn = conexion.getConnection();  // Obtener la conexión establecida

        try {
            ObjectMapper mapper = new ObjectMapper();  // Crear un objeto ObjectMapper para convertir JSON a objetos Java
            Libro libro = mapper.readValue(request.getInputStream(), Libro.class);  // Convertir el JSON de la solicitud a un objeto Libro
        
            // Consulta SQL para insertar un nuevo libro en la tabla liros_rincondeloslibros'
            String query = "INSERT INTO libros (titulo, autor, genero) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  // Indicar que queremos obtener las claves generadas automáticamente
        
            // Establecer los parámetros de la consulta de inserción
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getGenero());
            
           
        
            statement.executeUpdate();  // Ejecutar la consulta de inserción en la base de datos
        
            // Obtener las claves generadas automáticamente (en este caso, el ID del libro)
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Long idLibr = rs.getLong(1);  // Obtener el valor del primer campo generado automáticamente (en este caso, el ID)
                
                // Devolver el ID del libro insertado como JSON en la respuesta
                response.setContentType("application/json");  // Establecer el tipo de contenido de la respuesta como JSON
                String json = mapper.writeValueAsString(idLibr);  // Convertir el ID a formato JSON
                response.getWriter().write(json);  // Escribir el JSON en el cuerpo de la respuesta HTTP
            }
            
            response.setStatus(HttpServletResponse.SC_CREATED);  // Configurar el código de estado de la respuesta HTTP como 201 (CREATED)
        } catch (SQLException e) {
            e.printStackTrace();  // Imprimir el error en caso de problemas con la base de datos
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // Configurar el código de estado de la respuesta HTTP como 500 (INTERNAL_SERVER_ERROR)
        } catch (IOException e) {
            e.printStackTrace();  // Imprimir el error en caso de problemas de entrada/salida (por ejemplo, problemas con la solicitud o respuesta HTTP)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // Configurar el código de estado de la respuesta HTTP como 500 (INTERNAL_SERVER_ERROR)
        } finally {
            conexion.close();  // Cerrar la conexión a la base de datos al finalizar la operación
        }
        
    }

    // Método GET para obtener todas las películas almacenadas en la base de datos y devolverlas como JSON
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configurar cabeceras CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permitir acceso desde cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "*"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Cabeceras permitidas
        Conexion conexion = new Conexion();  // Crear una nueva conexión a la base de datos
        Connection conn = conexion.getConnection();  // Obtener la conexión establecida

        try {
            // Consulta SQL para seleccionar todas las películas de la tabla 'libros_rincondeloslibros'
            String query = "SELECT * FROM libros_rincondeloslibros.libros";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);  // Ejecutar la consulta y obtener los resultados

            List<Libro> libros = new ArrayList<>();  // Crear una lista para almacenar objetos Libro

            // Iterar sobre cada fila de resultados en el ResultSet
            while (resultSet.next()) {
                // Crear un objeto Libro con los datos de cada fila
                Libro libro = new Libro(
                    resultSet.getInt("id"),
                    resultSet.getString("titulo"),  
                    resultSet.getString("autor"),
                    resultSet.getString("genero")
                    
                );
                libros.add(libro);  // Agregar el objeto Libro a la lista
            }

            ObjectMapper mapper = new ObjectMapper();  // Crear un objeto ObjectMapper para convertir objetos Java a JSON
            String json = mapper.writeValueAsString(libros);  // Convertir la lista de películas a formato JSON

            response.setContentType("application/json");  // Establecer el tipo de contenido de la respuesta como JSON
            response.getWriter().write(json);  // Escribir el JSON en el cuerpo de la respuesta HTTP
        } catch (SQLException e) {
            e.printStackTrace();  // Imprimir el error en caso de problemas con la base de datos
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // Configurar el código de estado de la respuesta HTTP como 500 (INTERNAL_SERVER_ERROR)
        } finally {
            conexion.close();  // Cerrar la conexión a la base de datos al finalizar la operación
        }
    }
}
