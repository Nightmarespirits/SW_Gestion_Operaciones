/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.slc.sw_formato_operaciones.DAO;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.slc.sw_formato_operaciones.entities.Operacion;
import com.slc.sw_formato_operaciones.entities.Orden;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Clider Fernando Tutaya Rivera
 */
public class OperacionDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("slc_AppPU");
    
    // Crear (Create)
    public boolean addOperacion(Operacion operacion) {
        boolean band = false;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Usar merge para entidades ya existentes
            if (operacion.getMaquina() != null) {
                operacion.setMaquina(em.merge(operacion.getMaquina()));
            }
            if (operacion.getLocal() != null) {
                operacion.setLocal(em.merge(operacion.getLocal()));
            }
            if (operacion.getResponsable() != null) {
                operacion.setResponsable(em.merge(operacion.getResponsable()));
            }
           

            // Persistir la operación
            em.persist(operacion);
            tx.commit();
            band = true;
        } catch (Exception e) {
            
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return band;
    }
 
    // Leer (Read)
    public Operacion getOperacionById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Operacion.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Operacion> getOperacionesByType(String tipoOperacion){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Operacion> query = em.createQuery("SELECT o FROM Operacion o WHERE o.tipoOperacion = :tipoOperacion ORDER BY o.fecha DESC", Operacion.class)
                    .setParameter("tipoOperacion", tipoOperacion);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Operacion> getOperacionesByRangeDate(LocalDateTime rango1, LocalDateTime rango2){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Operacion> query = em.createQuery(
            "SELECT o FROM Operacion o WHERE o.fecha BETWEEN :rango1 AND :rango2", Operacion.class)
            .setParameter("rango1", rango1)
            .setParameter("rango2", rango2);
            
            return query.getResultList();
            
        } finally {
            em.close();
        }
    }
    
    public List<Operacion> getAllOperaciones() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Operacion> query = em.createQuery("SELECT o FROM Operacion o ORDER BY o.fecha DESC", Operacion.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Operacion> getOperacionesByTypeAndState(String tipoOperacion, Boolean estado){
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Operacion> query = em.createQuery("SELECT o FROM Operacion o WHERE o.tipoOperacion = :tipoOperacion AND o.estadoOperacion = :estado ORDER BY o.fecha DESC", Operacion.class)
                    .setParameter("tipoOperacion", tipoOperacion)
                    .setParameter("estado", estado);
            return query.getResultList();
        }
    }
    
    public boolean updateOperacion(Operacion operacion) {
        boolean band = false;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Operacion updateOp = em.find(Operacion.class, operacion.getId());
            if (updateOp != null) {
                // Actualizar los campos de la operación
                updateOp.setFecha(LocalDateTime.now());
                updateOp.setTipoOperacion(operacion.getTipoOperacion());
                updateOp.setLocal(operacion.getLocal());
                updateOp.setMaquina(operacion.getMaquina());
                updateOp.setResponsable(operacion.getResponsable());
                updateOp.setEstadoOperacion(operacion.getEstadoOperacion());

                // Manejo de la colección de órdenes
                List<Orden> nuevasOrdenes = operacion.getOrdenes();
                List<Orden> ordenesActuales = new ArrayList<>(updateOp.getOrdenes());

                for (Orden nuevaOrden : nuevasOrdenes) {
                    Orden ordenExistente = ordenesActuales.stream()
                        .filter(o -> o.getNumOrden().equals(nuevaOrden.getNumOrden()))
                        .findFirst()
                        .orElse(null);

                    if (ordenExistente != null) {
                        // Actualizar orden existente
                        ordenExistente.setCantPrendas(nuevaOrden.getCantPrendas());
                        // Actualizar otros campos si es necesario
                    } else {
                        // Añadir nueva orden
                        nuevaOrden.setOperacion(updateOp);
                        updateOp.getOrdenes().add(nuevaOrden);
                    }
                }

                // Eliminar órdenes que ya no están en la nueva lista
                updateOp.getOrdenes().removeIf(orden -> 
                    nuevasOrdenes.stream().noneMatch(o -> o.getNumOrden().equals(orden.getNumOrden())));
                updateOp.setIsNextProcessAdded(operacion.getIsNextProcessAdded());
                em.merge(updateOp);
                band = true;
            } else {
                System.out.println("Error: no existe la operación");
            }

            tx.commit();
        } catch (Exception e) {
            band = false;
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

        return band;
    }
    // Eliminar (Delete)
    public void deleteOperacion(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Operacion operacion = em.find(Operacion.class, id);
            if (operacion != null) {
                em.remove(operacion);
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
    
    //Eliminar operaciones completadas (Limpiar aplicacion)
    public boolean deleteOperacionesFinalizadas(){
        boolean band = false;
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            int deleteCount =  em.createQuery("DELETE FROM Operacion o WHERE o.estadoOperacion = true").executeUpdate();
           
            tx.commit();
            band = deleteCount > 0;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            band = false;
        } finally {
            em.close();
        }
        
        return band;
    }

    public int getMaxIdOperacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
