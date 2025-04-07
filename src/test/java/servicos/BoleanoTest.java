package servicos;

import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import br.ce.wcaquino.dto.FilmeDTO;
import br.ce.wcaquino.dto.LocacaoDTO;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoServiceCalculadora;
import br.ce.wcaquino.utils.DataUtils;
import matchers.DiaSemanaMatcher;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static matchers.MatchersProprios.caiEm;
import static matchers.MatchersProprios.caiNumaSegunda;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BoleanoTest {

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
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws LocadoraException, FilmeSemEstoqueException {

        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        Usuario usuario = new Usuario("Usuario 1");
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 2, 5.0));

        LocacaoDTO retorno = locacaoServiceCalculadora.alugarFilme(usuario, filmes);

//        boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
//
//        assertTrue(ehSegunda);

        //assertThat(retorno.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
        assertThat(retorno.getDataRetorno(), caiEm(Calendar.MONDAY));
        assertThat(retorno.getDataRetorno(), caiNumaSegunda());
    }
}
