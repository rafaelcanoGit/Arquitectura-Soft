/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarLibros;

import Datos.DAdministrarLibros.Libro;
import java.sql.Date;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class LibroNegocio {

    private EjemplarNegocio m_EjemplarNegocio;
    private Libro m_Libro;

    public LibroNegocio() {
        m_EjemplarNegocio = new EjemplarNegocio();
        m_Libro = new Libro();
    }

    /**
     *
     * @param id
     */
    public void eliminarLibro(int id) {
        m_Libro.eliminarLibro(id);
        m_EjemplarNegocio.eliminarEjemplares(id);
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
     
     */
    public void modificarLibro(int id, String titulo, String isbn, String descripcion, int paginas, int edicion, Date fecha_lanzamiento, String idioma, int nro_ejemplares, int id_categoria, int id_editorial, int id_autor) {
        m_Libro.setLibro(id, titulo, isbn, descripcion, paginas, edicion, fecha_lanzamiento, idioma, nro_ejemplares, id_categoria, id_editorial, id_autor);
        m_Libro.modificarLibro();
        m_EjemplarNegocio.modificarCantEjemplares(id, nro_ejemplares);
    }

    public DefaultTableModel obtenerLibros() {
        DefaultTableModel libros = m_Libro.getLibros();
        LinkedList<Integer> id_libros = new LinkedList<>();
        for (int i = 0; i < libros.getRowCount(); i++) {
            id_libros.add((Integer) libros.getValueAt(i, 0));
        }
        DefaultTableModel cantEjemplares = m_EjemplarNegocio.obtenerCantEjemplares(id_libros);
        int columnaEjemplares = 8;
        int columnaCantEjemplares = 1;
        for (int i = 0; i < libros.getRowCount(); i++) {
            libros.setValueAt(cantEjemplares.getValueAt(i, columnaCantEjemplares), i, columnaEjemplares);
        }
        return libros;
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
   
     * @return
     */
    public int registrarLibro(String titulo, String isbn, String descripcion, int paginas, int edicion, Date fecha_lanzamiento, String idioma, int nro_ejemplares, int id_categoria, int id_editorial,int id_autor) {
        m_Libro.setLibro(titulo, isbn, descripcion, paginas, edicion, fecha_lanzamiento, idioma, nro_ejemplares, id_categoria, id_editorial, id_autor);
        int id = m_Libro.insertarLibro();
        m_EjemplarNegocio.registrarCantEjemplares(id, nro_ejemplares);
        return id;
    }
}
