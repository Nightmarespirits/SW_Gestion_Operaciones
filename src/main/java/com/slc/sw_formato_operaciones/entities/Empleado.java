/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slc.sw_formato_operaciones.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author Clider Fernando Tutaya Rivera
 */

@Entity
@Table(name = "Registro_Empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private String apellidos;
    private String nombres;
    private String numDoc;
    
    @OneToMany(mappedBy = "responsable")
    private List<Operacion> operaciones;

    public Empleado(String apellidos, String nombres, String numDoc) {
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.numDoc = numDoc;
    }

    public Empleado() {
    }

    public Empleado(Long id, String apellidos, String nombres, String numDoc) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.numDoc = numDoc;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }
    
    
    
}
