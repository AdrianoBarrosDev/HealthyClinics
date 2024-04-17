
package healthyclinics.db;

import healthyclinics.tablesData.Dia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.sql.Date;
import java.util.List;

public class DiaDAO {
    
    
    public boolean salvar(Dia dia) {
        
        boolean status = false;
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            em.getTransaction().begin();
            em.persist(dia);
            em.getTransaction().commit();
            status = true;
            
        } catch(Exception ex) {
            em.getTransaction().rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return status;
        
    }
    
    
    public Dia get(int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Dia.class, id);
        }finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public Dia get(Date dia) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT d FROM Dia d WHERE d.dia = :dia");
            consulta.setParameter("dia", dia);
            Dia d = (Dia) consulta.getSingleResult();
            if(d != null) {
                return d;
            }
            return null;
        }finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public boolean excluir(Date dia) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            Dia c = em.find(Dia.class, dia);
            if(c != null) {
                em.getTransaction().begin();
                em.remove(c);
                em.getTransaction().commit();
            }
            return true;
            
        } catch(Exception ex) {
            em.getTransaction().rollback();
            return false;
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public void atualizar(Dia dia) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(dia);
            em.getTransaction().commit();
        } catch(Exception ex) {
            em.getTransaction().rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public List<Dia> getListaDias() {
        
        EntityManager em = JPAUtil.getEntityManager();
        List<Dia> listaDias = null;
        try {
            Query consulta = em.createQuery("SELECT d FROM Dia d");
            listaDias = consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return listaDias;
        
    }
    
    
}
