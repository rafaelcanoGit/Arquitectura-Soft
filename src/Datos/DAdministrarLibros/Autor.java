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
public class Autor {

    private int id;
    private String nombre;
    private String pais_origen;

    private Conexion m_Conexion;

    public Autor() {
        m_Conexion = Conexion.getInstancia();
this.id = 1;
    }

    /**
     *
     * @param id
     */
    public void eliminarAutor(int id) {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM biblioteca.autor WHERE biblioteca.autor.id = ?");
            ps.setInt(1, id);
            this.id--;
            int rows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getAutores() {
        DefaultTableModel autores = new DefaultTableModel();
        autores.setColumnIdentifiers(new Object[]{"id", "nombre", "pais_origen"});
        Connection con = m_Conexion.getConexion();
        String sql = "SELECT \n"
                + "biblioteca.autor.id,\n"
                + "biblioteca.autor.nombre,\n"
                + "biblioteca.autor.pais_origen\n"
                + "FROM biblioteca.autor";
        
        PreparedStatement ps;
        try {
            
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                autores.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("pais_origen")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autores;
    }

    public int insertarAutor() {
        Connection con = m_Conexion.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO biblioteca.autor(id,nombre,pais_origen)\n"
                    + "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, id++);
            ps.setString(2, nombre);
            ps.setString(3, pais_origen);
            
            int rows = ps.executeUpdate();
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void modificarAutor() {
        Connection con = m_Conexion.getConexion();
        try {
            String sql = "UPDATE biblioteca.autor \n"
                    + "SET biblioteca.autor.nombre = ?,\n"
                    + "biblioteca.autor.pais_origen = ?\n"
                    + "WHERE biblioteca.autor.id = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, pais_origen);
            ps.setInt(3, id);
            
            
            int rows = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param nombre
     * @param pais_origen
     */
    public void setAutor(String nombre, String pais_origen) {
        this.nombre = nombre;
        this.pais_origen = pais_origen;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param pais_origen
     */
    public void setAutor(int id, String nombre, String pais_origen) {
        this.id = id;
        this.nombre = nombre;
        this.pais_origen = pais_origen;
    }
}
