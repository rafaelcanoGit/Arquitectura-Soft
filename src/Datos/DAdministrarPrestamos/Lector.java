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
public class Lector {

    private int id;
    private int ci;
    private String apellidos;
    private String nombres;
    private String direccion;
    private Date fecha_nacimiento;
    private char sexo;
    private int telefono;
    private Date fecha_afiliacion;
    private int id_persona;

    private Conexion m_Conexion;

    public Lector() {
        m_Conexion = Conexion.getInstancia();
this.id = 1;
    }

    /**
     *
     * @param id
     */
    public void eliminarLector(int id) {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM biblioteca.lector WHERE biblioteca.lector.id = ?");
            ps.setInt(1, id);
            this.id--;
            int rows = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Bibliotecario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getLectores() {
        DefaultTableModel lectores = new DefaultTableModel();
        lectores.setColumnIdentifiers(new Object[]{"id", "ci", "apellidos", "nombres","fecha_nacimiento",  "direccion", "sexo", "telefono", "fecha_afiliacion"});
        Connection con = m_Conexion.getConexion();
        String sql = "SELECT \n"
                + "biblioteca.lector.id,\n"
                + "biblioteca.lector.ci,\n"
                + "biblioteca.lector.apellidos,\n"
                + "biblioteca.lector.nombres,\n"
                + "biblioteca.lector.direccion,\n"
                + "biblioteca.lector.fecha_nacimiento,\n"
                + "biblioteca.lector.sexo,\n"
                + "biblioteca.lector.telefono,\n"
                + "biblioteca.lector.fecha_afiliacion\n"
                + "FROM biblioteca.lector";

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lectores.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("ci"),
                    rs.getString("apellidos"),
                    rs.getString("nombres"),
                    rs.getString("direccion"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getString("sexo"),
                    rs.getInt("telefono"),
                    rs.getInt("fecha_afiliacion"),});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lectores;
    }

    public int insertarLector() {
        Connection con = m_Conexion.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO biblioteca.lector(id,ci,apellidos,nombres, fecha_nacimiento, direccion, sexo, telefono,fecha_afiliacion)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id++);
            ps.setInt(2, ci);
            ps.setString(3, apellidos);
            ps.setString(4, nombres);
            ps.setDate(5, fecha_nacimiento);
            ps.setString(6, direccion);
            ps.setString(7, String.valueOf(sexo));
            ps.setInt(8, telefono);
            ps.setDate(9, fecha_afiliacion);

            int rows = ps.executeUpdate();
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void modificarLector() {
        Connection con = m_Conexion.getConexion();
        try {
           
            String sql = "UPDATE biblioteca.lector \n"
                    + "SET biblioteca.lector.ci = ?,\n"
                    + "biblioteca.lector.apellidos = ?,\n"
                    + "biblioteca.lector.nombres = ?,\n"
                    + "biblioteca.lector.direccion = ?,\n"
                    + "biblioteca.lector.fecha_nacimiento = ?,\n"
                    + "biblioteca.lector.sexo = ?,\n"
                    + "biblioteca.lector.telefono = ?\n"
                    + "biblioteca.lector.fecha_afiliacion = ?\n"
                    + "WHERE biblioteca.persona.id = ?";
            
            PreparedStatement ps2 = con.prepareStatement(sql);
            ps2.setInt(1, ci);
            ps2.setString(2, apellidos);
            ps2.setString(3, nombres);
            ps2.setString(4, direccion);
            ps2.setDate(5, fecha_nacimiento);
            ps2.setString(6, String.valueOf(sexo));
            ps2.setInt(7, telefono);
            ps2.setInt(8, id_persona);
            
            int rows = ps2.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void setLector(int ci, String apellidos, String nombres, String direccion, Date fecha_nacimiento, char sexo, int telefono, Date fecha_afiliacion) {
        this.ci = ci;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.fecha_afiliacion = fecha_afiliacion;
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
    public void setLector(int id, int ci, String apellidos, String nombres, String direccion, Date fecha_nacimiento, char sexo, int telefono, Date fecha_afiliacion) {
        this.id = id;
        this.ci = ci;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.fecha_afiliacion = fecha_afiliacion;
        
    }
}
