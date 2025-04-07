package com.projetotestes.dto;

import java.util.Date;
import java.util.List;

public class LocacaoUserDTO {

    private UsuarioDTO usuarioDTO;
    private List<FilmeDTO> filmesDTO;
    private Date dataLocacao;
    private Date dataRetorno;
    private Double valor;

    public LocacaoUserDTO(){}

    public LocacaoUserDTO(UsuarioDTO usuarioDTO, List<FilmeDTO> filmesDTO, Date dataLocacao, Date dataRetorno, Double valor) {
        this.usuarioDTO = usuarioDTO;
        this.filmesDTO = filmesDTO;
        this.dataLocacao = dataLocacao;
        this.dataRetorno = dataRetorno;
        this.valor = valor;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
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
