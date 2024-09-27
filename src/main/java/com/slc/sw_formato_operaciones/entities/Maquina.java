/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slc.sw_formato_operaciones.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;


/**
 *
 * @author Clider Fernando Tutaya Rivera
 */

@Entity
@Table(name = "Registro_Maquinas")
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String tipoMaquina;
    
    @OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL)
    private List<Operacion> operaciones;
    
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;
    
    public Maquina() {
    }

    public Maquina(Long id, String name, String tipoMaquina, Local local) {
        this.id = id;
        this.name = name;
        this.tipoMaquina = tipoMaquina;
        this.local = local;
    }

    public Maquina(String name, String tipoMaquina, Local local) {
        this.name = name;
        this.tipoMaquina = tipoMaquina;
        this.local = local;
    }

    public Maquina(String name, String tipoMaquina) {
        this.name = name;
        this.tipoMaquina = tipoMaquina;
    }

    public List<Operacion> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(List<Operacion> operaciones) {
        this.operaciones = operaciones;
    }

    
    
    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipoMaquina() {
        return tipoMaquina;
    }

    public void setTipoMaquina(String tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }
    
    
}
