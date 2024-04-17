
package healthyclinics.dados;

import healthyclinics.classes.Manager;
import healthyclinics.db.ClinicaDAO;
import healthyclinics.ferramentas.ConvertBlob;
import healthyclinics.tablesData.Clinica;
import java.io.File;

public class DadosIniciais {
    
    private Manager manager;
    
    public DadosIniciais(Manager manager) {
        
        this.manager = manager;
        
        iniciarImagens();
        
    }
    
    
    public final void iniciarImagens() {
        
        ConvertBlob convert = new ConvertBlob();
        ClinicaDAO dao = new ClinicaDAO();
        Clinica clinica = null;
        
        // MayoClinic
        clinica = dao.get("79.712.712/0001-93");
        if(clinica.getImg() == null) {
            clinica.setImg(convert.converterImagem(new File(getClass().getResource("/imagens/MayoClinicOriginal.png").getPath())));
            dao.atualizar(clinica);
        }
        
        // HighClinic
        clinica = dao.get("38.939.398/0001-07");
        if(clinica.getImg() == null) {
            clinica.setImg(convert.converterImagem(new File(getClass().getResource("/imagens/HighClinicOriginal.png").getPath())));
            dao.atualizar(clinica);
        }
        
        // BigClinic
        clinica = dao.get("42.812.151/0001-94");
        if(clinica.getImg() == null) {
            clinica.setImg(convert.converterImagem(new File(getClass().getResource("/imagens/BigClinicOriginal.png").getPath())));
            dao.atualizar(clinica);
        }
        
        // DentristyClinic
        clinica = dao.get("23.694.827/0001-48");
        if(clinica.getImg() == null) {
            clinica.setImg(convert.converterImagem(new File(getClass().getResource("/imagens/DentristyClinicOriginal.png").getPath())));
            dao.atualizar(clinica);
        }
        
        // OfClinic
        clinica = dao.get("00.384.972/0001-27");
        if(clinica.getImg() == null) {
            clinica.setImg(convert.converterImagem(new File(getClass().getResource("/imagens/OfClinicOriginal.png").getPath())));
            dao.atualizar(clinica);
        }
        
        // PediatricsClinic
        clinica = dao.get("29.734.876/0001-43");
        if(clinica.getImg() == null) {
            clinica.setImg(convert.converterImagem(new File(getClass().getResource("/imagens/PediatricsClinicOriginal.png").getPath())));
            dao.atualizar(clinica);
        }
        
    }
    
    
}
