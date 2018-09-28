/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarLibros;

import Datos.DAdministrarLibros.Autor;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class AutorNegocio {
    
    private Autor m_Autor;
    
    public AutorNegocio() {
        m_Autor = new Autor();
    }

    /**
     *
     * @param id
     */
    public void eliminarAutor(int id) {
        m_Autor.eliminarAutor(id);
    }

    /**
     *
     * @param id
     * @param nombre
     * @param pais_origen
     */
    public void modificarAutor(int id, String nombre, String pais_origen) {
        m_Autor.setAutor(id, nombre, pais_origen);
        m_Autor.modificarAutor();
    }
    
    public DefaultTableModel obtenerAutores() {
        return m_Autor.getAutores();
    }

    /**
     *
     * @param nombre
     * @param pais_origen
     */
    public int registrarAutor(String nombre, String pais_origen) {
        m_Autor.setAutor(nombre, pais_origen);
        return m_Autor.insertarAutor();
    }
}
