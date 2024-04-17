
package healthyclinics.db;

import healthyclinics.tablesData.TipoConta;
import jakarta.persistence.EntityManager;

public class TipoContaDAO {
    
    
    public TipoConta consultar (int id) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(TipoConta.class, id);
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
}
