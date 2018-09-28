/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarLibros;

import Datos.DAdministrarLibros.Categoria;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class CategoriaNegocio {

    private Categoria m_Categoria;

    public CategoriaNegocio() {
        m_Categoria = new Categoria();
    }

    /**
     *
     * @param id
     */
    public int eliminarCategoria(int id) {
        m_Categoria.eliminarCategoria(id);
        return 0;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param descripcion
     */
    public void modificarCategoria(int id, String nombre, String descripcion) {
        m_Categoria.setCategoria(id, nombre, descripcion);
        m_Categoria.modificarCategoria();
    }

    public DefaultTableModel obtenerCategorias() {
        return m_Categoria.getCategorias();
    }

    /**
     *
     * @param nombre
     * @param descripcion
     */
    public int registrarCategoria(String nombre, String descripcion) {
        m_Categoria.setCategoria(nombre, descripcion);
        return m_Categoria.insertarCategoria();
    }
}
