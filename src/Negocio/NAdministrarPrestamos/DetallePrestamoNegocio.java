/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarPrestamos;

import Datos.DAdministrarPrestamos.DetallePrestamo;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class DetallePrestamoNegocio {

    private DetallePrestamo m_DetallePrestamo;

    public DetallePrestamoNegocio() {
        m_DetallePrestamo = new DetallePrestamo();
    }

    /**
     *
     * @param id_prestamo
     * @return
     */
    public DefaultTableModel obtenerDetallePrestamo(int id_prestamo) {
        return m_DetallePrestamo.getDetallePrestamo(id_prestamo);
    }

    /**
     *
     * @param id_prestamo
     * @param ids_libros
     * @param nros_ejemplares
     */
    public void registrarDetallePrestamo(int id_prestamo, LinkedList<Integer> ids_libros, LinkedList<Integer> nros_ejemplares) {
        for (int i = 0; i < ids_libros.size(); i++) {
            m_DetallePrestamo.setDetallePrestamo(id_prestamo, ids_libros.get(i), nros_ejemplares.get(i));
            m_DetallePrestamo.insertarDetallePrestamo();
        }
    }
}
