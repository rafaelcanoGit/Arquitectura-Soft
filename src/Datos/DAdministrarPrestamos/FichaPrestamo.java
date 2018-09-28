/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos.DAdministrarPrestamos;

import Datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
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
public class FichaPrestamo {

    private int id;
    private Date fechaDevolucion;
    private Date fechaPrestamo;
    private boolean devuelto;
    private int idBibliotecario;
    private int idLector;

    private Conexion m_Conexion;

    public FichaPrestamo() {
        m_Conexion = Conexion.getInstancia();
        this.id=1;
    }

    /**
     *
     * @param idPrestamo
     */
    public void concretarDevolucion(int idPrestamo) {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE biblioteca.ficha_prestamo\n"
                    + "SET biblioteca.ficha_prestamo.devuelto = ?\n"
                    + "WHERE biblioteca.ficha_prestamo.id = ?");
            ps.setBoolean(1, true);
            ps.setInt(2, idPrestamo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FichaPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getPrestamos() {
        DefaultTableModel prestamos = new DefaultTableModel();
        prestamos.setColumnIdentifiers(new Object[]{"id", "fecha_prestamo", "fecha_devolucion", "devuelto", "id_lector", "id_bibliotecario"});
        Connection con = m_Conexion.getConexion();
        String sql = "SELECT \n"
                + "biblioteca.ficha_prestamo.id,\n"
                + "biblioteca.ficha_prestamo.fecha_prestamo,\n"
                + "biblioteca.ficha_prestamo.fecha_devolucion,\n"
                + "biblioteca.ficha_prestamo.devuelto,\n"
                + "biblioteca.ficha_prestamo.id_lector,\n"
                + "biblioteca.ficha_prestamo.id_bibliotecario\n"
                + "FROM biblioteca.ficha_prestamo\n"
                + "WHERE biblioteca.ficha_prestamo.devuelto = 0";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                prestamos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("fecha_prestamo"),
                    rs.getDate("fecha_devolucion"),
                    rs.getBoolean("devuelto"),
                    rs.getString("id_lector"),
                    rs.getString("id_bibliotecario")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(FichaPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prestamos;
    }

    public int registrarPrestamo() {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO biblioteca.ficha_prestamo(id,fecha_prestamo,"
                    + "fecha_devolucion,devuelto,id_lector,id_bibliotecario)\n"
                    + "VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, id++ );
            ps.setDate(2, fechaPrestamo);
            ps.setDate(3, fechaDevolucion);
            ps.setBoolean(4, false);
            ps.setInt(5, idLector);
            ps.setInt(6, idBibliotecario);
            int rows = ps.executeUpdate();
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FichaPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     *
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param idLector
     * @param idBibliotecario
     */
    public void setPrestamo(Date fechaPrestamo, Date fechaDevolucion, int idLector, int idBibliotecario) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.idLector = idLector;
        this.idBibliotecario = idBibliotecario;
    }
}
