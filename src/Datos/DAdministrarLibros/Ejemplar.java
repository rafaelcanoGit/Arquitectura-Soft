/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.DAdministrarLibros;

import Datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class Ejemplar implements Prototype {

    private int id_libro;
    private int nro_ejemplar;

    private Conexion m_Conexion;

    public Ejemplar() {
        m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param id_libro
     * @param nro_ejemplar
     */
    public void eliminarEjemplar(int id_libro, int nro_ejemplar) {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM biblioteca.ejemplar\n"
                    + "WHERE biblioteca.ejemplar.id_libro = ?\n"
                    + "AND biblioteca.ejemplar.nro_ejemplar = ?");
            ps.setInt(1, id_libro);
            ps.setInt(2, nro_ejemplar);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id_libro
     * @return 
     */
    public int eliminarEjemplares(int id_libro) {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM biblioteca.ejemplar WHERE biblioteca.ejemplar.id_libro = ?");
            ps.setInt(1, id_libro);
            this.nro_ejemplar--;
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     *
     * @param id_libro
     */
    public int getCantEjemplares(int id_libro) {
        String sql = "SELECT count(*) AS `cantEjemplares`\n"
                + "FROM biblioteca.ejemplar\n"
                + "WHERE biblioteca.ejemplar.id_libro = ?";
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_libro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("cantEjemplares");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     *
     * @param id_libro
     */
    public DefaultTableModel getEjemplares(int id_libro) {
        return null;
    }

    public void insertarEjemplar() {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO biblioteca.ejemplar(id_libro, nro_ejemplar)\n"
                    + "VALUES (?,?)");
            ps.setInt(1, id_libro);
            ps.setInt(2, nro_ejemplar);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarEjemplar() {

    }

    /**
     *
     * @param id_libro
     * @param nro_ejemplar
     */
    public void setEjemplar(int id_libro, int nro_ejemplar) {
        this.id_libro = id_libro;
        this.nro_ejemplar = nro_ejemplar;
    }

    public void setNro_ejemplar(int nro_ejemplar) {
        this.nro_ejemplar = nro_ejemplar;
    }

    @Override
    public Ejemplar clonar() {
        Ejemplar copy = new Ejemplar();
        copy.setEjemplar(this.id_libro, this.nro_ejemplar);
        return copy;
    }
}
