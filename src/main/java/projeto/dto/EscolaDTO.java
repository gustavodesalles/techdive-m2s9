package projeto.dto;

import projeto.entity.Escola;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EscolaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEscola;

    private String nome;

    private EnderecoDTO enderecoDTO = new EnderecoDTO();

    private Date criacao;

    private List<TurmaDTO> turmas = new ArrayList<>();

    private List<Long> turmasIdSelecionadas = new ArrayList<>();

    public EscolaDTO() {
    }

    public EscolaDTO(Long idEscola, String nome) {
        this.idEscola = idEscola;
        this.nome = nome;
    }

    public EscolaDTO(Escola escola) {
        this.idEscola = escola.getIdEscola();
        this.nome = escola.getNome();
        this.enderecoDTO = new EnderecoDTO(escola.getEndereco());
        this.criacao = escola.getCriacao();
        this.turmas = escola.getTurmas().stream().map(TurmaDTO::new).sorted(Comparator.comparing(TurmaDTO::getNome)).collect(Collectors.toList());
    }

    public Long getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Long idEscola) {
        this.idEscola = idEscola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public List<TurmaDTO> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaDTO> turmas) {
        this.turmas = turmas;
    }

    public List<Long> getTurmasIdSelecionadas() {
        return turmasIdSelecionadas;
    }

    public void setTurmasIdSelecionadas(List<Long> turmasIdSelecionadas) {
        this.turmasIdSelecionadas = turmasIdSelecionadas;
    }

    @Override
    public String toString() {
        return "EscolaDTO{" +
                "idEscola=" + idEscola +
                ", nome='" + nome + '\'' +
                ", enderecoDTO=" + enderecoDTO +
                ", criacao=" + criacao +
                ", turmas=" + turmas +
                ", turmasIdSelecionadas=" + turmasIdSelecionadas +
                '}';
    }
}
