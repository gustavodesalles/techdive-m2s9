package projeto.repository;

import projeto.dto.EscolaDTO;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class EscolaRepository extends GenericRepository {

    public List<EscolaDTO> obterEscolas() {
        try {
            return entityManager.createQuery("SELECT new projeto.dto.EscolaDTO(e.idEscola, e.nome) " +
                            "FROM Escola e")
                    .getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
