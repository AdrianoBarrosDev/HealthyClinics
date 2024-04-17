
package healthyclinics.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    //constante para centralizar o nome da unidade de persistência
    // se o nome mudar, precisaremos alterar em um só lugar
    private static final String PERSISTENCE_UNIT = "HealthyClinics-PU";
    
    private static EntityManager em;
    private static EntityManagerFactory fabrica;
    
    // cria a entidade se estiver nula e a retorna
    public static EntityManager getEntityManager() {
        
        if(fabrica == null || !fabrica.isOpen()) {
            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        
        if(em == null || !em.isOpen()) {
            em = fabrica.createEntityManager();
        }
        
        return em;
        
    }
    
    //fecha o EntityManager e o factory
    public static void closeEntityManager() {
        if(em.isOpen() && em != null) {
            em.close();
            fabrica.close();
        }
    }
    
}
