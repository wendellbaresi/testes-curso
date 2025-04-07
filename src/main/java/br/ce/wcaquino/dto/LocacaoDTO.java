package br.ce.wcaquino.dto;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Usuario;

import java.util.Date;
import java.util.List;

public class LocacaoDTO {

    private Usuario usuario;
    private List<FilmeDTO> filmesDTO;
    private Date dataLocacao;
    private Date dataRetorno;
    private Double valor;

    public LocacaoDTO(){}

    public LocacaoDTO(Usuario usuario, List<FilmeDTO> filmesDTO, Date dataLocacao, Date dataRetorno, Double valor) {
        this.usuario = usuario;
        this.filmesDTO = filmesDTO;
        this.dataLocacao = dataLocacao;
        this.dataRetorno = dataRetorno;
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<FilmeDTO> getFilmesDTO() {
        return filmesDTO;
    }

    public void setFilmesDTO(List<FilmeDTO> filmesDTO) {
        this.filmesDTO = filmesDTO;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
