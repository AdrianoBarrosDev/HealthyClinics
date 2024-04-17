
package healthyclinics.dados;

import healthyclinics.db.EspecialidadeDAO;
import healthyclinics.tablesData.Especialidade;
import java.util.ArrayList;
import java.util.List;

public class ListaEspecialidade {

    private String [] listaEspecialidades;
    
    public ListaEspecialidade() {
        
    }
    
    public ListaEspecialidade(String [] listaEspecialidades) {
        this.listaEspecialidades = listaEspecialidades;
    }

    public String[] getListaEspecialidades() {
        return listaEspecialidades;
    }

    public void setListaEspecialidades(String[] listaEspecialidades) {
        this.listaEspecialidades = listaEspecialidades;
    }
    
    
    public void setEspecialidades(boolean [] especialidades) {
        
        if(listaEspecialidades == null) {
            
            listaEspecialidades = new String [8];
            
            if(especialidades != null) {

                for(int i = 0; i < 8; i++) {

                    if(especialidades[0] == true) {
                        listaEspecialidades[0] = ("Clínica Geral");
                    } else {
                        listaEspecialidades[0] = ("");
                    }
                    
                    if(especialidades[1] == true) {
                        listaEspecialidades[1] = ("Pediatria");
                    } else {
                        listaEspecialidades[1] = ("");
                    }
                    
                    if(especialidades[2] == true) {
                        listaEspecialidades[2] = ("Cardiologia");
                    } else {
                        listaEspecialidades[2] = ("");
                    }
                    
                    if(especialidades[3] == true) {
                        listaEspecialidades[3] = ("Oftalmologia");
                    } else {
                        listaEspecialidades[3] = ("");
                    }
                    
                    if(especialidades[4] == true) {
                        listaEspecialidades[4] = ("Odontologia");
                    } else {
                        listaEspecialidades[4] = ("");
                    }
                    
                    if(especialidades[5] == true) {
                        listaEspecialidades[5] = ("Ortopedia");
                    } else {
                        listaEspecialidades[5] = ("");
                    }
                    
                    if(especialidades[6] == true) {
                        listaEspecialidades[6] = ("Radiologia");
                    } else {
                        listaEspecialidades[6] = ("");
                    }
                    
                    if(especialidades[7] == true) {
                        listaEspecialidades[7] = ("Neurologia");
                    } else {
                        listaEspecialidades[7] = ("");
                    }

                }

            }
            
        }  
        
    }
    
    
    
    public List<Especialidade> getEspecialidades() {
        
        EspecialidadeDAO dao = new EspecialidadeDAO();
        List<Especialidade> list = new ArrayList<>();
        
        for(String especialidade : listaEspecialidades) {
            if(especialidade != null) {
                if(!especialidade.equals("")) {
                    list.add(dao.get(especialidade));
                }
            }
        }
        return list;
        
    }

    
    
    @Override
    public String toString() {
        
        String especialidades = "";
        for(String text : listaEspecialidades) {
            
            if(especialidades.equals("")) {
                especialidades = text;
            } else {
                if(text != null) {
                    if(!text.equals("")) {
                        especialidades = especialidades + ", " + text;
                    }
                }   
            }
            
        }
        return especialidades;
        
    }
    
    
}
