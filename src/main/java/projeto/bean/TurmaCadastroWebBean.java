package projeto.bean;

import org.omnifaces.cdi.ViewScoped;
import projeto.dto.TurmaDTO;
import projeto.service.TurmaService;
import projeto.utils.MessageUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("turmaCadastroWebBean")
public class TurmaCadastroWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TurmaService turmaService;

    @Inject
    private Long idTurma;

    private TurmaDTO turmaDTO = new TurmaDTO();

    public void cadastrar() {
        try {
            turmaService.cadastrar(turmaDTO);
            MessageUtils.returnMessageOnSuccess("Salvo com sucesso!");
        } catch (Exception e) {
            MessageUtils.returnMessageOnFail(e.getMessage());
        }
    }

    public TurmaDTO getTurmaDTO() {
        return turmaDTO;
    }

    public void setTurmaDTO(TurmaDTO turmaDTO) {
        this.turmaDTO = turmaDTO;
    }
}
