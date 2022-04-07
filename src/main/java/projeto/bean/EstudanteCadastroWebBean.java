package projeto.bean;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;
import projeto.dto.EstudanteDTO;
import projeto.service.EstudanteService;
import projeto.utils.MessageUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("estudanteCadastroWebBean")
public class EstudanteCadastroWebBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private EstudanteDTO estudanteDTO = new EstudanteDTO();

    @Param(name = "idEstudante")
    private Long idEstudante;

    @Inject
    private EstudanteService estudanteService;

    public EstudanteDTO getEstudanteDTO() {
        return estudanteDTO;
    }

    public void setEstudanteDTO(EstudanteDTO estudanteDTO) {
        this.estudanteDTO = estudanteDTO;
    }
}
