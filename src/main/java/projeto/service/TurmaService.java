package projeto.service;

import projeto.business.TurmaBusiness;
import projeto.dto.TurmaDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TurmaService {

    @Inject
    private TurmaBusiness turmaBusiness;

    public void cadastrar(TurmaDTO turmaDTO) throws Exception {
        turmaBusiness.cadastrar(turmaDTO);
    }
}
