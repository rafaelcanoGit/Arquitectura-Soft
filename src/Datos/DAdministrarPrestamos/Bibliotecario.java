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
public class Bibliotecario {

    private int id;
    private int ci;
    private String apellidos;
    private String nombres;
    private String direccion;
    private Date fecha_nacimiento;
    private char sexo;
    private int telefono;
    private int ano_contratacion;
    

    private Conexion m_Conexion;

    public Bibliotecario() {

        m_Conexion = Conexion.getInstancia();
        this.id = 1;
    }

    /**
     *
     * @param id
     */
    public void eliminarBibliotecario(int id) {
        Connection con = m_Conexion.getConexion();
        try {
    
            PreparedStatement ps2 = con.prepareStatement("DELETE FROM biblioteca.bibliotecario WHERE biblioteca.bibliotecario.id = ?");
            ps2.setInt(1, id);
            this.id--;
            int rows = ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bibliotecario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getBibliotecarios() {
        DefaultTableModel bibliotecarios = new DefaultTableModel();
        bibliotecarios.setColumnIdentifiers(new Object[]{"id", "ci", "apellidos", "nombres", "direccion", "fecha_nacimiento", "sexo", "telefono", "ano_contratacion"});
        Connection con = m_Conexion.getConexion();
        String sql = "SELECT \n"
                + "biblioteca.bibliotecario.id,\n"
                + "biblioteca.bibliotecario.ci,\n"
                + "biblioteca.bibliotecario.apellidos,\n"
                + "biblioteca.bibliotecario.nombres,\n"
                + "biblioteca.bibliotecario.direccion,\n"
                + "biblioteca.bibliotecario.fecha_nacimiento,\n"
                + "biblioteca.bibliotecario.sexo,\n"
                + "biblioteca.bibliotecario.telefono,\n"
                + "biblioteca.bibliotecario.ano_contratacion\n"

                + "FROM biblioteca.bibliotecario";
        
        
        PreparedStatement ps;
        try {
            
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                bibliotecarios.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("ci"),
                    rs.getString("apellidos"),
                    rs.getString("nombres"),
                    rs.getString("direccion"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getString("sexo"),
                    rs.getInt("telefono"),
                    rs.getInt("ano_contratacion"),
                   
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bibliotecario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bibliotecarios;
    }

    public int insertarBibliotecario() {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO biblioteca.bibliotecario(id,ci,apellidos,nombres, fecha_nacimiento, direccion, sexo, telefono, ano_contratacion)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, id++);
            ps.setInt(2, ci);
            ps.setString(3, apellidos);
            ps.setString(4, nombres);
            ps.setDate(5, fecha_nacimiento);
            ps.setString(6, direccion);
            ps.setString(7, String.valueOf(sexo));
            ps.setInt(8, telefono);
            ps.setInt(9, ano_contratacion );
            
            int rows = ps.executeUpdate();
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bibliotecario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void modificarBibliotecario() {
        Connection con = m_Conexion.getConexion();
        try {
         
            String sql = "UPDATE biblioteca.bibliotecario \n"
                    + "SET biblioteca.bibliotecario.ci = ?,\n"
                    + "biblioteca.bibliotecario.apellidos = ?,\n"
                    + "biblioteca.bibliotecario.nombres = ?,\n"
                    + "biblioteca.bibliotecario.direccion = ?,\n"
                    + "biblioteca.bibliotecario.fecha_nacimiento = ?,\n"
                    + "biblioteca.bibliotecario.sexo = ?,\n"
                    + "biblioteca.bibliotecario.telefono = ?\n"
                    + "biblioteca.bibliotecario.ano_contratacion = ?\n"
                    + "WHERE biblioteca.bibliotecario.id = ?";
            
            PreparedStatement ps2 = con.prepareStatement(sql);
            ps2.setInt(1, ci);
            ps2.setString(2, apellidos);
            ps2.setString(3, nombres);
            ps2.setString(4, direccion);
            ps2.setDate(5, fecha_nacimiento);
            ps2.setString(6, String.valueOf(sexo));
            ps2.setInt(7, telefono);
            ps2.setInt(8, ano_contratacion );
            
            int rows = ps2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Bibliotecario.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param ano_contratacion
     */
    public void setBibliotecario(int ci, String apellidos, String nombres, String direccion, Date fecha_nacimiento, char sexo, int telefono, int ano_contratacion) {
        this.ci = ci;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.ano_contratacion = ano_contratacion;
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
     *
     */
    public void setBibliotecario(int id, int ci, String apellidos, String nombres, String direccion, Date fecha_nacimiento, char sexo, int telefono, int ano_contratacion) {
        this.id = id;
        this.ci = ci;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.ano_contratacion = ano_contratacion;
     
    }
}
