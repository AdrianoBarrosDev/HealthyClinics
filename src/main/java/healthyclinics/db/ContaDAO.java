
package healthyclinics.db;

import healthyclinics.tablesData.Conta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ContaDAO {
    
    
    public boolean salvar(Conta conta) {
        
        boolean status = false;
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            em.getTransaction().begin();
            em.persist(conta);
            em.getTransaction().commit();
            status = true;
            
        } catch(Exception ex) {
            em.getTransaction().rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return status;
        
    }
    
    
    public Conta get(int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Conta.class, id);
        }finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public Conta get(String cpf) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT c FROM Conta c WHERE c.cpf = :cpf");
            consulta.setParameter("cpf", cpf);
            Conta conta = (Conta) consulta.getSingleResult();
            if(conta != null) {
                return conta;
            }
            return null;
        }finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public boolean excluir(String cpf) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            Conta c = em.find(Conta.class, cpf);
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
    
    
    public void atualizar(Conta conta) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(conta);
            em.getTransaction().commit();
        } catch(Exception ex) {
            em.getTransaction().rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public List<Conta> getListaContas() {
        
        EntityManager em = JPAUtil.getEntityManager();
        List<Conta> listaContas = null;
        try {
            Query consulta = em.createQuery("SELECT c FROM Conta c");
            listaContas = consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return listaContas;
        
    }
    
    
}
