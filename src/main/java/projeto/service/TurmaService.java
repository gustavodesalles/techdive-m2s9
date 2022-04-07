package projeto.service;

import projeto.business.TurmaBusiness;
import projeto.dto.TurmaDTO;
import projeto.exception.BusinessException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TurmaService {

    @Inject
    private TurmaBusiness turmaBusiness;

    public void cadastrar(TurmaDTO turmaDTO) throws BusinessException {
        turmaBusiness.cadastrar(turmaDTO);
    }

    public TurmaDTO consultarDadosTurma(Long idTurma) throws BusinessException {
        return turmaBusiness.consultarDadosTurma(idTurma);
    }
}
