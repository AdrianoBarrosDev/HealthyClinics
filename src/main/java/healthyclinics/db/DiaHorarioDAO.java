
package healthyclinics.db;

import healthyclinics.tablesData.Dia;
import healthyclinics.tablesData.DiaHorario;
import healthyclinics.tablesData.Horario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class DiaHorarioDAO {

    public boolean salvar(DiaHorario clinica) {
        
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
    
    
    public DiaHorario get(Dia dia, Horario horario) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT dh.id FROM DiaHorario dh JOIN Dia d ON dh.dia.id = d.id JOIN horario h ON dh.horario.id = h.id WHERE d.id = :did AND h.id = :hid");
            consulta.setParameter("did", dia.getId());
            consulta.setParameter("hid", horario.getId());
            List<Integer> list = consulta.getResultList();
            for(int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                if(i == (list.size() - 1)) {
                    return em.find(DiaHorario.class, list.get(i));
                }
            }
            int id = (int) consulta.getSingleResult();
            return em.find(DiaHorario.class, id);
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public DiaHorario get(DiaHorario diaHorario) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(DiaHorario.class, diaHorario);
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public boolean remover(Dia dia, Horario horario) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            DiaHorario dh = get(dia, horario);
            if(dh != null) {
                em.getTransaction().begin();
                em.remove(dh);
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
