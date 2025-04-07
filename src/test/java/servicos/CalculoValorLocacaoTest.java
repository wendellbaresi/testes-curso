package servicos;

import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import br.ce.wcaquino.dto.FilmeDTO;
import br.ce.wcaquino.dto.LocacaoDTO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoServiceCalculadora;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Parameterized.Parameter
    public List<FilmeDTO> filmes;

    @Parameterized.Parameter(value = 1)
    public Double valorLocacao;

    @Parameterized.Parameter(value = 2)
    public String cenario;

    private LocacaoServiceCalculadora locacaoServiceCalculadora;

    @Before
    public void setUp(){
        locacaoServiceCalculadora = new LocacaoServiceCalculadora();
    }

    private static FilmeDTO filme1 = new FilmeDTO("Filmes 1", 2, 4.0);
    private static FilmeDTO filme2 = new FilmeDTO("Filmes 2", 2, 4.0);
    private static FilmeDTO filme3 = new FilmeDTO("Filmes 3", 2, 4.0);
    private static FilmeDTO filme4 = new FilmeDTO("Filmes 4", 2, 4.0);
    private static FilmeDTO filme5 = new FilmeDTO("Filmes 5", 2, 4.0);
    private static FilmeDTO filme6 = new FilmeDTO("Filmes 6", 2, 4.0);

    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[] []{
                {Arrays.asList(filme1,filme2), 8.0, "2 Filmes: Sem Desconto"},
                {Arrays.asList(filme1,filme2,filme3), 11.0, "3 Filmes: 25%"},
                {Arrays.asList(filme1,filme2,filme3, filme4), 13.0, "4 Filmes: 50%"},
                {Arrays.asList(filme1,filme2,filme3, filme4, filme5), 14.0, "5 Filmes: 75%"},
                {Arrays.asList(filme1,filme2,filme3, filme4, filme5, filme6), 14.0, "6 Filmes: 100%"}
        });
    }

    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
        Usuario usuario = new Usuario("Usuario 1");

        LocacaoDTO resultado  = locacaoServiceCalculadora.alugarFilme(usuario,filmes);

        assertThat(resultado.getValor(), is(valorLocacao));
    }

    @Test
    public void print(){
        System.out.println(valorLocacao);
    }

    @Test
    public void suites(){
        System.out.println("!");
    }
}
