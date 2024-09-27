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
import com.slc.sw_formato_operaciones.entities.Orden;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class OrdenDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("slc_AppPU");
    
    // Crear
    public void addOrden(Orden orden) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(orden);
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
    public Orden getOrdenById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Orden.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Orden> getAllOrdenes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Orden> query = em.createQuery("SELECT o FROM Orden o", Orden.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // Actualizar
    public void updateOrden(Orden orden) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(orden);
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
    public void deleteOrden(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Orden orden = em.find(Orden.class, id);
            if (orden != null) {
                em.remove(orden);
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
