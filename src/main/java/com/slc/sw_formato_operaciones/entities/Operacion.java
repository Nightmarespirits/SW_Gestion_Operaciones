/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slc.sw_formato_operaciones.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Clider Fernando Tutaya Rivera
 */
@Entity
@Table(name = "Registro_Operaciones")
public class Operacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tipoOperacion;
    
    private LocalDateTime fecha;
    
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;
    
    @OneToMany(mappedBy = "operacion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Orden> ordenes;
    
    
    @ManyToOne()
    @JoinColumn(name = "maquina_id")
    private Maquina maquina;
    
    
    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Empleado responsable;
    
    private Boolean estadoOperacion;
    private Boolean isNextProcessAdded;
    
    @PrePersist
    public void prePersist(){
        fecha = LocalDateTime.now();
        isNextProcessAdded = Boolean.FALSE;
    }
    
    public Operacion(String tipoOperacion, Local local, List<Orden> ordenes, Maquina maquina, Empleado responsable, Boolean estado) {
      
        this.tipoOperacion = tipoOperacion;
        this.local = local;
        this.ordenes = ordenes;
        this.maquina = maquina;
        this.responsable = responsable;
        this.estadoOperacion = estado;
    }

    public Operacion(Long id, String tipoOperacion, Local local, List<Orden> ordenes, Maquina maquina, Empleado responsable, Boolean estadoOperacion) {
        this.id = id;
        this.tipoOperacion = tipoOperacion;
        this.local = local;
        this.ordenes = ordenes;
        this.maquina = maquina;
        this.responsable = responsable;
        this.estadoOperacion = estadoOperacion;
    }
    
    public Operacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    public Boolean getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(Boolean estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public Boolean getIsNextProcessAdded() {
        return isNextProcessAdded;
    }

    public void setIsNextProcessAdded(Boolean isNextProcessAdded) {
        this.isNextProcessAdded = isNextProcessAdded;
    }
    
    
    
          
    
}
