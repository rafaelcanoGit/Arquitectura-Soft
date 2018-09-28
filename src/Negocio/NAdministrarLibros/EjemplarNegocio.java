/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarLibros;

import Datos.DAdministrarLibros.Ejemplar;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class EjemplarNegocio {

    private Ejemplar m_Ejemplar;

    public EjemplarNegocio() {
        m_Ejemplar = new Ejemplar();
    }

    /**
     *
     * @param id_libro
     */
    public void eliminarEjemplares(int id_libro) {
        m_Ejemplar.eliminarEjemplares(id_libro);
    }

    /**
     *
     * @param id_libro
     * @param cant_ejemplares
     */
    public void modificarCantEjemplares(int id_libro, int cant_ejemplares) {
        int old_cantidad = m_Ejemplar.getCantEjemplares(id_libro);
        if (cant_ejemplares >= old_cantidad) {
            // Salir por que no cumple la condicion, debe ser manor la cantidad
            return;
        }
        // Eliminar los ejemplares excedentes
        for (int i = cant_ejemplares + 1; i <= old_cantidad; i++) {
            m_Ejemplar.eliminarEjemplar(id_libro, i);
        }
    }

    /**
     *
     * @param id_libros
     */
    public DefaultTableModel obtenerCantEjemplares(LinkedList<Integer> id_libros) {
        DefaultTableModel cantEjemplares = new DefaultTableModel();
        cantEjemplares.setColumnIdentifiers(new Object[]{"id_libro", "cantEjemplares"});
        for (int i = 0; i < id_libros.size(); i++) {
            cantEjemplares.addRow(new Object[]{
                id_libros.get(i),
                m_Ejemplar.getCantEjemplares(id_libros.get(i))
            });
        }
        return cantEjemplares;
    }

    /**
     *
     * @param id_libro
     * @param cant_ejemplares
     */
    public void registrarCantEjemplares(int id_libro, int cant_ejemplares) {
        m_Ejemplar.setEjemplar(id_libro, cant_ejemplares);
        for (int i = 1; i <= cant_ejemplares; i++) {
            Ejemplar ejemplar = m_Ejemplar.clonar();
            ejemplar.setNro_ejemplar(i);
            ejemplar.insertarEjemplar();
        }
    }
}
