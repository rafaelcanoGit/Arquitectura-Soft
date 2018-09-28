/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.PAdministrarPrestamos;

import Negocio.NAdministrarPrestamos.FichaPrestamoFacade;
import Negocio.NAdministrarPrestamos.FichaPrestamoNegocio;
import java.sql.Date;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class FichaPrestamoFrm extends javax.swing.JFrame {

    /**
     * Creates new form FichaPrestamoFrm
     */
    public FichaPrestamoNegocio m_FichaPrestamoNegocio;
    public FichaPrestamoFacade m_FichaPrestamoFacade;
    
    public FichaPrestamoFrm() {
        initComponents();
        this.setTitle("Gestionar Ficha de Prestamo");
        this.setLocationRelativeTo(null);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id", "Libro", "NroEjemplar"});
        tableDetallePrestamos.setModel(model);
        m_FichaPrestamoNegocio = new FichaPrestamoNegocio();
        m_FichaPrestamoFacade = new FichaPrestamoFacade();
        this.inicializar();
    }
    
    public void cargarComboEjemplares() {
        int len = 8;
        Object v[] = new Object[len];
        for (int i = 0; i < len; i++) {
            v[i] = i + 1;
        }
        comboEjemplar.setModel(new DefaultComboBoxModel(v));
    }
    
    public void cargarTablaBibliotecario() {
        DefaultTableModel bibiliotecarios = m_FichaPrestamoFacade.obtenerBibliotecarios();
        tableBibliotecario.setModel(bibiliotecarios);
        tableBibliotecario.removeColumn(tableBibliotecario.getColumn("ci"));
        tableBibliotecario.removeColumn(tableBibliotecario.getColumn("direccion"));
        tableBibliotecario.removeColumn(tableBibliotecario.getColumn("fecha_nacimiento"));
        tableBibliotecario.removeColumn(tableBibliotecario.getColumn("sexo"));
        tableBibliotecario.removeColumn(tableBibliotecario.getColumn("telefono"));
        tableBibliotecario.removeColumn(tableBibliotecario.getColumn("ano_contratacion"));
       
    }
    
    public void cargarTablaLector() {
        DefaultTableModel lectores = m_FichaPrestamoFacade.obtenerLectores();
        tableLectores.setModel(lectores);
        tableLectores.removeColumn(tableLectores.getColumn("ci"));
        tableLectores.removeColumn(tableLectores.getColumn("direccion"));
        tableLectores.removeColumn(tableLectores.getColumn("fecha_nacimiento"));
        tableLectores.removeColumn(tableLectores.getColumn("sexo"));
        tableLectores.removeColumn(tableLectores.getColumn("telefono"));
        tableLectores.removeColumn(tableLectores.getColumn("fecha_afiliacion"));
      
    }
    
    public void cargarTablaLibro() {
        DefaultTableModel libros = m_FichaPrestamoFacade.obtenerLibros();
        tableLibros.setModel(libros);
        tableLibros.removeColumn(tableLibros.getColumn("isbn"));
        tableLibros.removeColumn(tableLibros.getColumn("descripcion"));
        tableLibros.removeColumn(tableLibros.getColumn("paginas"));
        tableLibros.removeColumn(tableLibros.getColumn("fecha_lanzamiento"));
        tableLibros.removeColumn(tableLibros.getColumn("idioma"));
        tableLibros.removeColumn(tableLibros.getColumn("edicion"));
        tableLibros.removeColumn(tableLibros.getColumn("nro_ejemplares"));
        tableLibros.removeColumn(tableLibros.getColumn("id_categoria"));
        tableLibros.removeColumn(tableLibros.getColumn("id_editorial"));
        tableLibros.removeColumn(tableLibros.getColumn("ids_autores"));
    }
    
    public void concretarDevolucion() {
        int fila = tablePrestamos.getSelectedRow();
        DefaultTableModel prestamos = (DefaultTableModel) tablePrestamos.getModel();
        int id = Integer.parseInt(String.valueOf(prestamos.getValueAt(fila, 0)));
        m_FichaPrestamoNegocio.concretarPrestamo(id);
        prestamos.removeRow(fila);
        DefaultTableModel detalle = (DefaultTableModel) tableDetallePrestamos.getModel();
        detalle.setRowCount(0);
    }
    
    public void inicializar() {
        cargarTablaBibliotecario();
        cargarTablaLector();
        cargarTablaLibro();
        cargarComboEjemplares();
        obtenerPrestamos();
    }
    
    public void obtenerDetallePrestamo() {
        int fila = tablePrestamos.getSelectedRow();
        DefaultTableModel prestamos = (DefaultTableModel) tablePrestamos.getModel();
        int id = Integer.parseInt(String.valueOf(prestamos.getValueAt(fila, 0)));
        DefaultTableModel detalle = m_FichaPrestamoNegocio.obtenerDetallePrestamo(id);
        tableDetallePrestamos.setModel(detalle);
    }
    
    public void obtenerPrestamos() {
        DefaultTableModel prestamos = m_FichaPrestamoNegocio.obtenerPrestamos();
        tablePrestamos.setModel(prestamos);
        tablePrestamos.removeColumn(tablePrestamos.getColumn("devuelto"));
        tablePrestamos.removeColumn(tablePrestamos.getColumn("id_bibliotecario"));
    }
    
    public void registrarPrestamo() {
        int columnaLibro = 1, columnaEjemplar = 2;
        int dia = calendarFechaPrestamo.getDate().getDate();
        int mes = calendarFechaPrestamo.getDate().getMonth() + 1;
        int anio = calendarFechaPrestamo.getDate().getYear() + 1900;
        Date datePrestamo = Date.valueOf(anio + "-" + mes + "-" + dia);
        dia = calendarFechaDevolucion.getDate().getDate();
        mes = calendarFechaDevolucion.getDate().getMonth() + 1;
        anio = calendarFechaDevolucion.getDate().getYear() + 1900;
        Date dateDevolucion = Date.valueOf(anio + "-" + mes + "-" + dia);
        LinkedList<Integer> ids_libro = new LinkedList<>();
        LinkedList<Integer> nro_ejemplares = new LinkedList<>();
        DefaultTableModel detalle = (DefaultTableModel) tableDetallePrestamos.getModel();
        for (int i = 0; i < detalle.getRowCount(); i++) {
            ids_libro.add(Integer.parseInt(String.valueOf(detalle.getValueAt(i, columnaLibro))));
            nro_ejemplares.add(Integer.parseInt(String.valueOf(detalle.getValueAt(i, columnaEjemplar))));
        }
        int rowLector = tableLectores.getSelectedRow();
        int rowBibliotecario = tableBibliotecario.getSelectedRow();
        int id = m_FichaPrestamoNegocio.registrarPrestamo(
                datePrestamo,
                dateDevolucion,
                Integer.parseInt(String.valueOf(tableLectores.getValueAt(rowLector, 0))),
                Integer.parseInt(String.valueOf(tableBibliotecario.getValueAt(rowBibliotecario, 0))),
                ids_libro,
                nro_ejemplares
        );
        DefaultTableModel prestamo = (DefaultTableModel) tablePrestamos.getModel();
        prestamo.addRow(new Object[]{
            id, datePrestamo, dateDevolucion, false,
            Integer.parseInt(String.valueOf(tableLectores.getValueAt(rowLector, 0))),
            Integer.parseInt(String.valueOf(tableBibliotecario.getValueAt(rowBibliotecario, 0)))
        });
        detalle.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelFechaPrestamo = new javax.swing.JLabel();
        labelFechaDevolucion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLibros = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableLectores = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableBibliotecario = new javax.swing.JTable();
        labelLectores = new javax.swing.JLabel();
        labelLibros = new javax.swing.JLabel();
        labelBibliotecario = new javax.swing.JLabel();
        labelEjemplar = new javax.swing.JLabel();
        comboEjemplar = new javax.swing.JComboBox<>();
        buttonAgregarEjemplar = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonObtener = new javax.swing.JButton();
        buttonConcretar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePrestamos = new javax.swing.JTable();
        labelPrestamos = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableDetallePrestamos = new javax.swing.JTable();
        labelDetallePrestamos = new javax.swing.JLabel();
        calendarFechaPrestamo = new com.toedter.calendar.JDateChooser();
        calendarFechaDevolucion = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelFechaPrestamo.setText("Fecha de Prestamo");

        labelFechaDevolucion.setText("Fecha de Devolucion");

        tableLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Titulo"
            }
        ));
        tableLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLibrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLibros);

        tableLectores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Apellidos y Nombres"
            }
        ));
        jScrollPane2.setViewportView(tableLectores);

        tableBibliotecario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Apellidos y Nombres"
            }
        ));
        jScrollPane3.setViewportView(tableBibliotecario);

        labelLectores.setText("Lectores");

        labelLibros.setText("Libros");

        labelBibliotecario.setText("Bibliotecario");

        labelEjemplar.setText("Ejemplar");

        comboEjemplar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonAgregarEjemplar.setText("Agregar");
        buttonAgregarEjemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarEjemplarActionPerformed(evt);
            }
        });

        buttonRegistrar.setText("Registrar Prestamo");
        buttonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarActionPerformed(evt);
            }
        });

        buttonObtener.setText("Obtener Detalle");
        buttonObtener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonObtenerActionPerformed(evt);
            }
        });

        buttonConcretar.setText("Concretar Devolucion");
        buttonConcretar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConcretarActionPerformed(evt);
            }
        });

        tablePrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Fecha Prestamo", "Fecha Devolucion", "Lector"
            }
        ));
        jScrollPane4.setViewportView(tablePrestamos);

        labelPrestamos.setText("Prestamos");

        tableDetallePrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Libro", "Nro Ejemplar"
            }
        ));
        jScrollPane5.setViewportView(tableDetallePrestamos);

        labelDetallePrestamos.setText("Detalle Prestamos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(labelLectores)
                .addGap(214, 214, 214)
                .addComponent(labelLibros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelBibliotecario)
                .addGap(97, 97, 97))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(labelPrestamos))
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(labelFechaPrestamo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendarFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelFechaDevolucion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendarFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                            .addComponent(buttonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonObtener, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(labelEjemplar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAgregarEjemplar)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonConcretar, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(labelDetallePrestamos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelFechaPrestamo)
                        .addComponent(labelFechaDevolucion))
                    .addComponent(calendarFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendarFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLectores)
                    .addComponent(labelLibros)
                    .addComponent(labelBibliotecario))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEjemplar)
                    .addComponent(comboEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAgregarEjemplar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConcretar)
                    .addComponent(buttonObtener)
                    .addComponent(buttonRegistrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPrestamos)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(labelDetallePrestamos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLibrosMouseClicked
        // Selecciono Libro
        int fila = tableLibros.getSelectedRow();
        String[] tableHeader = new String[]{"id", "titulo", "isbn", "descripcion", "paginas", "fecha_lanzamiento", "idioma", "edicion", "nro_ejemplares", "id_categoria", "id_editorial", "ids_autores"};
        DefaultTableModel libros = (DefaultTableModel) tableLibros.getModel();
        // Cargar Combo
        int len = Integer.parseInt(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("nro_ejemplares"))));
        Object v[] = new Object[len];
        for (int i = 0; i < len; i++) {
            v[i] = i + 1;
        }
        comboEjemplar.setModel(new DefaultComboBoxModel(v));
    }//GEN-LAST:event_tableLibrosMouseClicked

    private void buttonAgregarEjemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarEjemplarActionPerformed
        /* Agregar libro al Detalle del Prestamo */
        // Selecciono Libro
        int fila = tableLibros.getSelectedRow();
        String[] tableHeader = new String[]{"id", "titulo", "isbn", "descripcion", "paginas", "fecha_lanzamiento", "idioma", "edicion", "nro_ejemplares", "id_categoria", "id_editorial", "ids_autores"};
        DefaultTableModel libros = (DefaultTableModel) tableLibros.getModel();
        // Lo agrego al detalle
        DefaultTableModel detalle = (DefaultTableModel) tableDetallePrestamos.getModel();
        detalle.addRow(new Object[]{null, Integer.parseInt(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("id")))),
            Integer.parseInt(String.valueOf(comboEjemplar.getSelectedItem()))});
    }//GEN-LAST:event_buttonAgregarEjemplarActionPerformed

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        registrarPrestamo();
        JOptionPane.showMessageDialog(rootPane, "SE HA REGISTRADO EL PRESTAMO CON EXITO");
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonObtenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonObtenerActionPerformed
        obtenerDetallePrestamo();
    }//GEN-LAST:event_buttonObtenerActionPerformed

    private void buttonConcretarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConcretarActionPerformed
        concretarDevolucion();
    }//GEN-LAST:event_buttonConcretarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FichaPrestamoFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FichaPrestamoFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FichaPrestamoFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FichaPrestamoFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FichaPrestamoFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAgregarEjemplar;
    private javax.swing.JButton buttonConcretar;
    private javax.swing.JButton buttonObtener;
    private javax.swing.JButton buttonRegistrar;
    private com.toedter.calendar.JDateChooser calendarFechaDevolucion;
    private com.toedter.calendar.JDateChooser calendarFechaPrestamo;
    private javax.swing.JComboBox<String> comboEjemplar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelBibliotecario;
    private javax.swing.JLabel labelDetallePrestamos;
    private javax.swing.JLabel labelEjemplar;
    private javax.swing.JLabel labelFechaDevolucion;
    private javax.swing.JLabel labelFechaPrestamo;
    private javax.swing.JLabel labelLectores;
    private javax.swing.JLabel labelLibros;
    private javax.swing.JLabel labelPrestamos;
    private javax.swing.JTable tableBibliotecario;
    private javax.swing.JTable tableDetallePrestamos;
    private javax.swing.JTable tableLectores;
    private javax.swing.JTable tableLibros;
    private javax.swing.JTable tablePrestamos;
    // End of variables declaration//GEN-END:variables
}
