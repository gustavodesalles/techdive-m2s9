package projeto.bean;

import org.omnifaces.cdi.ViewScoped;
import projeto.dto.EstudanteDTO;
import projeto.dto.FiltroEstudanteDTO;
import projeto.dto.TurmaDTO;
import projeto.exception.BusinessException;
import projeto.service.EstudanteService;
import projeto.service.TurmaService;
import projeto.utils.MessageUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("buscaEstudanteWebBean")
public class BuscaEstudanteWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TurmaService turmaService;

    @Inject
    private EstudanteService estudanteService;

    private FiltroEstudanteDTO filtro = new FiltroEstudanteDTO();

    private List<EstudanteDTO> estudantesEncontrados = new ArrayList<>();

    private boolean buscaFeita;

    public void buscar() {
        try {
            estudantesEncontrados = estudanteService.buscar(filtro);
            buscaFeita = true;
        } catch (BusinessException e) {
            MessageUtils.returnMessageOnFail(e.getErros());
        }
    }

    public List<TurmaDTO> consultarTurmaPorNomeOuMatricula(Object query) {
        return turmaService.consultarTurmaPorNomeOuMatricula(query.toString());
    }

    public FiltroEstudanteDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroEstudanteDTO filtro) {
        this.filtro = filtro;
    }

    public List<EstudanteDTO> getEstudantesEncontrados() {
        return estudantesEncontrados;
    }

    public void setEstudantesEncontrados(List<EstudanteDTO> estudantesEncontrados) {
        this.estudantesEncontrados = estudantesEncontrados;
    }

    public boolean isBuscaFeita() {
        return buscaFeita;
    }

    public void setBuscaFeita(boolean buscaFeita) {
        this.buscaFeita = buscaFeita;
    }
}
