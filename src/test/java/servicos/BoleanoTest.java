package servicos;

import com.projetotestes.Exception.FilmeSemEstoqueException;
import com.projetotestes.Exception.LocadoraException;
import com.projetotestes.dto.FilmeDTO;
import com.projetotestes.dto.LocacaoDTO;
import com.projetotestes.entidades.Usuario;
import com.projetotestes.servicos.LocacaoServiceCalculadora;
import com.projetotestes.utils.DataUtils;
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
