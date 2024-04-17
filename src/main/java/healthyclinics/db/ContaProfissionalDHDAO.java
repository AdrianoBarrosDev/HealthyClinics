
package healthyclinics.db;

import healthyclinics.tablesData.ContaProfissional;
import healthyclinics.tablesData.ContaProfissionalDH;
import healthyclinics.tablesData.DiaHorario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ContaProfissionalDHDAO {

    public boolean salvar(ContaProfissionalDH contaDia) {
        
        boolean status = false;
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(contaDia);
            em.getTransaction().commit();
            status = true;
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return status;
        
    }
    
    
    public ContaProfissionalDH get(ContaProfissional contaProfissional, DiaHorario diaHorario) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT cd FROM ContaProfissionalDH cd JOIN ContaProfissional cp ON cd.contaProfissional.id = cp.id JOIN DiaHorario dh ON cd.diaHorario.id = dh.id WHERE cp.id = :cid AND dh.id = :did");
            consulta.setParameter("cid", contaProfissional.getId());
            consulta.setParameter("did", diaHorario.getId());
            return (ContaProfissionalDH) consulta.getSingleResult();
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public List<ContaProfissionalDH> getAgenda(ContaProfissional contaProfissional) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT cd FROM ContaProfissionalDH cd JOIN ContaProfissional cp ON cd.contaProfissional.id = cp.id JOIN DiaHorario dh ON cd.diaHorario.id = dh.id WHERE cp.id = :cid");
            consulta.setParameter("cid", contaProfissional.getId());
            return consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public boolean remover(ContaProfissionalDH ch) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            List<ContaProfissionalDH> agenda = getAgenda(ch.getContaProfissional());
            for(ContaProfissionalDH dh : agenda) {
                if(dh != null) {
                    em.getTransaction().begin();
                    em.remove(dh);
                    em.getTransaction().commit();
                }
            }
            return true;
            
        } catch(Exception ex) {
            em.getTransaction().rollback();
            return false;
            
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public void atualizar(DiaHorario dh) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(dh);
            em.getTransaction().commit();
        } catch(Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
}
