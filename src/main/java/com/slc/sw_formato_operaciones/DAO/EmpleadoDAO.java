/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slc.sw_formato_operaciones.DAO;

/**
 *
 * @author Clider Fernando Tutaya Rivera
 */

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.slc.sw_formato_operaciones.entities.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class EmpleadoDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("slc_AppPU");
    
    // Crear
    public void addEmpleado(Empleado empleado) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(empleado);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    // Leer
    public Empleado getEmpleadoById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }
    
    public Empleado getEmpleadoByNombresAndApellidos(String nombres, String apellidos){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Empleado e WHERE e.nombres = :nombres AND e.apellidos = :apellidos", Empleado.class)
                     .setParameter("nombres", nombres)
                     .setParameter("apellidos", apellidos)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } finally {
            em.close();
        }
                
    }
    public List<Empleado> getAllEmpleados() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado  e", Empleado.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // Actualizar
    public void updateEmpleado(Empleado empleado) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            Empleado updateEmp = em.find(Empleado.class, empleado.getId());
            if(updateEmp != null){
                updateEmp.setApellidos(empleado.getApellidos());
                updateEmp.setNombres(empleado.getNombres());
                updateEmp.setNumDoc(empleado.getNumDoc());
                em.merge(updateEmp);
            }else{
                System.out.println("Empleado No Encontrado");
            }
            
            
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    // Eliminar
    public void deleteEmpleado(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Empleado empleado = em.find(Empleado.class, id);
            if (empleado != null) {
                em.remove(empleado);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}