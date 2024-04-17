
package healthyclinics.db;

import healthyclinics.tablesData.Consulta;
import healthyclinics.tablesData.ContaCliente;
import healthyclinics.tablesData.ContaProfissional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ConsultaDAO {
    
    public ConsultaDAO() {
        
    }
    
    
    public boolean salvar(Consulta consulta) {
        
        boolean status = false;
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(consulta);
            em.getTransaction().commit();
            status = true;
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return status;
        
    }
    
    
    public Consulta get(int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Consulta.class, id);
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public Consulta get(ContaCliente paciente) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Consulta.class, paciente);
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public Consulta get(ContaProfissional medico) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Consulta.class, medico);
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public boolean remover(int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            Consulta c = em.find(Consulta.class, id);
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
    
    
    public void atualizar(Consulta c) {
        
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
    
    
    public List<Consulta> getListaConsultas() {
        
        EntityManager em = JPAUtil.getEntityManager();
        List<Consulta> listaConsultas = null;
        try {
            Query consulta = em.createQuery("SELECT c FROM Consulta c");
            listaConsultas = consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return listaConsultas;
        
    }
    
    
    public List<Consulta> getListaConsultas(ContaCliente paciente) {
        
        EntityManager em = JPAUtil.getEntityManager();
        List<Consulta> listaConsultas = null;
        try {
            Query consulta = em.createQuery("SELECT c FROM Consulta c JOIN ContaCliente cc ON c.paciente.id = cc.id WHERE cc.id = :id");
            consulta.setParameter("id", paciente.getId());
            listaConsultas = consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return listaConsultas;
        
    }
    
    
    public List<Consulta> getListaConsultas(ContaProfissional medico) {
        
        EntityManager em = JPAUtil.getEntityManager();
        List<Consulta> listaConsultas = null;
        try {
            Query consulta = em.createQuery("SELECT c FROM Consulta c JOIN ContaCliente cc ON c.paciente.id = cc.id JOIN clinica cl ON c.clinica.id = cl.id JOIN ContaProfissional cp ON c.medico.id = cp.id JOIN DiaHorario dh ON c.diaHorario.id = dh.id WHERE cl.id = :id");
            consulta.setParameter("id", medico.getClinica().getId());
            listaConsultas = consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return listaConsultas;
        
    }
    
    
}
