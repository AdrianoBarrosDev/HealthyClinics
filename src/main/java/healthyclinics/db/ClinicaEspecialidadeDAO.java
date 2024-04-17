
package healthyclinics.db;

import healthyclinics.dados.ListaEspecialidade;
import healthyclinics.tablesData.Clinica;
import healthyclinics.tablesData.ClinicaEspecialidade;
import healthyclinics.tablesData.Especialidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ClinicaEspecialidadeDAO {

    public boolean salvar(ClinicaEspecialidade clinica) {
        
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
    
    
    public List<ClinicaEspecialidade> getLista(Clinica clinica) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT ce FROM ClinicaEspecialidade ce JOIN clinica c ON ce.clinica.id = c.id JOIN especialidade e ON ce.especialidade.id = e.id WHERE ce.clinica.id = :id");
            consulta.setParameter("id", clinica.getId());
            return consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public ClinicaEspecialidade get(Clinica clinica, Especialidade especialidade) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT ce.id FROM ClinicaEspecialidade ce JOIN clinica c ON ce.clinica.id = c.id JOIN especialidade e ON ce.especialidade.id = e.id WHERE c.id = :cid AND e.id = :eid");
            consulta.setParameter("cid", clinica.getId());
            consulta.setParameter("eid", especialidade.getId());
            int id = (int) consulta.getSingleResult();
            return em.find(ClinicaEspecialidade.class, id);
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public boolean remover(Clinica clinica, Especialidade especialidade) {
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            
            ClinicaEspecialidade c = em.find(ClinicaEspecialidade.class, especialidade);
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
    
    
    public boolean limpar(Clinica clinica) {
        
        List<ClinicaEspecialidade> lista = getLista(clinica);
        
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            for(ClinicaEspecialidade ce : lista) {
                em.remove(em.contains(ce) ? ce : em.merge(ce));
            }
            em.getTransaction().commit();
            return true;
            
        } catch(Exception ex) {
            em.getTransaction().rollback();
            return false;
            
        } finally {
            JPAUtil.closeEntityManager();
        }
        
    }
    
    
    public void atualizar(ClinicaEspecialidade c) {
        
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
    
    
    public ListaEspecialidade getListaEspecialidades(Clinica clinica) {
        
        EntityManager em = JPAUtil.getEntityManager();
        ListaEspecialidade listaEspecialidades = new ListaEspecialidade();
        
        try {
            Query consulta = em.createQuery("SELECT e.descricao FROM ClinicaEspecialidade ce LEFT JOIN clinica c ON ce.clinica.id = c.id JOIN especialidade e ON ce.especialidade.id = e.id WHERE c.id = :id");
            consulta.setParameter("id", clinica.getId());
            List<String> especialidades = consulta.getResultList();
            
            boolean[] descricaoEspecialidades = new boolean[8];
            for(int i = 0; i < especialidades.size(); i++) {
                
                switch(especialidades.get(i)) {
                    
                    case "Clínica Geral":
                        descricaoEspecialidades[0] = true;
                        break;
                    case "Pediatria":
                        descricaoEspecialidades[1] = true;
                        break;
                    case "Cardiologia":
                        descricaoEspecialidades[2] = true;
                        break;
                    case "Oftalmologia":
                        descricaoEspecialidades[3] = true;
                        break;
                    case "Odontologia":
                        descricaoEspecialidades[4] = true;
                        break;
                    case "Ortopedia":
                        descricaoEspecialidades[5] = true;
                        break;
                    case "Radiologia":
                        descricaoEspecialidades[6] = true;
                        break;
                    case "Neurologia":
                        descricaoEspecialidades[7] = true;
                        break;
                    
                }
                
            }
            
            listaEspecialidades.setEspecialidades(descricaoEspecialidades);
            
        } finally {
            JPAUtil.closeEntityManager();
        }
        return listaEspecialidades;
        
    }
    
    
}
