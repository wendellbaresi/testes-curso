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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class NomeandoTestes {
    @Rule
    public ErrorCollector error = new ErrorCollector();

    private LocacaoServiceDesafio locacaoServiceDesafio;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
        locacaoServiceDesafio = new LocacaoServiceDesafio();
    }

    @Test
    public void deveAlugarFilme() throws Exception {

        // cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //acao
        Locacao locacao = service.alugarFilme(usuario, filme);


        //verificacao
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        Assert.assertEquals("Erro de comparacao", 1, 1);
        Assert.assertEquals(0.51234, 0.512, 0.001);
        Assert.assertEquals(Math.PI, 3.14, 0.01);

        assertThat(locacao.getValor(), is(equalTo(5.0)));
        assertThat(locacao.getValor(), is(not(6.0)));
        assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getValor(), is(not(6.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws Exception{
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 0, 5.0));

        locacaoServiceDesafio.alugarFilme(usuario,filmes);
    }

    @Test
    public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException { // Excecao tratada pelo JUnit

        // cenario
        LocacaoServiceSemEstoque service = new LocacaoServiceSemEstoque();
        Filme filme = new Filme("Filme 1", 1, 5.0);
        Usuario usuario = null;

        //acao
        try {
            service.alugarFilme_SemEstoque(usuario, filme);
            Assert.fail();
        } catch (LocadoraException e){
            assertThat(e.getMessage(), Is.is("Usuario vazio"));
        }

        System.out.println("Forma Robusta");
    }


    @Test
    public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException{
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 1, 5.0));

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        locacaoServiceDesafio.alugarFilme(usuario,null);

        System.out.println("Forma Nova");
    }


}
