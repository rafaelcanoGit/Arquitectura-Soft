/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarLibros;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class LibroFacade {

    private EditorialNegocio m_EditorialNegocio;
    private AutorNegocio m_AutorNegocio;
    private CategoriaNegocio m_CategoriaNegocio;

    public LibroFacade() {
        m_EditorialNegocio = new EditorialNegocio();
        m_AutorNegocio = new AutorNegocio();
        m_CategoriaNegocio = new CategoriaNegocio();
    }

    public DefaultTableModel obtenerAutores() {
        return m_AutorNegocio.obtenerAutores();
    }

    public DefaultTableModel obtenerCategorias() {
        return m_CategoriaNegocio.obtenerCategorias();
    }

    public DefaultTableModel obtenerEditoriales() {
        return m_EditorialNegocio.obtenerEditoriales();
    }
}
