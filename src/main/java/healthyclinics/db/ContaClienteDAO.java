
package healthyclinics.db;

import healthyclinics.tablesData.Conta;
import healthyclinics.tablesData.ContaCliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ContaClienteDAO {
    
    
    public boolean salvar(ContaCliente conta) {
        
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
    
    
    public ContaCliente get(int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ContaCliente.class, id);
        }finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public ContaCliente getConta(Conta conta) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT cc FROM ContaCliente cc JOIN Conta c ON cc.conta.id = c.id WHERE cc.conta.id = :contaId");
            consulta.setParameter("contaId", conta.getId());
            return (ContaCliente) consulta.getSingleResult();
        }finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public boolean excluir(int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            ContaCliente c = em.find(ContaCliente.class, id);
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
    
    
    public void atualizar(ContaCliente conta) {
        
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
    
    
    public List<ContaCliente> getListaContasClientes() {
        
        EntityManager em = JPAUtil.getEntityManager();
        List<ContaCliente> listaContasClientes = null;
        try {
            Query consulta = em.createQuery("SELECT c FROM ContaCliente c");
            listaContasClientes = consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return listaContasClientes;
        
    }
    
    
}
