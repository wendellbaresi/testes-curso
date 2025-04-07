package br.ce.wcaquino.servicos;

import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import br.ce.wcaquino.dto.FilmeDTO;
import br.ce.wcaquino.dto.LocacaoDTO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

import java.util.Date;
import java.util.List;

public class LocacaoServiceDesafio {

    public LocacaoDTO alugarFilme(Usuario usuario, List<FilmeDTO> filmes) throws FilmeSemEstoqueException, LocadoraException{

        if (usuario == null) {
            throw new LocadoraException("Usuario vazio");
        }
        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Filme vazio");
        }
        for (FilmeDTO filmeDTO : filmes){
            if (filmeDTO.getEstoque() == 0){
                throw new FilmeSemEstoqueException();
            }
        }

        LocacaoDTO locacaoDTO = new LocacaoDTO();
        locacaoDTO.setFilmesDTO(filmes);
        locacaoDTO.setUsuario(usuario);
        locacaoDTO.setDataLocacao(new Date());
        Date dataRetorno = DataUtils.obterDataComDiferencaDias(1);
        locacaoDTO.setDataRetorno(dataRetorno);
        Double valorTotal = 0d;
        for (FilmeDTO filme : filmes){
            valorTotal += filme.getPrecoLocacao();
        }
        locacaoDTO.setValor(valorTotal);

        return locacaoDTO;
    }
}
