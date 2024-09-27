/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.slc.sw_formato_operaciones;

import com.formdev.flatlaf.FlatLightLaf;
import com.slc.sw_formato_operaciones.DAO.EmpleadoDAO;
import com.slc.sw_formato_operaciones.DAO.LocalDAO;
import com.slc.sw_formato_operaciones.entities.Empleado;
import com.slc.sw_formato_operaciones.entities.Local;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Clider Fernando Tutaya Rivera
 */
public class FrmLocal extends javax.swing.JFrame {

    /**
     * Creates new form Empleados
     */
    
    DefaultTableModel defaultTableModel = null;
    LocalDAO localDAO = new LocalDAO();
    public FrmLocal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Data = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jTextField_NombreLocal = new javax.swing.JTextField();
        jLabel_id = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Sucursales - Speed Wash ");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTable_Data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_DataMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTable_DataMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Data);

        btnAdd.setText("Guardar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setText("Actualizar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDel.setText("Eliminar");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        jTextField_NombreLocal.setText("jTextField1");
        jTextField_NombreLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NombreLocalActionPerformed(evt);
            }
        });

        jLabel_id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_id.setText("id");

        jToggleButton1.setText("Nuevo");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_id, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_NombreLocal))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jToggleButton1)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnDel)
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnSave)
                    .addComponent(btnDel)
                    .addComponent(jToggleButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_NombreLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_id, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        
        if(jTable_Data.getSelectedRow() >= 0){
            int response = JOptionPane.showConfirmDialog(this,"Seguro que desea eliminar este Registro?");
            
            if(response == JOptionPane.YES_OPTION){
                int empId = Integer.parseInt(jTable_Data.getValueAt(jTable_Data.getSelectedRow(), 0).toString());
                
                //Eliminar entidad
                localDAO.deleteLocal(Long.valueOf(empId) );
                JOptionPane.showMessageDialog(rootPane, "Eliminado con Exito");
                limpiar();
            }            
           
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un Registro.");
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_formWindowOpened

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        String txtNameLocal = jTextField_NombreLocal.getText();
        

        Object[] row = new Object[4];
        row[0] = defaultTableModel.getRowCount() + 1;
        row[1] = txtNameLocal;
        
        defaultTableModel.addRow(row);
        
        Local empleado = new Local(txtNameLocal);
        localDAO.addLocal(empleado);
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        int rowSelected = jTable_Data.getSelectedRow();
        
        if( rowSelected >= 0 ){
            Local local = new Local(Long.valueOf(jLabel_id.getText()), jTextField_NombreLocal.getText());
            
            try {
                localDAO.updateLocal(local);
                JOptionPane.showMessageDialog(this, "Registro Actualizado");
                limpiar();

            } catch (HeadlessException e) {
                System.err.println("Error al actualizar Registro" + e.getMessage());
            }
            
        }else{
             JOptionPane.showMessageDialog(null, "Seleccione un Registro para Actualizar");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jTable_DataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_DataMouseClicked
        // TODO add your handling code here:
        int row = jTable_Data.getSelectedRow();
        String row_id = jTable_Data.getValueAt(row, 0).toString();
        String rowNombreLocal = jTable_Data.getValueAt(row, 1).toString();
        
        jLabel_id.setText(row_id);
        jTextField_NombreLocal.setText(rowNombreLocal);
       
    }//GEN-LAST:event_jTable_DataMouseClicked

    private void jTable_DataMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_DataMouseExited
        
    }//GEN-LAST:event_jTable_DataMouseExited

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void jTextField_NombreLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NombreLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NombreLocalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("Failed to apply Substance Graphite Look and Feel.");
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new FrmLocal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel_id;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Data;
    private javax.swing.JTextField jTextField_NombreLocal;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables



    private void cargarTabla() {
       defaultTableModel = new DefaultTableModel();
       defaultTableModel.addColumn("N°");
       defaultTableModel.addColumn("Nombre Local");
       List<Local> locales =  localDAO.getAllLocales();
       
       for(Local local : locales){
           defaultTableModel.addRow(new Object[]{local.getId().toString(), local.getNombre()});
       }
       jTable_Data.setModel(defaultTableModel);
       
       jTable_Data.getColumnModel().getColumn(0).setPreferredWidth(60); // Columna "N°"
       jTable_Data.getColumnModel().getColumn(1).setPreferredWidth(340); // Columna "Apellidos"

       
    }

    private void limpiar() {
        
        cargarTabla();
        // Obtener el valor de la primera columna y la última fila
        int lastRowIndex = jTable_Data.getRowCount() - 1;
        if (lastRowIndex >= 0) {
            Object value = jTable_Data.getValueAt(lastRowIndex, 0);
            //Convertir a String
            String valueStr = String.valueOf(value);
            
            //Convertir a Integer e incrementar
            int valueInt = Integer.parseInt(valueStr) +  1;
            
            //Asignar al jLabel
            jLabel_id.setText(String.valueOf(valueInt));
           
        } else {
            jLabel_id.setText("No hay datos");
        }
        

        jTextField_NombreLocal.setText("");
    }
}
