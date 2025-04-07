package servicos;

import com.projetotestes.Exception.FilmeSemEstoqueException;
import com.projetotestes.Exception.LocadoraException;
import com.projetotestes.dto.FilmeDTO;
import com.projetotestes.dto.LocacaoDTO;
import com.projetotestes.entidades.Usuario;
import com.projetotestes.servicos.LocacaoServiceCalculadora;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PercentualTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    private LocacaoServiceCalculadora locacaoServiceCalculadora;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
        locacaoServiceCalculadora = new LocacaoServiceCalculadora();
    }

    @Test
    public void devePagar75PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 2, 4.0), new FilmeDTO("Filme 2", 2, 4.0), new FilmeDTO("Filme 3", 2, 4.0));


        LocacaoDTO resultado  = locacaoServiceCalculadora.alugarFilme(usuario,filmes);

        //verificacao
        //Resultado esperado 4 + 4 + 3 = 11
        assertThat(resultado.getValor(), is(11.0));
        System.out.println("Valor Final foi: " + resultado.getValor());
    }

    @Test
    public void devePagar50PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 2, 4.0), new FilmeDTO("Filme 2", 2, 4.0), new FilmeDTO("Filme 3", 2, 4.0),new FilmeDTO("Filme 4", 2, 4.0));


        LocacaoDTO resultado  = locacaoServiceCalculadora.alugarFilme(usuario,filmes);

        //verificacao
        //Resultado esperado 4 + 4 + 3 + 2 = 13
        assertThat(resultado.getValor(), is(13.0));
        System.out.println("Valor Final foi: " + resultado.getValor());
    }

    @Test
    public void devePagar25PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(
                new FilmeDTO("Filme 1", 2, 4.0),
                new FilmeDTO("Filme 2", 2, 4.0),
                new FilmeDTO("Filme 3", 2, 4.0),
                new FilmeDTO("Filme 4", 2, 4.0),
                new FilmeDTO("Filme 5", 2, 4.0));


        LocacaoDTO resultado  = locacaoServiceCalculadora.alugarFilme(usuario,filmes);

        //verificacao
        //Resultado esperado 4 + 4 + 3 + 2 + 1= 14
        assertThat(resultado.getValor(), is(14.0));
        System.out.println("Valor Final foi: " + resultado.getValor());
    }

    @Test
    public void devePagar0PctNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(
                new FilmeDTO("Filme 1", 2, 4.0),
                new FilmeDTO("Filme 2", 2, 4.0),
                new FilmeDTO("Filme 3", 2, 4.0),
                new FilmeDTO("Filme 4", 2, 4.0),
                new FilmeDTO("Filme 5", 2, 4.0),
                new FilmeDTO("Filme 6", 2, 4.0));


        LocacaoDTO resultado  = locacaoServiceCalculadora.alugarFilme(usuario,filmes);

        //verificacao
        //Resultado esperado 4 + 4 + 3 + 2 + 1= 14
        assertThat(resultado.getValor(), is(14.0));
        System.out.println("Valor Final foi: " + resultado.getValor());
    }
}
