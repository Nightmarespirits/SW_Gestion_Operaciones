/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slc.sw_formato_operaciones.utils;

import com.slc.sw_formato_operaciones.DAO.OperacionDAO;
import com.slc.sw_formato_operaciones.entities.Operacion;
import com.slc.sw_formato_operaciones.entities.Orden;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Clider Fernando Tutaya Rivera
 */
public class Utils {
     //Funcion para exportar datos a excel: 
    OperacionDAO operacionDao = new OperacionDAO();

    public Utils() {
    }
    
    public void exportarAExcel(){
        
        //crar una lista de todos los registros de operaciones:
        List<Operacion> listaOperacionesLavado = operacionDao.getOperacionesByType("Lavado");
        List<Operacion> listaOperacionesSecado = operacionDao.getOperacionesByType("Secado");
        List<Operacion> listaOperacionesDoblado = operacionDao.getOperacionesByType("Doblado");
        
        //Crear un nuevo libro de trabajo
        Workbook workbook = new XSSFWorkbook();
        Sheet hoja1 = workbook.createSheet("Todas las Operaciones");
        
        //TableModel modelTabla = jtable.getModel();
        
        //Creando la cabecera para excel
        Row cabeceraTipoOperacion = hoja1.createRow(0);
        Cell celda1 = cabeceraTipoOperacion.createCell(0);
        celda1.setCellValue("LAVADO");
         // Combinar celdas de la columna 0 a la columna 12 en la fila 0
        hoja1.addMergedRegion(new CellRangeAddress(0,0,0,12));
        Cell celda2 = cabeceraTipoOperacion.createCell(13);
        celda2.setCellValue("SECADO");
        hoja1.addMergedRegion(new CellRangeAddress(0,0,13,25));
        Cell celda3 = cabeceraTipoOperacion.createCell(26);
        celda3.setCellValue("DOBLADO");
        hoja1.addMergedRegion(new CellRangeAddress(0,0,26,38));
        
        Row cabeceraItems = hoja1.createRow(1);
        //Columnas
        String[] headersString = {"N°", "Fecha", "Hora", "Local", "N° Orden1", "Cantidad", "N° Orden2", "Cantidad", "N° Orden3", "Cantidad", "Maquina", "Responsable", "Estado",
        "N°", "Fecha", "Hora", "Local", "N° Orden1", "Cantidad", "N° Orden2", "Cantidad", "N° Orden3", "Cantidad", "Maquina", "Responsable", "Estado",
        "N°", "Fecha", "Hora", "Local", "N° Orden1", "Cantidad", "N° Orden2", "Cantidad", "N° Orden3", "Cantidad", "Maquina", "Responsable", "Estado"};
        //LLenar datos de los items de la cabecera
        for(int i = 0; i < headersString.length;  i++){
           Cell cell = cabeceraItems.createCell(i);
           cell.setCellValue(headersString[i]);
        }

        
        llenarDatos(hoja1, listaOperacionesLavado, 2, 0);
        llenarDatos(hoja1, listaOperacionesSecado, 2, 13);
        llenarDatos(hoja1, listaOperacionesDoblado, 2, 26);
        
        
        //Guardar el archivo excel( hacer que el usuario seleccione la ruta donde guardar o guardar en downloads  por defectoS
        File defaultDownloadFolder = FileSystemView.getFileSystemView().getDefaultDirectory();
        LocalDateTime ldtm = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String fechaHoraFormateada = ldtm.format(formato);
        File fileToSave = new File(defaultDownloadFolder,  fechaHoraFormateada + ".xlsx");
        
        try(FileOutputStream fileout = new FileOutputStream(fileToSave)) {
            workbook.write(fileout);
            JOptionPane.showMessageDialog(null, "Datos exportados con exito",  "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);
            
//Abrir el archivo en el escritorio
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(fileToSave);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al exportar los datos.");

        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Método para llenar datos en operacion
    private void llenarDatos(Sheet hoja, List<Operacion> listaOperaciones, int filaInicial, int columnaInicial) {
        int filaActual = filaInicial; // Variable para controlar la fila actual
        for (Operacion op : listaOperaciones) {
            Row fila = hoja.getRow(filaActual); // Obtener o crear fila según filaActual
            if (fila == null) {
                fila = hoja.createRow(filaActual);
            }

            String[] data = {
                op.getId().toString(),
                op.getFecha() != null ? op.getFecha().toLocalDate().toString() : "SIN ESPECIFICAR",
                op.getFecha() != null ? op.getFecha().toLocalTime().toString() : "SIN ESPECIFICAR",
                op.getLocal() != null ? op.getLocal().getNombre() : "SIN ESPECIFICAR",
                !op.getOrdenes().isEmpty() ? op.getOrdenes().get(0).getNumOrden() : "SIN ESPECIFICAR",
                !op.getOrdenes().isEmpty() ? String.valueOf(op.getOrdenes().get(0).getCantPrendas()) : "SIN ESPECIFICAR",
                op.getOrdenes().size() > 1 ? op.getOrdenes().get(1).getNumOrden() : "SIN ESPECIFICAR",
                op.getOrdenes().size() > 1 ? String.valueOf(op.getOrdenes().get(1).getCantPrendas()) : "SIN ESPECIFICAR",
                op.getOrdenes().size() > 2 ? op.getOrdenes().get(2).getNumOrden() : "SIN ESPECIFICAR",
                op.getOrdenes().size() > 2 ? String.valueOf(op.getOrdenes().get(2).getCantPrendas()) : "SIN ESPECIFICAR",
                op.getMaquina() != null ? op.getMaquina().getName() : "NO APLICA",
                op.getResponsable() != null ? op.getResponsable().getNombres() + "," + op.getResponsable().getApellidos() : "SIN ESPECIFICAR",
                op.getEstadoOperacion() != null ? (op.getEstadoOperacion() ? "FINALIZADO" : "PENDIENTE") : "PENDIENTE"
            };

            // Llenar las celdas de la fila actual empezando desde columnaInicial
            for (int j = 0; j < data.length; j++) {
                Cell celda = fila.createCell(columnaInicial + j);
                celda.setCellValue(data[j]);
            }

            filaActual++; // Incrementar la fila para la siguiente operación
        }
    }
    
    
    public void traspasarProcesoLavado_Secado(){
        List<Operacion> operacionesLavadoCompletadas = operacionDao.getOperacionesByTypeAndState("Lavado", true);
        //TRASPASO DE OPERACIONES DE LAVADO FINALIZADAS (PASADAS AL PROCESO SECADO)
        if (!operacionesLavadoCompletadas.isEmpty()) {
            for (Operacion op : operacionesLavadoCompletadas) {
                /*CONDICIONAL PARA COMPARAR SI LA OPERACION YA HA SIDO ENVIADA AL SIGUIENTE PROCESO*/
                System.out.println(op.getIsNextProcessAdded());
                Boolean isNextProcessAdded = op.getIsNextProcessAdded() != null ? op.getIsNextProcessAdded() : Boolean.FALSE;
                if( !isNextProcessAdded){

                    // Verificar que haya al menos un orden en la lista
                    String numOrden1 = !op.getOrdenes().isEmpty() ? op.getOrdenes().get(0).getNumOrden() : "";

                    // Verificar que haya al menos dos órdenes en la lista
                    String numOrden2 = op.getOrdenes().size() > 1 ? op.getOrdenes().get(1).getNumOrden() : "";

                    // Verificar que haya al menos tres órdenes en la lista
                    String numOrden3 = op.getOrdenes().size() > 2 ? op.getOrdenes().get(2).getNumOrden() : "";



                    //Agregar Operacion lavado como tipo secado



                    System.out.println(numOrden1);
                    System.out.println(numOrden2);
                    System.out.println(numOrden3);


                    Orden orden1 = new Orden(numOrden1, 0);
                    Orden orden2 = new Orden(numOrden2, 0);
                    Orden orden3 = new Orden(numOrden3, 0);

                    List<Orden> ordenesList = new ArrayList<>();

                    ordenesList.add(orden1);
                    ordenesList.add(orden2);
                    ordenesList.add(orden3);


                    Operacion operacionSecado = new Operacion("Secado",null,ordenesList,null,null,null );
                    ordenesList.forEach(orden -> orden.setOperacion(operacionSecado));

                    op.setIsNextProcessAdded(true);
                    if(operacionDao.updateOperacion(op)){
                        System.out.println("Actualizado el objeto persistente");
                    }else{
                        System.out.println("Retorno false");
                    }


                    operacionDao.addOperacion(operacionSecado);

                    //cambiar la propiedad a true para que ya no se agrege en la proxima carga

                }else{
                    System.err.println("Parece que esta dando false");
                }
                System.out.println(op.getIsNextProcessAdded());
            }
        }
    }
    
    public void traspasarProcesoSecado_Doblado(){
        
        List<Operacion> operacionesSecadoCompletadas = operacionDao.getOperacionesByTypeAndState("Secado", true);
        //TRASPASO DE OPERACIONES DE LAVADO FINALIZADAS (PASADAS AL PROCESO SECADO)
        if (!operacionesSecadoCompletadas.isEmpty()) {
            for (Operacion op : operacionesSecadoCompletadas) {
                /*CONDICIONAL PARA COMPARAR SI LA OPERACION YA HA SIDO ENVIADA AL SIGUIENTE PROCESO*/
                System.out.println(op.getIsNextProcessAdded());
                Boolean isNextProcessAdded = op.getIsNextProcessAdded() != null ? op.getIsNextProcessAdded() : Boolean.FALSE;
                if( !isNextProcessAdded){
                    
                    // Verificar que haya al menos un orden en la lista
                    String numOrden1 = !op.getOrdenes().isEmpty() ? op.getOrdenes().get(0).getNumOrden() : "";

                    // Verificar que haya al menos dos órdenes en la lista
                    String numOrden2 = op.getOrdenes().size() > 1 ? op.getOrdenes().get(1).getNumOrden() : "";

                    // Verificar que haya al menos tres órdenes en la lista
                    String numOrden3 = op.getOrdenes().size() > 2 ? op.getOrdenes().get(2).getNumOrden() : "";


                    
                    //Agregar Operacion lavado como tipo secado
                    
                    
                    
                    System.out.println(numOrden1);
                    System.out.println(numOrden2);
                    System.out.println(numOrden3);
                    
                    
                    Orden orden1 = new Orden(numOrden1, 0);
                    Orden orden2 = new Orden(numOrden2, 0);
                    Orden orden3 = new Orden(numOrden3, 0);
                    
                    List<Orden> ordenesList = new ArrayList<>();
                    
                    ordenesList.add(orden1);
                    ordenesList.add(orden2);
                    ordenesList.add(orden3);
        

                    Operacion operacionSecado = new Operacion("Doblado",null,ordenesList,null,null,null );
                    ordenesList.forEach(orden -> orden.setOperacion(operacionSecado));
                    
                    op.setIsNextProcessAdded(true);
                    if(operacionDao.updateOperacion(op)){
                        System.out.println("Actualizado el objeto persistente");
                    }else{
                        System.out.println("Retorno false");
                    }
                    
                    
                    operacionDao.addOperacion(operacionSecado);
                    
                    //cambiar la propiedad a true para que ya no se agrege en la proxima carga
                    
                }else{
                    System.err.println("Parece que esta dando false");
                }
                System.out.println(op.getIsNextProcessAdded());
            }
        }
    }
}
