package com.projetotestes.servicos;

import com.projetotestes.Exception.FilmeSemEstoqueException;
import com.projetotestes.Exception.LocadoraException;
import com.projetotestes.entidades.Filme;
import com.projetotestes.entidades.Locacao;
import com.projetotestes.entidades.Usuario;

import java.util.Date;

import static com.projetotestes.utils.DataUtils.adicionarDias;

public class LocacaoServiceEx {

    public Locacao alugarFilmeEx(Usuario usuario, Filme filme)throws Exception {

        if (filme.getEstoque() == 0) {
            throw new FilmeSemEstoqueException();
        }

        if (usuario == null) {
            throw new LocadoraException("Usuario vazio");
        }
        if (filme == null) {
            throw new LocadoraException("Filme vazio");
        }

        Locacao locacao = new Locacao();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        return locacao;
    }
}
