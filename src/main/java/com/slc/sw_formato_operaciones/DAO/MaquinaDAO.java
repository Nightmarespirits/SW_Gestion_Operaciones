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
import com.slc.sw_formato_operaciones.entities.Maquina;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class MaquinaDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("slc_AppPU");
    
    // Crear
    public void addMaquina(Maquina maquina) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(maquina);
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
    public Maquina getMaquinaById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Maquina.class, id);
        } finally {
            em.close();
        }
    }
    
    public Maquina getMaquinaByName(String nombre){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Maquina m WHERE m.name = :nombre", Maquina.class)
                     .setParameter("nombre", nombre)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } finally {
            em.close();
        }
    }
    
    public Maquina getMaquinaByLocalAndTypeAndName(String local , String tipoMaquina, String nombre){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Maquina m WHERE m.local.nombre = :local AND "
                    + "m.tipoMaquina = :tipoMaquina AND "
                    + "m.name = :nombre", Maquina.class)
                     .setParameter("local", local)
                     .setParameter("tipoMaquina", tipoMaquina)
                     .setParameter("nombre", nombre)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } finally {
            em.close();
        }
    }
    public List<Maquina> getMaquinaByLocalAndType(String local, String tipoMaquina){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Maquina> query = em.createQuery("SELECT m FROM Maquina m WHERE LOWER(m.local.nombre) = :local AND LOWER(m.tipoMaquina) = :tipoMaquina", Maquina.class)
                    .setParameter("local", local.toLowerCase())
                    .setParameter("tipoMaquina", tipoMaquina.toLowerCase());
            
            return query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList(); 
        } finally {
            em.close();
        }
    }
    public List<Maquina> getMaquinaByType(String tipoMaquina){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Maquina> query = em.createQuery("SELECT m FROM Maquina m WHERE m.tipoMaquina = :tipoMaquina", Maquina.class)
                     .setParameter("tipoMaquina", tipoMaquina.toLowerCase());
            return query.getResultList();
        } catch (NoResultException e) {
            return null; 
        } finally {
            em.close();
        }
    }
   
    public List<Maquina> getAllMaquinas() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Maquina> query = em.createQuery("SELECT m FROM Maquina m", Maquina.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // Actualizar
    public void updateMaquina(Maquina maquina) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(maquina);
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
    public void deleteMaquina(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Maquina maquina = em.find(Maquina.class, id);
            if (maquina != null) {
                em.remove(maquina);
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