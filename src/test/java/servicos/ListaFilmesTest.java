package servicos;

import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import br.ce.wcaquino.dto.FilmeDTO;
import br.ce.wcaquino.dto.LocacaoDTO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.servicos.LocacaoServiceDesafio;
import br.ce.wcaquino.servicos.LocacaoServiceSemEstoque;
import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static matchers.MatchersProprios.ehHoje;
import static matchers.MatchersProprios.ehHojeComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ListaFilmesTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private LocacaoServiceDesafio service;

    @Before
    public void setUp(){
        service = new LocacaoServiceDesafio();
    }

    @Test
    public void testeLocacao() throws Exception{

        // cenario
        LocacaoServiceDesafio service = new LocacaoServiceDesafio();
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 1, 5.0));

        LocacaoDTO locacao = service.alugarFilme(usuario, filmes);


        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getValor(), is(not(6.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()),is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));

        // Desafio

        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(Boolean.TRUE));
        error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
        error.checkThat(locacao.getDataLocacao(), ehHoje());
    }

    @Test
    public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException { // As Excecoes de extensao sao FilmeSemEstoqueException e LocacaoException

        // cenario
        LocacaoServiceSemEstoque service = new LocacaoServiceSemEstoque();
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //acao
        try {
            service.alugarFilme_SemEstoque(null, filme);
            Assert.fail();
        } catch (LocadoraException e){
            assertThat(e.getMessage(), Is.is("Usuario vazio"));
        }
    }

    @Test(expected = Exception.class)
    public void testeLocacao_filmeSemEstoque() throws Exception{

        // cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 0, 5.0));

        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void testLocacao_usuarioVazio2() throws FilmeSemEstoqueException { // Excecao tratada pelo JUnit

        // cenario
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 1, 5.0));
        Usuario usuario = null;

        //acao
        try {
            service.alugarFilme(usuario, filmes);
            Assert.fail();
        } catch (LocadoraException e){
            assertThat(e.getMessage(), Is.is("Usuario vazio"));
        }

        System.out.println("Forma Robusta");
    }

    @Test
    public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException{
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 1, 5.0));

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        service.alugarFilme(usuario,null);

        System.out.println("Forma Nova");
    }
}
