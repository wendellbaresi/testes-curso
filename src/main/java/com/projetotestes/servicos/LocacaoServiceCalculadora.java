package com.projetotestes.servicos;

import com.projetotestes.Exception.FilmeSemEstoqueException;
import com.projetotestes.Exception.LocadoraException;
import com.projetotestes.dto.FilmeDTO;
import com.projetotestes.dto.LocacaoDTO;
import com.projetotestes.entidades.Usuario;
import com.projetotestes.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.projetotestes.utils.DataUtils.adicionarDias;

public class LocacaoServiceCalculadora {

    public LocacaoDTO alugarFilme(Usuario usuario, List<FilmeDTO> filmes) throws FilmeSemEstoqueException, LocadoraException {

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
        Double valorTotal = 0d;
        for (int i = 0; i < filmes.size(); i++){
            FilmeDTO filmeDTO = filmes.get(i);
            Double valorFilme = filmeDTO.getPrecoLocacao();
            if (i == 2){
                valorFilme = valorFilme * 0.75;
            }
            if (i == 3){
                valorFilme = valorFilme * 0.5;
            }
            if (i == 4){
                valorFilme = valorFilme * 0.25;
            }
            if (i == 5){
                valorFilme = valorFilme * 0;
            }

            //fatorar o codigo com boas praticas
//            switch (i){
//                case 2: valorFilme = valorFilme = valorFilme * 0.75;
//                case 3: valorFilme = valorFilme = valorFilme * 0.50;
//                case 4: valorFilme = valorFilme = valorFilme * 0.25;
//                case 5: valorFilme = 0d;
//            }
            valorTotal += valorFilme;
        }
        locacaoDTO.setValor(valorTotal);

        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        if (DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
            dataEntrega = adicionarDias(dataEntrega, 1);
        }
        locacaoDTO.setDataRetorno(dataEntrega);

        return locacaoDTO;
    }
}
