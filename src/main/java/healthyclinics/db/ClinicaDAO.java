
package healthyclinics.db;

import healthyclinics.tablesData.Clinica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ClinicaDAO {
    
    public ClinicaDAO() {
        
    }
    
    
    public boolean salvar(Clinica clinica) {
        
        boolean status = false;
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(clinica);
            em.getTransaction().commit();
            status = true;
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return status;
        
    }
    
    
    public Clinica get(String cnpj) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT c FROM Clinica c WHERE cnpj = :cnpj");
            consulta.setParameter("cnpj", cnpj);
            if(consulta.getSingleResult() == null) {
                return null;
            }
            return (Clinica) consulta.getSingleResult();
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public Clinica get(int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Clinica.class, id);
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public boolean remover(int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Clinica c = em.find(Clinica.class, id);
            if(c != null) {
                em.getTransaction().begin();
                em.remove(c);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
            
        } catch(Exception ex) {
            em.getTransaction().rollback();
            return false;
            
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public void atualizar(Clinica c) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } catch(Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public List<Clinica> getListaClinicas() {
        
        EntityManager em = JPAUtil.getEntityManager();
        List<Clinica> listaClinicas = null;
        try {
            Query consulta = em.createQuery("SELECT c FROM Clinica c");
            listaClinicas = consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return listaClinicas;
        
    }
    
    
}
