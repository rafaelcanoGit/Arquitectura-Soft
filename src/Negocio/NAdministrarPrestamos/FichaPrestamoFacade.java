/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarPrestamos;

import Negocio.NAdministrarLibros.LibroNegocio;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class FichaPrestamoFacade {

    private BibliotecarioNegocio m_BibliotecarioNegocio;
    private LectorNegocio m_LectorNegocio;
    private LibroNegocio m_LibroNegocio;

    public FichaPrestamoFacade() {
        m_BibliotecarioNegocio = new BibliotecarioNegocio();
        m_LectorNegocio = new LectorNegocio();
        m_LibroNegocio = new LibroNegocio();
    }

    public DefaultTableModel obtenerBibliotecarios() {
        return m_BibliotecarioNegocio.obtenerBibliotecarios();
    }

    public DefaultTableModel obtenerLectores() {
        return m_LectorNegocio.obtenerLectores();
    }

    public DefaultTableModel obtenerLibros() {
        return m_LibroNegocio.obtenerLibros();
    }
}
