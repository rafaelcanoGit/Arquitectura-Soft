/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarPrestamos;

import Datos.DAdministrarPrestamos.Lector;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class LectorNegocio {

    private Lector m_Lector;
    public  int id = 1  ;

    public LectorNegocio() {
        m_Lector = new Lector();
        
    }

    /**
     *
     * @param id
     */
    public void eliminarLector(int id) {
        m_Lector.eliminarLector(id);
    }

    /**
     *
     * @param id
     * @param ci
     * @param apellidos
     * @param nombres
     * @param direccion
     * @param fecha_nacimiento
     * @param sexo
     * @param telefono
     * @param fecha_afiliacion
     
     */
    public void modificarLector(int id, int ci, String apellidos, String nombres, String direccion, Date fecha_nacimiento, char sexo, int telefono, Date fecha_afiliacion) {
        m_Lector.setLector(id, ci, apellidos, nombres, direccion, fecha_nacimiento, sexo, telefono, fecha_afiliacion);
        m_Lector.modificarLector();
    }

    public DefaultTableModel obtenerLectores() {
        return m_Lector.getLectores();
    }

    /**
     *
     * @param ci
     * @param apellidos
     * @param nombres
     * @param direccion
     * @param fecha_nacimiento
     * @param sexo
     * @param telefono
     * @param fecha_afiliacion
     */
    public int registrarLector(int ci, String apellidos, String nombres, String direccion, Date fecha_nacimiento, char sexo, int telefono, Date fecha_afiliacion) {
        m_Lector.setLector(ci, apellidos, nombres, direccion, fecha_nacimiento, sexo, telefono, fecha_afiliacion);
        return m_Lector.insertarLector();
    }
}
