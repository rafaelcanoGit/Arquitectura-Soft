/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarLibros;

import Datos.DAdministrarLibros.Editorial;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class EditorialNegocio {

    private Editorial m_Editorial;

    public EditorialNegocio() {
        m_Editorial = new Editorial();
    }

    /**
     *
     * @param id
     */
    public void eliminarEditorial(int id) {
        m_Editorial.eliminarEditorial(id);
    }

    /**
     *
     * @param id
     * @param nombre
     * @param direccion
     * @param telefono
     */
    public void modificarEditorial(int id, String nombre, String direccion, int telefono) {
        m_Editorial.setEditorial(id, nombre, direccion, telefono);
        m_Editorial.modificarEditorial();
    }

    public DefaultTableModel obtenerEditoriales() {
        return m_Editorial.getEditoriales();
    }

    /**
     *
     * @param nombre
     * @param direccion
     * @param telefono
     */
    public int registrarEditorial(String nombre, String direccion, int telefono) {
        m_Editorial.setEditorial(nombre, direccion, telefono);
        return m_Editorial.insertarEditorial();
    }
}
