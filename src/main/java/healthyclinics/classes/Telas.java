
package healthyclinics.classes;

import healthyclinics.telas.Home;
import healthyclinics.telas.TelaCadastrarCliente;
import healthyclinics.telas.TelaCadastrarProfissional;
import healthyclinics.telas.TelaClinicas;
import healthyclinics.telas.TelaConsultas;
import healthyclinics.telas.TelaContinuacaoProjeto;
import healthyclinics.telas.TelaEscolha;
import healthyclinics.telas.TelaGerenciarClinica;
import healthyclinics.telas.TelaInicial;
import healthyclinics.telas.TelaLogin;
import healthyclinics.telas.TelaPerfil;
import healthyclinics.telas.TelaSobre;

public class Telas {

    private final TelaInicial telaInicial;
    private final TelaEscolha telaEscolha;
    private final TelaLogin telaLogin;
    private final TelaCadastrarCliente cadastroCliente;
    private final TelaCadastrarProfissional cadastroProfissional;
    private final Home telaHome;
    private final TelaConsultas telaConsultas;
    private final TelaClinicas telaClinicas;
    private final TelaGerenciarClinica telaGerenciarClinica;
    private final TelaSobre telaSobre;
    private final TelaPerfil telaPerfil;
    private final TelaContinuacaoProjeto telaContinuacao;
    
    
    public Telas(ConfigFrame configFrame) {
        
        telaInicial = new TelaInicial(configFrame);
        telaEscolha = new TelaEscolha(configFrame);
        telaLogin = new TelaLogin(configFrame);
        cadastroCliente = new TelaCadastrarCliente(configFrame);
        cadastroProfissional = new TelaCadastrarProfissional(configFrame);
        telaHome = new Home(configFrame);
        telaConsultas = new TelaConsultas(configFrame);
        telaClinicas = new TelaClinicas(configFrame);
        telaGerenciarClinica = new TelaGerenciarClinica(configFrame);
        telaSobre = new TelaSobre(configFrame);
        telaPerfil = new TelaPerfil(configFrame);
        telaContinuacao = new TelaContinuacaoProjeto(configFrame);
    }

    
    public TelaInicial getTelaInicial() {
        return telaInicial;
    }
    
    public TelaEscolha getTelaEscolha() {
        return telaEscolha;
    }

    public TelaLogin getTelaLogin() {
        return telaLogin;
    }

    public TelaCadastrarCliente getCadastroCliente() {
        return cadastroCliente;
    }

    public TelaCadastrarProfissional getCadastroProfissional() {
        return cadastroProfissional;
    }

    public Home getTelaHome() {
        return telaHome;
    }

    public TelaConsultas getTelaConsultas() {
        return telaConsultas;
    }

    public TelaClinicas getTelaClinicas() {
        return telaClinicas;
    }

    public TelaGerenciarClinica getTelaGerenciarClinica() {
        return telaGerenciarClinica;
    }
    
    public TelaSobre getTelaSobre() {
        return telaSobre;
    }

    public TelaPerfil getTelaPerfil() {
        return telaPerfil;
    }

    public TelaContinuacaoProjeto getTelaContinuacao() {
        return telaContinuacao;
    }

    
}
