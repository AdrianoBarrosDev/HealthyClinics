
package healthyclinics.db;

import healthyclinics.tablesData.Clinica;
import healthyclinics.tablesData.Conta;
import healthyclinics.tablesData.ContaProfissional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ContaProfissionalDAO {
    
    
    public boolean salvar(ContaProfissional conta) {
        
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
    
    
    
    public ContaProfissional get(int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(ContaProfissional.class, id);
        }finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public ContaProfissional getConta(Conta conta) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT cp FROM ContaProfissional cp JOIN Conta c ON cp.conta.id = c.id WHERE cp.conta.id = :contaId");
            consulta.setParameter("contaId", conta.getId());
            return (ContaProfissional) consulta.getSingleResult();
        }finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    
    public boolean excluir(String cpf) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            ContaProfissional c = em.find(ContaProfissional.class, cpf);
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
    
    
    public void atualizar(ContaProfissional conta) {
        
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
    
    
    public List<ContaProfissional> getContasProfissionais() {
        
        EntityManager em = JPAUtil.getEntityManager();
        List<ContaProfissional> contasProfissionais = null;
        try {
            Query consulta = em.createQuery("SELECT cp FROM ContaProfissional cp JOIN Conta c ON cp.conta.id = c.id");
            contasProfissionais = consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return contasProfissionais;
        
    }
    
    
    public List<ContaProfissional> getListaMedicos(Clinica clinica) {
        
        EntityManager em = JPAUtil.getEntityManager();
        List<ContaProfissional> contasProfissionais = null;
        try {
            Query consulta = em.createQuery("SELECT cp FROM ContaProfissional cp JOIN Clinica c ON cp.clinica.id = c.id WHERE c.id = :id");
            consulta.setParameter("id", clinica.getId());
            contasProfissionais = consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return contasProfissionais;
        
    }
    
    
}
