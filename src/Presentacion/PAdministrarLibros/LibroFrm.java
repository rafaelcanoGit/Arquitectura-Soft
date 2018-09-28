/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.PAdministrarLibros;

import Negocio.NAdministrarLibros.LibroFacade;
import Negocio.NAdministrarLibros.LibroNegocio;
import java.sql.Date;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class LibroFrm extends javax.swing.JFrame {

    /**
     * Creates new form LibroFrm
     */
    private LibroNegocio m_LibroNegocio;
    private LibroFacade m_LibroFacade;
    
    public LibroFrm() {
        initComponents();
        this.setTitle("Gestionar Libro");
        this.setLocationRelativeTo(null);
        m_LibroNegocio = new LibroNegocio();
        m_LibroFacade = new LibroFacade();
        inicializar();
    }
    
    public void cargarTablaAutores() {
        DefaultTableModel autores = m_LibroFacade.obtenerAutores();
        tableAutores.setModel(autores);
    }
    
    public void cargarTablaCategoria() {
        DefaultTableModel categorias = m_LibroFacade.obtenerCategorias();
        tableCategoria.setModel(categorias);
        tableCategoria.removeColumn(tableCategoria.getColumn("descripcion"));
    }
    
    public void cargarTablaEditorial() {
        DefaultTableModel editoriales = m_LibroFacade.obtenerEditoriales();
        tableEditorial.setModel(editoriales);
        tableEditorial.removeColumn(tableEditorial.getColumn("telefono"));
        tableEditorial.removeColumn(tableEditorial.getColumn("direccion"));
    }
    
    public void eliminarLibro() {
        int fila = tableLibros.getSelectedRow();
        DefaultTableModel librosUpdated = (DefaultTableModel) tableLibros.getModel();
        m_LibroNegocio.eliminarLibro(Integer.parseInt(librosUpdated.getValueAt(fila, 0).toString()));
        librosUpdated.removeRow(fila);
        tableAutores.clearSelection();
        tableCategoria.clearSelection();
        tableEditorial.clearSelection();
        textTitulo.setText("");
        textIsbn.setText("");
        calendarFechaLanzamiento.setDateFormatString("");
        textDescripcion.setText("");
        textPaginas.setText("");
        textEdicion.setText("");
        textIdioma.setText("");
        spinnerEjemplares.setValue(0);
    }
    
    public void inicializar() {
        cargarTablaEditorial();
        cargarTablaAutores();
        cargarTablaCategoria();
        obtenerLibros();
    }
    
    public void modificarLibro() {
        String[] tableHeader = new String[]{"id", "titulo", "isbn", "descripcion", "paginas", "fecha_lanzamiento", "idioma", "edicion", "nro_ejemplares", "id_categoria", "id_editorial", "ids_autores"};
        int dia = calendarFechaLanzamiento.getDate().getDate();
        int mes = calendarFechaLanzamiento.getDate().getMonth() + 1;
        int anio = calendarFechaLanzamiento.getDate().getYear() + 1900;
        Date date = Date.valueOf(anio +"-"+ mes +"-"+ dia);
        LinkedList<Integer> autores = new LinkedList<>();
        int[] autoresSelected = tableAutores.getSelectedRows();
        for (int i = 0; i < autoresSelected.length; i++) {
            autores.add((Integer) tableAutores.getValueAt(autoresSelected[i], 0));
        }
        DefaultTableModel librosUpdated = (DefaultTableModel) tableLibros.getModel();
        int fila = tableLibros.getSelectedRow();
        m_LibroNegocio.modificarLibro( Integer.parseInt(librosUpdated.getValueAt(fila, Arrays.asList(tableHeader).indexOf("id")).toString()),
                textTitulo.getText(),  textIsbn.getText(),
                textDescripcion.getText(), Integer.parseInt(textPaginas.getText()),
                Integer.parseInt(textEdicion.getText()),date, textIdioma.getText(), Integer.parseInt(String.valueOf(spinnerEjemplares.getValue())),
                Integer.parseInt(String.valueOf(tableCategoria.getValueAt(tableCategoria.getSelectedRow(), 0))),Integer.parseInt(String.valueOf(tableEditorial.getValueAt(tableEditorial.getSelectedRow(), 0))),
                Integer.parseInt(String.valueOf(tableAutores.getValueAt(tableAutores.getSelectedRow(), 0))) );
        
        librosUpdated.setValueAt(textTitulo.getText(), fila, Arrays.asList(tableHeader).indexOf("titulo"));
        librosUpdated.setValueAt(textIsbn.getText(), fila, Arrays.asList(tableHeader).indexOf("isbn"));
        librosUpdated.setValueAt(textDescripcion.getText(), fila, Arrays.asList(tableHeader).indexOf("descripcion"));
        librosUpdated.setValueAt(Integer.parseInt(textPaginas.getText()), fila, Arrays.asList(tableHeader).indexOf("paginas"));
        librosUpdated.setValueAt(Integer.parseInt(textEdicion.getText()), fila, Arrays.asList(tableHeader).indexOf("edicion"));
        librosUpdated.setValueAt(calendarFechaLanzamiento.getDate().toString(), fila, Arrays.asList(tableHeader).indexOf("fecha_lanzamiento"));
        librosUpdated.setValueAt(Integer.parseInt(String.valueOf(spinnerEjemplares.getValue())), fila, Arrays.asList(tableHeader).indexOf("nro_ejemplares"));
        librosUpdated.setValueAt(Integer.parseInt(String.valueOf(tableCategoria.getValueAt(tableCategoria.getSelectedRow(), 0))), fila, Arrays.asList(tableHeader).indexOf("id_categoria"));
        librosUpdated.setValueAt(Integer.parseInt(String.valueOf(tableEditorial.getValueAt(tableEditorial.getSelectedRow(), 0))), fila, Arrays.asList(tableHeader).indexOf("id_editorial"));
        librosUpdated.setValueAt(autores, fila, Arrays.asList(tableHeader).indexOf("ids_autores"));
    }
    
    public void obtenerLibros() {
        DefaultTableModel libros = m_LibroNegocio.obtenerLibros();
        tableLibros.setModel(libros);
        tableLibros.removeColumn(tableLibros.getColumn("isbn"));
        tableLibros.removeColumn(tableLibros.getColumn("paginas"));
        tableLibros.removeColumn(tableLibros.getColumn("fecha_lanzamiento"));
        tableLibros.removeColumn(tableLibros.getColumn("idioma"));
        tableLibros.removeColumn(tableLibros.getColumn("edicion"));
        tableLibros.removeColumn(tableLibros.getColumn("nro_ejemplares"));
        tableLibros.removeColumn(tableLibros.getColumn("id_categoria"));
        tableLibros.removeColumn(tableLibros.getColumn("id_editorial"));
        tableLibros.removeColumn(tableLibros.getColumn("ids_autores"));
    }
    
    public void registrarLibro() {
        int dia = calendarFechaLanzamiento.getDate().getDate();
        int mes = calendarFechaLanzamiento.getDate().getMonth() + 1;
        int anio = calendarFechaLanzamiento.getDate().getYear() + 1900;
        Date date = Date.valueOf(anio +"-"+ mes +"-"+ dia);
        LinkedList<Integer> autores = new LinkedList<>();
        int[] autoresSelected = tableAutores.getSelectedRows();
        for (int i = 0; i < autoresSelected.length; i++) {
            autores.add((Integer) tableAutores.getValueAt(autoresSelected[i], 0));
        }
        int id = m_LibroNegocio.registrarLibro(
                textTitulo.getText(),
                textIsbn.getText(),
                textDescripcion.getText(),
                Integer.parseInt(textPaginas.getText()),
                Integer.parseInt(textEdicion.getText()),
                date,
                textIdioma.getText(),
                Integer.parseInt(String.valueOf(spinnerEjemplares.getValue())),
                Integer.parseInt(String.valueOf(tableCategoria.getValueAt(tableCategoria.getSelectedRow(), 0))),
                Integer.parseInt(String.valueOf(tableEditorial.getValueAt(tableEditorial.getSelectedRow(), 0))),
                Integer.parseInt(String.valueOf(tableAutores.getValueAt(tableAutores.getSelectedRow(), 0))));
        
        DefaultTableModel librosUpdated = (DefaultTableModel) tableLibros.getModel();
        librosUpdated.addRow(new Object[]{
            id,
            textTitulo.getText(),
            textIsbn.getText(),
            textDescripcion.getText(),
            Integer.parseInt(textPaginas.getText()),
            date,
            textIdioma.getText(),
            Integer.parseInt(textEdicion.getText()),
            Integer.parseInt(String.valueOf(spinnerEjemplares.getValue())),
            Integer.parseInt(String.valueOf(tableCategoria.getValueAt(tableCategoria.getSelectedRow(), 0))),
            Integer.parseInt(String.valueOf(tableEditorial.getValueAt(tableEditorial.getSelectedRow(), 0))),
            autores
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

        labelTitulo = new javax.swing.JLabel();
        textTitulo = new javax.swing.JTextField();
        labelIsbn = new javax.swing.JLabel();
        textIsbn = new javax.swing.JTextField();
        labelPaginas = new javax.swing.JLabel();
        textPaginas = new javax.swing.JTextField();
        labelEdicion = new javax.swing.JLabel();
        textEdicion = new javax.swing.JTextField();
        labelIdioma = new javax.swing.JLabel();
        textIdioma = new javax.swing.JTextField();
        labelFechaLanzamiento = new javax.swing.JLabel();
        labelEjemplares = new javax.swing.JLabel();
        spinnerEjemplares = new javax.swing.JSpinner();
        labelDescripcion = new javax.swing.JLabel();
        textDescripcion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLibros = new javax.swing.JTable();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableEditorial = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableCategoria = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableAutores = new javax.swing.JTable();
        labelEditorial = new javax.swing.JLabel();
        labelCategoria = new javax.swing.JLabel();
        labelAutores = new javax.swing.JLabel();
        calendarFechaLanzamiento = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTitulo.setText("Titulo");

        labelIsbn.setText("ISBN");

        labelPaginas.setText("Paginas");

        labelEdicion.setText("Edicion");

        labelIdioma.setText("Idioma");

        labelFechaLanzamiento.setText("Fecha Lanzamiento");

        labelEjemplares.setText("Ejemplares");

        labelDescripcion.setText("Descripcion");

        tableLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Titulo", "Descripcion"
            }
        ));
        tableLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLibrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLibros);

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

        tableEditorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nombre"
            }
        ));
        jScrollPane2.setViewportView(tableEditorial);

        tableCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nombre"
            }
        ));
        jScrollPane3.setViewportView(tableCategoria);

        tableAutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Pais Origen"
            }
        ));
        tableAutores.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane4.setViewportView(tableAutores);

        labelEditorial.setText("Editorial");

        labelCategoria.setText("Categoria");

        labelAutores.setText("Autores");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelFechaLanzamiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(calendarFechaLanzamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTitulo)
                                    .addComponent(labelIsbn)
                                    .addComponent(labelPaginas)
                                    .addComponent(labelEdicion)
                                    .addComponent(labelIdioma))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textIdioma)
                                    .addComponent(textEdicion)
                                    .addComponent(textPaginas)
                                    .addComponent(textIsbn)
                                    .addComponent(textTitulo)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelEjemplares)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerEjemplares, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(labelEditorial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelCategoria)
                                .addGap(71, 71, 71))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelAutores)
                                .addGap(174, 174, 174))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelDescripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textDescripcion))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)
                        .addComponent(buttonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addGap(149, 149, 149)
                        .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTitulo)
                            .addComponent(textTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelIsbn)
                            .addComponent(textIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPaginas)
                            .addComponent(textPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEdicion)
                            .addComponent(textEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEditorial)
                            .addComponent(labelCategoria))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelAutores)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelIdioma)
                            .addComponent(textIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelFechaLanzamiento)
                            .addComponent(calendarFechaLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEjemplares)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDescripcion)
                    .addComponent(textDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRegistrar)
                    .addComponent(buttonModificar)
                    .addComponent(buttonEliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        registrarLibro();
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void tableLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLibrosMouseClicked
        int fila = tableLibros.getSelectedRow();
        String[] tableHeader = new String[]{"id", "titulo", "isbn", "descripcion", "paginas", "fecha_lanzamiento", "idioma", "edicion", "nro_ejemplares", "id_categoria", "id_editorial", "ids_autores"};
        DefaultTableModel libros = (DefaultTableModel) tableLibros.getModel();
        textTitulo.setText(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("titulo"))));
        textIsbn.setText(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("isbn"))));
        textDescripcion.setText(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("descripcion"))));
        textPaginas.setText(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("paginas"))));
        calendarFechaLanzamiento.setDate(Date.valueOf(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("fecha_lanzamiento")))));
        textIdioma.setText(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("idioma"))));
        textEdicion.setText(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("edicion"))));
        spinnerEjemplares.setValue(Integer.parseInt(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("nro_ejemplares")))));
        // Seleccionar tablas
        int id_categoria = Integer.parseInt(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("id_categoria"))));
        for (int i = 0; i < tableCategoria.getRowCount(); i++) {
            if (Integer.parseInt(String.valueOf(tableCategoria.getValueAt(i, 0))) == id_categoria) {
                tableCategoria.setRowSelectionInterval(i, i);
                break;
            }
        }
        int id_editorial = Integer.parseInt(String.valueOf(libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("id_editorial"))));
        for (int i = 0; i < tableEditorial.getRowCount(); i++) {
            if (Integer.parseInt(String.valueOf(tableEditorial.getValueAt(i, 0))) == id_editorial) {
                tableEditorial.setRowSelectionInterval(i, i);
                break;
            }
        }
        LinkedList<Integer> ids_autores = (LinkedList<Integer>) libros.getValueAt(fila, Arrays.asList(tableHeader).indexOf("ids_autores"));
        ListSelectionModel selection = tableAutores.getSelectionModel();
        selection.clearSelection();
        for (int i = 0; i < tableAutores.getRowCount(); i++) {
            if (ids_autores.indexOf(Integer.parseInt(String.valueOf(tableAutores.getValueAt(i, 0)))) != -1) {
                selection.addSelectionInterval(i, i);
            }
        }
    }//GEN-LAST:event_tableLibrosMouseClicked

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        modificarLibro();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        eliminarLibro();
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
            java.util.logging.Logger.getLogger(LibroFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibroFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibroFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibroFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibroFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonRegistrar;
    private com.toedter.calendar.JDateChooser calendarFechaLanzamiento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelAutores;
    private javax.swing.JLabel labelCategoria;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelEdicion;
    private javax.swing.JLabel labelEditorial;
    private javax.swing.JLabel labelEjemplares;
    private javax.swing.JLabel labelFechaLanzamiento;
    private javax.swing.JLabel labelIdioma;
    private javax.swing.JLabel labelIsbn;
    private javax.swing.JLabel labelPaginas;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JSpinner spinnerEjemplares;
    private javax.swing.JTable tableAutores;
    private javax.swing.JTable tableCategoria;
    private javax.swing.JTable tableEditorial;
    private javax.swing.JTable tableLibros;
    private javax.swing.JTextField textDescripcion;
    private javax.swing.JTextField textEdicion;
    private javax.swing.JTextField textIdioma;
    private javax.swing.JTextField textIsbn;
    private javax.swing.JTextField textPaginas;
    private javax.swing.JTextField textTitulo;
    // End of variables declaration//GEN-END:variables
}
