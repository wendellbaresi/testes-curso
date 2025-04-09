package com.projetotestes.servicos;

import com.projetotestes.Exception.FilmeSemEstoqueException;
import com.projetotestes.Exception.LocadoraException;
import com.projetotestes.dto.FilmeDTO;
import com.projetotestes.dto.LocacaoUserDTO;
import com.projetotestes.dto.UsuarioDTO;
import com.projetotestes.utils.DataUtils;

import java.util.Date;
import java.util.List;

public class LocacaoServiceBuilder {

    public LocacaoUserDTO alugarFilme(UsuarioDTO usuarioDTO, List<FilmeDTO> filmes) throws FilmeSemEstoqueException, LocadoraException {

        if (usuarioDTO == null) {
            throw new LocadoraException("Usuario vazio");
        }
        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Filme vazio");
        }
        for (FilmeDTO filmeDTO : filmes){
            if (filmeDTO.getEstoque() == 0){
                throw new FilmeSemEstoqueException("Filme sem estoque");
            }
        }


        LocacaoUserDTO locacaoUserDTO = new LocacaoUserDTO();
        locacaoUserDTO.setFilmesDTO(filmes);
        locacaoUserDTO.setUsuarioDTO(usuarioDTO);
        locacaoUserDTO.setDataLocacao(new Date());
        Date dataRetorno = DataUtils.obterDataComDiferencaDias(1);
        locacaoUserDTO.setDataRetorno(dataRetorno);
        Double valorTotal = 0d;
        for (FilmeDTO filme : filmes){
            valorTotal += filme.getPrecoLocacao();
        }
        locacaoUserDTO.setValor(valorTotal);

        return locacaoUserDTO;
    }
}
