package projeto.bean;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import projeto.dto.TurmaDTO;
import projeto.exception.BusinessException;
import projeto.service.TurmaService;
import projeto.utils.MessageUtils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("turmaCadastroWebBean")
public class TurmaCadastroWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TurmaService turmaService;

    @Param(name = "idTurma")
    private Long idTurma;

    private TurmaDTO turmaDTO = new TurmaDTO();

    public void inicializar() {
        if (idTurma != null) {
            try {
                turmaDTO = turmaService.consultarDadosTurma(idTurma);
                MessageUtils.limparMensagens();
            } catch (BusinessException e) {
                MessageUtils.returnGlobalMessageOnFail(e.getErros());
                Faces.redirect("/turma.xhtml");
            }
        }
    }

    public void cadastrar() {
        try {
            turmaService.cadastrar(turmaDTO);
            if (idTurma == null) {
                MessageUtils.returnGlobalMessageOnSuccess("Salvo com sucesso!");
                Faces.redirect("/turma.xhtml?idTurma=" + turmaDTO.getIdTurma());
            } else {
                MessageUtils.returnMessageOnSuccess("Salvo com sucesso!");
            }
        } catch (BusinessException e) {
            MessageUtils.returnMessageOnFail(e.getErros());
        } catch (Exception e) {
            MessageUtils.returnMessageOnFail("Ocorreu um erro ao salvar a turma. Por favor, entre em contato com o suporte.");
        }
    }

    public TurmaDTO getTurmaDTO() {
        return turmaDTO;
    }

    public void setTurmaDTO(TurmaDTO turmaDTO) {
        this.turmaDTO = turmaDTO;
    }
}
