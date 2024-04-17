
package healthyclinics.telas;

import healthyclinics.classes.ConfigFrame;

public class TelaPerfil {
    
    private PerfilCliente perfilCliente;
    private PerfilProfissional perfilProfissional;
    
    public TelaPerfil(ConfigFrame configFrame) {
        perfilCliente = new PerfilCliente(configFrame);
        perfilProfissional = new PerfilProfissional(configFrame);
    }

    public PerfilCliente getPerfilCliente() {
        return perfilCliente;
    }

    public void setPerfilPaciente(PerfilCliente perfilCliente) {
        this.perfilCliente = perfilCliente;
    }

    public PerfilProfissional getPerfilProfissional() {
        return perfilProfissional;
    }

    public void setPerfilProfissional(PerfilProfissional perfilProfissional) {
        this.perfilProfissional = perfilProfissional;
    }
    
}
