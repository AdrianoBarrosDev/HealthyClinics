
package healthyclinics.db;

import healthyclinics.tablesData.Especialidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class EspecialidadeDAO {
    
    
    public Especialidade get(String descricao) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM Especialidade e WHERE e.descricao = :descricao");
            consulta.setParameter("descricao", descricao);
            return (Especialidade) consulta.getSingleResult();
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
}
