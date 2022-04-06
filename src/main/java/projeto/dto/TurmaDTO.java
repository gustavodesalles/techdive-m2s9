package projeto.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TurmaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idTurma;

    private String nome;

    private Date dataInicio;

    private Date dataTermino;

    private List<EstudanteDTO> estudantes = new ArrayList<>();

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public List<EstudanteDTO> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<EstudanteDTO> estudantes) {
        this.estudantes = estudantes;
    }
}
