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
import com.slc.sw_formato_operaciones.entities.Local;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class LocalDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("slc_AppPU");
    
    // Crear
    public void addLocal(Local local) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(local);
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
    public Local getLocalById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Local.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Local> getAllLocales() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Local> query = em.createQuery("SELECT l FROM Local l", Local.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Local getLocalByName(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Local l WHERE l.nombre = :nombre", Local.class)
                     .setParameter("nombre", nombre)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } finally {
            em.close();
        }
    }

    
    // Actualizar
    public void updateLocal(Local local) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(local);
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
    public void deleteLocal(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Local local = em.find(Local.class, id);
            if (local != null) {
                em.remove(local);
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