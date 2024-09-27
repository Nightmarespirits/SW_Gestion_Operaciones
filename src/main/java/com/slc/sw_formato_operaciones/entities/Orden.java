/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slc.sw_formato_operaciones.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Clider Fernando Tutaya Rivera
 */


@Entity
@Table(name = "Registro_Ordenes")
public class Orden {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numOrden;
    
    private int cantPrendas;
    
    @ManyToOne
    @JoinColumn(name = "operacion_id")
    private Operacion operacion;

    public Orden() {
    }

    public Orden(String numOrden, int cantPrendas, Operacion operacion) {
        this.numOrden = numOrden;
        this.cantPrendas = cantPrendas;
        this.operacion = operacion;
    }

    public Orden(String numOrden, int cantPrendas) {
        this.numOrden = numOrden;
        this.cantPrendas = cantPrendas;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(String numOrden) {
        this.numOrden = numOrden;
    }

    public int getCantPrendas() {
        return cantPrendas;
    }

    public void setCantPrendas(int cantPrendas) {
        this.cantPrendas = cantPrendas;
    }
        public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }
    
    
}
