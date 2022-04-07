package projeto.bean;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;
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

    @PostConstruct
    public void init() {
        if (idTurma != null) {
            try {
                turmaDTO = turmaService.consultarDadosTurma(idTurma);
            } catch (BusinessException e) {
                MessageUtils.returnMessageOnFail(e.getErros());
            }
        }
    }

    public void cadastrar() {
        try {
            turmaService.cadastrar(turmaDTO);
            MessageUtils.returnMessageOnSuccess("Salvo com sucesso!");
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
