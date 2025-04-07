package com.projetotestes.servicos;

import static com.projetotestes.utils.DataUtils.adicionarDias;

import java.util.Date;

import com.projetotestes.entidades.Filme;
import com.projetotestes.entidades.Locacao;
import com.projetotestes.entidades.Usuario;

public class LocacaoService {

	public String vPublico;
	protected  String vProtegida;
	private String vPrivada;
	
	public Locacao alugarFilme(Usuario usuario, Filme filme){

		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}


}