/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.NAdministrarPrestamos;

import Datos.DAdministrarPrestamos.Bibliotecario;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class BibliotecarioNegocio {

    private Bibliotecario m_Bibliotecario;
    public  int id = 1;
    
    public BibliotecarioNegocio() {
        m_Bibliotecario = new Bibliotecario();
        
    }

    /**
     *
     * @param id
     */
 
            
    
    public void eliminarBibliotecario(int id) {
        m_Bibliotecario.eliminarBibliotecario(id);
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
     * @param ano_contratacion
     
     */
    public void modificarBibliotecario(int id, int ci, String apellidos, String nombres, String direccion, Date fecha_nacimiento, char sexo, int telefono, int ano_contratacion) {
        m_Bibliotecario.setBibliotecario(id, ci, apellidos, nombres, direccion, fecha_nacimiento, sexo, telefono, ano_contratacion);
        m_Bibliotecario.modificarBibliotecario();
    }

    public DefaultTableModel obtenerBibliotecarios() {
        return m_Bibliotecario.getBibliotecarios();
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
     * @param ano_contratacion
     */
    public int registrarBibliotecario( int ci, String apellidos, String nombres, String direccion, Date fecha_nacimiento, char sexo, int telefono, int ano_contratacion) {
        m_Bibliotecario.setBibliotecario(ci, apellidos, nombres, direccion, fecha_nacimiento, sexo, telefono, ano_contratacion);
        return m_Bibliotecario.insertarBibliotecario();
    }
}
