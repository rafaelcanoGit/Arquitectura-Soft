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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class Editorial {

    private int id;
    private String nombre;
    private String direccion;
    private int telefono;

    private Conexion m_Conexion;

    public Editorial() {
        m_Conexion = Conexion.getInstancia();
        this.id=1;
    }

    /**
     *
     * @param id
     */
    public void eliminarEditorial(int id) {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM biblioteca.editorial WHERE biblioteca.editorial.id = ?");
            ps.setInt(1, id);
            this.id--;
            int rows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Editorial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getEditoriales() {
        DefaultTableModel editoriales = new DefaultTableModel();
        editoriales.setColumnIdentifiers(new Object[]{"id", "nombre", "telefono", "direccion"});
        Connection con = m_Conexion.getConexion();
        String sql = "SELECT \n"
                + "biblioteca.editorial.id,\n"
                + "biblioteca.editorial.nombre,\n"
                + "biblioteca.editorial.telefono,\n"
                + "biblioteca.editorial.direccion\n"
                + "FROM biblioteca.editorial";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                editoriales.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("telefono"),
                    rs.getString("direccion")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(Editorial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editoriales;
    }

    public int insertarEditorial() {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO biblioteca.editorial(id,nombre,direccion,telefono)\n"
                    + "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id++);
            ps.setString(2, nombre);
            ps.setString(3, direccion);
            ps.setInt(4, telefono);
            int rows = ps.executeUpdate();
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Editorial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void modificarEditorial() {
        Connection con = m_Conexion.getConexion();
        try {
            String sql = "UPDATE biblioteca.editorial \n"
                    + "SET biblioteca.editorial.nombre = ?,\n"
                    + "biblioteca.editorial.direccion = ?,\n"
                    + "biblioteca.editorial.telefono = ?\n"
                    + "WHERE biblioteca.editorial.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setInt(3, telefono);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Editorial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param nombre
     * @param direccion
     * @param telefono
     */
    public void setEditorial(String nombre, String direccion, int telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param direccion
     * @param telefono
     */
    public void setEditorial(int id, String nombre, String direccion, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
