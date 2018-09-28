/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.DAdministrarLibros;

import Datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class Libro {

    private int id;
    private String titulo;
    private String isbn;
    private String descripcion;
    private int paginas;
    private Date fecha_lanzamiento;
    private String idioma;
    private int edicion;
    private int nro_ejemplares;
    private int id_categoria;
    private int id_editorial;
    private int id_autor;

    private Conexion m_Conexion;

    public Libro() {
        m_Conexion = Conexion.getInstancia();
        this.id = 1;
    }

    /**
     *
     * @param id
     */
    public void eliminarLibro(int id) {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM biblioteca.ejemplar WHERE biblioteca.ejemplar.id_libro = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement("DELETE FROM biblioteca.libro WHERE biblioteca.libro.id = ?");
            ps2.setInt(1, id);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getLibros() {
        DefaultTableModel libros = new DefaultTableModel();
        libros.setColumnIdentifiers(new Object[]{"id", "titulo", "isbn", "descripcion", "paginas", "fecha_lanzamiento", "idioma", "edicion", "nro_ejemplares", "id_categoria", "id_editorial", "ids_autores"});
        Connection con = m_Conexion.getConexion();
        String sql = "SELECT \n"
                + "biblioteca.libro.id,\n"
                + "biblioteca.libro.titulo,\n"
                + "biblioteca.libro.isbn,\n"
                + "biblioteca.libro.descripcion,\n"
                + "biblioteca.libro.paginas,\n"
                + "biblioteca.libro.fecha_lanzamiento,\n"
                + "biblioteca.libro.idioma,\n"
                + "biblioteca.libro.edicion,\n"
                + "biblioteca.libro.id_categoria,\n"
                + "biblioteca.libro.id_editorial,\n"
                + "biblioteca.libro.id_autor\n"
                
                + "FROM biblioteca.libro";
        
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                libros.addRow(new Object[]{
                    id,
                    rs.getString("titulo"),
                    rs.getString("isbn"),
                    rs.getString("descripcion"),
                    rs.getInt("paginas"),
                    rs.getDate("fecha_lanzamiento"),
                    rs.getString("idioma"),
                    rs.getInt("edicion"),
                    0,
                    rs.getInt("id_categoria"),
                    rs.getInt("id_editorial"),
                    rs.getInt("id_autor"),
                    
                });
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return libros;
    }

    public int insertarLibro() {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO biblioteca.libro(" + "id, titulo, isbn, descripcion, paginas, fecha_lanzamiento, idioma, edicion,nro_ejemplares, id_categoria, id_editorial, id_autor)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id++);
            ps.setString(2, titulo);
            ps.setString(3, isbn);
            ps.setString(4, descripcion);
            ps.setInt(5, paginas);
            ps.setDate(6, fecha_lanzamiento);
            ps.setString(7, idioma);
            ps.setInt(8, edicion);
            ps.setInt(9, nro_ejemplares);
            ps.setInt(10, id_categoria);
            ps.setInt(11, id_editorial);
            ps.setInt(12, id_autor);

            int rows = ps.executeUpdate();
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void modificarLibro() {
        Connection con = m_Conexion.getConexion();
        try {
            String sql = "UPDATE biblioteca.libro \n"
                    + "SET biblioteca.libro.titulo = ?, \n"
                    + "biblioteca.libro.isbn = ?, \n"
                    + "biblioteca.libro.descripcion = ?, \n"
                    + "biblioteca.libro.paginas = ?, \n"
                    + "biblioteca.libro.fecha_lanzamiento = ?, \n"
                    + "biblioteca.libro.idioma = ?, \n"
                    + "biblioteca.libro.edicion = ?, \n"
                    + "biblioteca.libro.id_categoria = ?, \n"
                    + "biblioteca.libro.id_editorial = ?\n"
                    + "WHERE biblioteca.libro.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, isbn);
            ps.setString(3, descripcion);
            ps.setInt(4, paginas);
            ps.setDate(5, fecha_lanzamiento);
            ps.setString(6, idioma);
            ps.setInt(7, edicion);
            ps.setInt(8, id_categoria);
            ps.setInt(9, id_editorial);
            ps.setInt(10, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param titulo
     * @param isbn
     * @param descripcion
     * @param paginas
     * @param edicion
     * @param fecha_lanzamiento
     * @param idioma
     * @param nro_ejemplares
     * @param id_categoria
     * @param id_editorial
     *
     */
    public void setLibro(String titulo, String isbn, String descripcion, int paginas, int edicion, Date fecha_lanzamiento, String idioma, int nro_ejemplares, int id_categoria, int id_editorial, int id_autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.descripcion = descripcion;
        this.paginas = paginas;
        this.edicion = edicion;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.idioma = idioma;
        this.nro_ejemplares = nro_ejemplares;
        this.id_categoria = id_categoria;
        this.id_editorial = id_editorial;
        this.id_autor = id_autor;

    }

    /**
     *
     * @param id
     * @param titulo
     * @param isbn
     * @param descripcion
     * @param paginas
     * @param edicion
     * @param fecha_lanzamiento
     * @param idioma
     * @param nro_ejemplares
     * @param id_categoria
     * @param id_editorial
     *
     */
    public void setLibro(int id, String titulo, String isbn, String descripcion, int paginas, int edicion, Date fecha_lanzamiento, String idioma, int nro_ejemplares, int id_categoria, int id_editorial, int id_autor) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.descripcion = descripcion;
        this.paginas = paginas;
        this.edicion = edicion;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.idioma = idioma;
        this.nro_ejemplares = nro_ejemplares;
        this.id_categoria = id_categoria;
        this.id_editorial = id_editorial;
        this.id_autor = id_autor;
    }
}
