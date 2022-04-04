package projeto.bean;

import org.omnifaces.cdi.ViewScoped;
import projeto.entity.Role;
import projeto.entity.Usuario;
import projeto.security.UserAuthenticationToken;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("homeWebBean")
public class HomeWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserAuthenticationToken usuarioLogado;

    private String texto;

    private boolean apenasVisualizarCampoDeTexto;

    @PostConstruct
    public void init() {
        texto = "Hello, world!";
        apenasVisualizarCampoDeTexto = true;
    }

    public boolean isAdmin() {
        return usuarioLogado.possuiRole(Role.ADMIN);
    }

    public boolean isRenderizarCampoDeTexto() {
        return true;
    }

    public boolean desabilitarCampoDeTexto() {
        return false;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isApenasVisualizarCampoDeTexto() {
        return apenasVisualizarCampoDeTexto;
    }

    public void setApenasVisualizarCampoDeTexto(boolean apenasVisualizarCampoDeTexto) {
        this.apenasVisualizarCampoDeTexto = apenasVisualizarCampoDeTexto;
    }
}
