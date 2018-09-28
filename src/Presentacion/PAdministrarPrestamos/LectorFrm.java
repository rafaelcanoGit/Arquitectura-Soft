/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.PAdministrarPrestamos;

import Negocio.NAdministrarPrestamos.LectorNegocio;
import java.sql.Date;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class LectorFrm extends javax.swing.JFrame {

    /**
     * Creates new form LectorFrm
     */
    private LectorNegocio m_LectorNegocio;

    public LectorFrm() {
        initComponents();
        this.setTitle("Gestionar Lector");
        this.setLocationRelativeTo(null);
        m_LectorNegocio = new LectorNegocio();
        inicializar();
    }

    public void cargarComboSexo() {
        comboSexo.setModel(new DefaultComboBoxModel(new Object[]{"M", "F"}));
    }

    public void eliminarLector() {
        int fila = tableLectores.getSelectedRow();
        DefaultTableModel lectoresUpdated = (DefaultTableModel) tableLectores.getModel();
        m_LectorNegocio.eliminarLector(Integer.parseInt(lectoresUpdated.getValueAt(fila, 0).toString()));
        lectoresUpdated.removeRow(fila);
        textCi.setText("");
        textApellidos.setText("");
        textNombres.setText("");
        textDireccion.setText("");
        calendarFechaAfiliacion.setDateFormatString("");
        calendarFechaNacimiento.setDateFormatString("");
        textTelefono.setText("");
    }

    public void inicializar() {
        cargarComboSexo();
        obtenerLectores();
    }

    public void modificarLector() {
        String[] tableHeader = new String[]{"id", "ci", "apellidos", "nombres", "direccion", "fecha_nacimiento", "sexo", "telefono", "fecha_afiliacion"};
        int dia = calendarFechaNacimiento.getDate().getDate();
        int mes = calendarFechaNacimiento.getDate().getMonth() + 1;
        int anio = calendarFechaNacimiento.getDate().getYear() + 1900;
        Date date = Date.valueOf(anio +"-"+ mes +"-"+ dia);
        dia = calendarFechaAfiliacion.getDate().getDate();
        mes = calendarFechaAfiliacion.getDate().getMonth() + 1;
        anio = calendarFechaAfiliacion.getDate().getYear() + 1900;
        Date date2 = Date.valueOf(anio +"-"+ mes +"-"+ dia);
        DefaultTableModel lectoresUpdated = (DefaultTableModel) tableLectores.getModel();
        int fila = tableLectores.getSelectedRow();
        m_LectorNegocio.modificarLector( Integer.parseInt(lectoresUpdated.getValueAt(fila, Arrays.asList(tableHeader).indexOf("id")).toString()),
                Integer.parseInt(textCi.getText()),textApellidos.getText(),textNombres.getText(),textDireccion.getText(),date,
                String.valueOf(comboSexo.getSelectedItem()).charAt(0),Integer.parseInt(textTelefono.getText()),date2);
        
        lectoresUpdated.setValueAt(textCi.getText(), fila, Arrays.asList(tableHeader).indexOf("ci"));
        lectoresUpdated.setValueAt(textApellidos.getText(), fila, Arrays.asList(tableHeader).indexOf("apellidos"));
        lectoresUpdated.setValueAt(textNombres.getText(), fila, Arrays.asList(tableHeader).indexOf("nombres"));
        lectoresUpdated.setValueAt(textDireccion.getText(), fila, Arrays.asList(tableHeader).indexOf("direccion"));
        lectoresUpdated.setValueAt(calendarFechaNacimiento.getDate().toString(), fila, Arrays.asList(tableHeader).indexOf("fecha_nacimiento"));
        lectoresUpdated.setValueAt(String.valueOf(comboSexo.getSelectedItem()).charAt(0), fila, Arrays.asList(tableHeader).indexOf("sexo"));
        lectoresUpdated.setValueAt(textTelefono.getText(), fila, Arrays.asList(tableHeader).indexOf("telefono"));
        lectoresUpdated.setValueAt(calendarFechaAfiliacion.getDate().toString(), fila, Arrays.asList(tableHeader).indexOf("fecha_afiliacion"));
    }

    public void obtenerLectores() {
        DefaultTableModel lectores = m_LectorNegocio.obtenerLectores();
        tableLectores.setModel(lectores);
        tableLectores.removeColumn(tableLectores.getColumn("direccion"));
        tableLectores.removeColumn(tableLectores.getColumn("fecha_nacimiento"));
        tableLectores.removeColumn(tableLectores.getColumn("sexo"));
        tableLectores.removeColumn(tableLectores.getColumn("telefono"));
        tableLectores.removeColumn(tableLectores.getColumn("fecha_afiliacion"));
        
    }

    public void registrarLector() {
        int dia = calendarFechaNacimiento.getDate().getDate();
        int mes = calendarFechaNacimiento.getDate().getMonth() + 1;
        int anio = calendarFechaNacimiento.getDate().getYear() + 1900;
        Date date = Date.valueOf(anio +"-"+ mes +"-"+ dia);
        dia = calendarFechaAfiliacion.getDate().getDate();
        mes = calendarFechaAfiliacion.getDate().getMonth() + 1;
        anio = calendarFechaAfiliacion.getDate().getYear() + 1900;
        Date date2 = Date.valueOf(anio +"-"+ mes +"-"+ dia);
        int id = m_LectorNegocio.registrarLector(
                Integer.parseInt(textCi.getText()),
                textApellidos.getText(),
                textNombres.getText(),
                textDireccion.getText(),
                date,
                String.valueOf(comboSexo.getSelectedItem()).charAt(0),
                Integer.parseInt(textTelefono.getText()),
                date2);
        DefaultTableModel lectoresUpdated = (DefaultTableModel) tableLectores.getModel();
        lectoresUpdated.addRow(new Object[]{
            id,
            Integer.parseInt(textCi.getText()),
            textApellidos.getText(),
            textNombres.getText(),
            textDireccion.getText(),
            date,
            String.valueOf(comboSexo.getSelectedItem()).charAt(0),
            Integer.parseInt(textTelefono.getText()),
            date2,
            0
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCi = new javax.swing.JLabel();
        labelApellidos = new javax.swing.JLabel();
        labelNombres = new javax.swing.JLabel();
        labelDireccion = new javax.swing.JLabel();
        labelFechaNacimiento = new javax.swing.JLabel();
        labelSexo = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        labelFechaAfiliacion = new javax.swing.JLabel();
        textCi = new javax.swing.JTextField();
        textApellidos = new javax.swing.JTextField();
        textNombres = new javax.swing.JTextField();
        textDireccion = new javax.swing.JTextField();
        textTelefono = new javax.swing.JTextField();
        comboSexo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLectores = new javax.swing.JTable();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        calendarFechaAfiliacion = new com.toedter.calendar.JDateChooser();
        calendarFechaNacimiento = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelCi.setText("CI");

        labelApellidos.setText("Apellidos");

        labelNombres.setText("Nombres");

        labelDireccion.setText("Direccion");

        labelFechaNacimiento.setText("Fecha de Nacimiento");

        labelSexo.setText("Sexo");

        labelTelefono.setText("Telefono");

        labelFechaAfiliacion.setText("Fecha de Afiliacion");

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));

        tableLectores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Apellidos", "Nombres", "CI"
            }
        ));
        tableLectores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLectoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLectores);

        buttonRegistrar.setText("Registrar");
        buttonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarActionPerformed(evt);
            }
        });

        buttonModificar.setText("Modificar");
        buttonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarActionPerformed(evt);
            }
        });

        buttonEliminar.setText("Eliminar");
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNombres)
                                    .addComponent(labelDireccion))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(textDireccion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSexo)
                                    .addComponent(labelFechaNacimiento)
                                    .addComponent(labelTelefono)
                                    .addComponent(labelFechaAfiliacion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(144, 144, 144)
                                .addComponent(buttonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                .addGap(71, 71, 71))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelApellidos)
                                    .addComponent(labelCi))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textCi, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textApellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textTelefono, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(comboSexo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 189, Short.MAX_VALUE))
                            .addComponent(buttonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(calendarFechaAfiliacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(calendarFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelCi)
                        .addComponent(labelFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textCi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(calendarFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFechaAfiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(calendarFechaAfiliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRegistrar)
                    .addComponent(buttonModificar)
                    .addComponent(buttonEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        registrarLector();
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void tableLectoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLectoresMouseClicked
        int fila = tableLectores.getSelectedRow();
        String[] tableHeader = new String[]{"id", "ci", "apellidos", "nombres", "direccion", "fecha_nacimiento", "sexo", "telefono", "fecha_afiliacion"};
        DefaultTableModel lectores = (DefaultTableModel) tableLectores.getModel();
        textCi.setText(String.valueOf(lectores.getValueAt(fila, Arrays.asList(tableHeader).indexOf("ci"))));
        textApellidos.setText(String.valueOf(lectores.getValueAt(fila, Arrays.asList(tableHeader).indexOf("apellidos"))));
        textNombres.setText(String.valueOf(lectores.getValueAt(fila, Arrays.asList(tableHeader).indexOf("nombres"))));
        textDireccion.setText(String.valueOf(lectores.getValueAt(fila, Arrays.asList(tableHeader).indexOf("direccion"))));
        calendarFechaNacimiento.setDate(Date.valueOf(String.valueOf(lectores.getValueAt(fila, Arrays.asList(tableHeader).indexOf("fecha_nacimiento")))));
        textTelefono.setText(String.valueOf(lectores.getValueAt(fila, Arrays.asList(tableHeader).indexOf("telefono"))));
        calendarFechaAfiliacion.setDate(Date.valueOf(String.valueOf(lectores.getValueAt(fila, Arrays.asList(tableHeader).indexOf("fecha_afiliacion")))));
        comboSexo.setSelectedItem(String.valueOf(lectores.getValueAt(fila, Arrays.asList(tableHeader).indexOf("sexo"))));
    }//GEN-LAST:event_tableLectoresMouseClicked

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        modificarLector();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        eliminarLector();
    }//GEN-LAST:event_buttonEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(LectorFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LectorFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LectorFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LectorFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LectorFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonRegistrar;
    private com.toedter.calendar.JDateChooser calendarFechaAfiliacion;
    private com.toedter.calendar.JDateChooser calendarFechaNacimiento;
    private javax.swing.JComboBox<String> comboSexo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelApellidos;
    private javax.swing.JLabel labelCi;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelFechaAfiliacion;
    private javax.swing.JLabel labelFechaNacimiento;
    private javax.swing.JLabel labelNombres;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JTable tableLectores;
    private javax.swing.JTextField textApellidos;
    private javax.swing.JTextField textCi;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textNombres;
    private javax.swing.JTextField textTelefono;
    // End of variables declaration//GEN-END:variables
}
