/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.DAdministrarPrestamos;

import Datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class DetallePrestamo {

    private int id_libro;
    private int nro_ejemplar;
    private int id_prestamo;

    private Conexion m_Conexion;

    public DetallePrestamo() {
        m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param id_prestamo
     * @return
     */
    public DefaultTableModel getDetallePrestamo(int id_prestamo) {
        DefaultTableModel detalle = new DefaultTableModel();
        detalle.setColumnIdentifiers(new Object[]{"id_prestamo", "nro_ejemplar","id_libro" });
        Connection con = m_Conexion.getConexion();
        String sql = "SELECT biblioteca.detalle_prestamo.id_prestamo,\n"
                + "biblioteca.detalle_prestamo.nro_ejemplar,\n"
                + "biblioteca.detalle_prestamo.id_libro\n"
                + "FROM biblioteca.detalle_prestamo\n"
                + "WHERE biblioteca.detalle_prestamo.id_prestamo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_prestamo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                detalle.addRow(new Object[]{
                    rs.getInt("id_prestamo"),
                    rs.getInt("id_libro"),
                    rs.getInt("nro_ejemplar")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetallePrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalle;
    }

    public void insertarDetallePrestamo() {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO biblioteca.detalle_prestamo(id_prestamo,"
                    + "nro_ejemplar,id_libro)\n"
                    + "VALUES (?,?,?)");
            ps.setInt(1, id_prestamo);
            ps.setInt(2, nro_ejemplar);
            ps.setInt(3, id_libro);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DetallePrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id_prestamo
     * @param id_libro
     * @param nro_ejemplar
     */
    public void setDetallePrestamo(int id_prestamo, int id_libro, int nro_ejemplar) {
        this.id_prestamo = id_prestamo;
        this.id_libro = id_libro;
        this.nro_ejemplar = nro_ejemplar;
    }
}
