package projeto.repository;

import org.apache.commons.lang3.StringUtils;
import projeto.dto.EstudanteDTO;
import projeto.dto.FiltroEstudanteDTO;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EstudanteRepository extends GenericRepository {

    public List<EstudanteDTO> consultarEstudantePorNomeOuMatricula(String query) {
        query = "%" + query + "%";
        query = query.toLowerCase();

        try {
            return entityManager.createQuery("SELECT new projeto.dto.EstudanteDTO(e.idEstudante, e.nome) " +
                            "FROM Estudante e " +
                            "WHERE CAST(e.idEstudante AS string) = :query " +
                            "OR LOWER(e.nome) LIKE :query", EstudanteDTO.class)
                    .setParameter("query", query)
                    .getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    public List<EstudanteDTO> buscar(FiltroEstudanteDTO filtro) {
        String hql = montarSqlBuscaEstudante(filtro);
        Query query = entityManager.createQuery(hql, EstudanteDTO.class);
        popularParametrosBuscaEstudante(filtro, query);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    private void popularParametrosBuscaEstudante(FiltroEstudanteDTO filtro, Query query) {
        if (filtro.getIdEstudante() != null) {
            query.setParameter("idEstudante", filtro.getIdEstudante());
        }

        if (filtro.getIdTurma() != null) {
            query.setParameter("idTurma", filtro.getIdTurma());
        }

        if (!StringUtils.isBlank(filtro.getNome())) {
            String nome = "%" + filtro.getNome() + "%";
            nome = nome.toLowerCase();
            query.setParameter("nome", nome);
        }

        if (!StringUtils.isBlank(filtro.getEmail())) {
            String email = "%" + filtro.getEmail() + "%";
            email = email.toLowerCase();
            query.setParameter("email", email);
        }

        if (filtro.getDataNascimento() != null) {
            query.setParameter("dataNascimento", filtro.getDataNascimento());
        }
    }

    private String montarSqlBuscaEstudante(FiltroEstudanteDTO filtro) {
        String hql = "SELECT new projeto.dto.EstudanteDTO(e) " +
                "FROM Estudante e ";
        String andOrWhere = "WHERE ";

        if (filtro.getIdEstudante() != null) {
            hql = hql.concat(andOrWhere).concat("e.idEstudante = :idEstudante ");
            andOrWhere = "AND ";
        }

        if (filtro.getIdTurma() != null) {
            hql = hql.concat(andOrWhere).concat("e.turma.idTurma = :idTurma ");
            andOrWhere = "AND ";
        }

        if (!StringUtils.isBlank(filtro.getNome())) {
            hql = hql.concat(andOrWhere).concat("LOWER(e.nome) LIKE :nome ");
            andOrWhere = "AND ";
        }

        if (!StringUtils.isBlank(filtro.getEmail())) {
            hql = hql.concat(andOrWhere).concat("LOWER(e.email) LIKE :email ");
            andOrWhere = "AND ";
        }

        if (filtro.getDataNascimento() != null) {
            hql = hql.concat(andOrWhere).concat("e.dataNascimento = :dataNascimento ");
        }

        return hql.concat("ORDER BY e.idEstudante");
    }
}
