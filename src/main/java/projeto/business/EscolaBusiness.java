package projeto.business;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EscolaDTO;
import projeto.entity.Endereco;
import projeto.entity.Escola;
import projeto.exception.BusinessException;
import projeto.repository.EscolaRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EscolaBusiness {

    @Inject
    private EscolaRepository escolaRepository;

    public void cadastrar(EscolaDTO escolaDTO) throws BusinessException {
        validarCadastrar(escolaDTO);
        cadastrarEscolaNoBanco(escolaDTO);
    }

    private void cadastrarEscolaNoBanco(EscolaDTO escolaDTO) throws BusinessException {
        Escola escola;
        if (escolaDTO.getIdEscola() != null) {
            escola = escolaRepository.find(Escola.class, escolaDTO.getIdEscola());
            if (escola == null) {
                throw new BusinessException("Escola não encontrada.");
            }
        } else {
            escola = new Escola();
        }

        escola.setNome(escolaDTO.getNome());
        escola.setCriacao(escolaDTO.getCriacao());
        escola.setEndereco(new Endereco(escolaDTO.getEnderecoDTO()));

        if (escola.getIdEscola() != null) {
            escolaRepository.merge(escola);
        } else {
            escolaRepository.persist(escola);
            escolaDTO.setIdEscola(escola.getIdEscola());
        }
    }

    private void validarCadastrar(EscolaDTO escolaDTO) throws BusinessException {
        List<String> erros = new ArrayList<>();

        if (StringUtils.isBlank(escolaDTO.getNome())) {
            erros.add("O nome da escola é inválido.");
        }

        if (escolaDTO.getEnderecoDTO() == null) {
            erros.add("O endereço é inválido.");
        }

        if (escolaDTO.getCriacao() == null) {
            erros.add("A data de criação é inválida.");
        }

        if (!erros.isEmpty()) {
            throw new BusinessException(erros);
        }
    }

    public EscolaDTO consultarDadosEscola(Long idEscola) throws BusinessException {
        Escola escola = escolaRepository.find(Escola.class, idEscola);
        if (escola == null) {
            throw new BusinessException("Escola não encontrada através do ID " + idEscola + ".");
        }

        return new EscolaDTO(escola);
    }

    public List<EscolaDTO> obterEscolas() {
        return escolaRepository.obterEscolas();
    }
}
