package com.ar.libros;

public class Libro {
// Atributos de la clase Libro que representan las columnas de la tabla
private int idLibro;  // ID autoincremental del libro(int)
private String titulo; // Título del libro (varchar)
private String autor;   // Autor del libro (varchar)
private String genero; // Genero del libro(varchar)


// Constructor vacío necesario para deserialización de JSON
public Libro() {}

// Constructor con parámetros para inicializar todos los atributos
public Libro(int idLibro,String titulo, String autor,String genero) {
    this.idLibro = idLibro;
    this.titulo = titulo;
    this.autor = autor;
    this.genero = genero;
   
}

// Método getter para obtener el ID del libro
public int getIdLibro() {
    return idLibro;
}

// Método setter para establecer el ID del libro
public void setIdLibro(int idLibro) {
    this.idLibro = idLibro;
}

// Método getter para obtener el título del libro
public String getTitulo() {
    return titulo;
}

// Método setter para establecer el título del libro
public void setTitulo(String titulo) {
    this.titulo = titulo;
}
// Método getter para establecer el autor del libro
public String getAutor() {
    return autor;
}
// Método setter para establecer el autor del libro
public void setAutor(String autor) {
    this.autor = autor;
}
// Método getter para obtener el género del libro
public String getGenero() {
    return genero;
}

// Método setter para establecer el género del libro
public void setGenero(String genero) {
    this.genero = genero;
}


}
