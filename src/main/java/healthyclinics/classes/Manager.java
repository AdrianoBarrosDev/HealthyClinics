
package healthyclinics.classes;

import healthyclinics.dados.DadosIniciais;


public class Manager {
    
    private final ConfigFrame configFrame;
    private final Telas telas;
    private final Fonts fonts;
    
    
    public Manager() {
        this.configFrame = new ConfigFrame(this);
        this.telas = new Telas(configFrame);
        this.fonts = new Fonts();
        
        // Inicializa dados necessários
        DadosIniciais dados = new DadosIniciais(this);
    }

    public ConfigFrame getConfigFrame() {
        return configFrame;
    }

    public Telas getTelas() {
        return telas;
    }

    public Fonts getFonts() {
        return fonts;
    }
    
    
    public void iniciarSistema() {
        telas.getTelaInicial().configTelaInicial();
    }
    
    
}
