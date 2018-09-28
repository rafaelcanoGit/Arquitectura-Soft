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
public class Categoria {

    private int id;
    private String nombre;
    private String descripcion;
    
    private Conexion m_Conexion;

    public Categoria() {
        m_Conexion = Conexion.getInstancia();
        this.id=1;
    }

    /**
     *
     * @param id
     */
    public void eliminarCategoria(int id) {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM biblioteca.categoria WHERE biblioteca.categoria.id = ?");
            ps.setInt(1, id);
            this.id--;
            int rows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getCategorias() {
        DefaultTableModel categorias = new DefaultTableModel();
        categorias.setColumnIdentifiers(new Object[]{"id", "nombre", "descripcion"});
        Connection con = m_Conexion.getConexion();
        String sql = "SELECT \n"
                + "biblioteca.categoria.id,\n"
                + "biblioteca.categoria.nombre,\n"
                + "biblioteca.categoria.descripcion\n"
                + "FROM biblioteca.categoria";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categorias.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }

    public int insertarCategoria() {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO biblioteca.categoria(id,nombre,descripcion)\n"
                    + "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id++);
            ps.setString(2, nombre);
            ps.setString(3, descripcion);
            int rows = ps.executeUpdate();
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void modificarCategoria() {
        Connection con = m_Conexion.getConexion();
        try {
            String sql = "UPDATE biblioteca.categoria \n"
                    + "SET biblioteca.categoria.nombre = ?,\n"
                    + "biblioteca.categoria.descripcion = ?\n"
                    + "WHERE biblioteca.categoria.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param nombre
     * @param descripcion
     */
    public void setCategoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param descripcion
     */
    public void setCategoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
