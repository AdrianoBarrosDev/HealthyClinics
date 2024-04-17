
package healthyclinics.db;

import healthyclinics.tablesData.Horario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class HorarioDAO {
    
    
    public Horario get(String horario) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT h FROM Horario h WHERE h.horario = :horario");
            consulta.setParameter("horario", horario);
            return (Horario) consulta.getSingleResult();
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
}
