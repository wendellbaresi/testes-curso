package servicos;

import com.projetotestes.Exception.FilmeSemEstoqueException;
import com.projetotestes.Exception.LocadoraException;
import com.projetotestes.dto.FilmeDTO;
import com.projetotestes.dto.LocacaoUserDTO;
import com.projetotestes.dto.UsuarioDTO;
import com.projetotestes.servicos.LocacaoServiceBuilder;
import com.projetotestes.utils.DataUtils;
import builders.UsuarioDTOBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static builders.FilmeDTOBuilder.umFilme;
import static builders.FilmeDTOBuilder.umFilmeSemEstoque;
import static com.projetotestes.utils.DataUtils.isMesmaData;
import static com.projetotestes.utils.DataUtils.obterDataComDiferencaDias;
import static matchers.MatchersProprios.ehHoje;
import static matchers.MatchersProprios.ehHojeComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

public class BuilderTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private LocacaoServiceBuilder service;


    @Before
    public void setUp(){
        service = new LocacaoServiceBuilder();
    }

    @Test
    public void testeLocacao() throws Exception{


        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.umUsuarioDTO().agora();
        List<FilmeDTO> filmes = Arrays.asList(umFilme().comValor(5.0).agora());
        LocacaoUserDTO locacao = service.alugarFilme(usuarioDTO, filmes);


        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getValor(), is(not(6.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()),is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));

        // Desafio

        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(Boolean.TRUE));
        error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
        error.checkThat(locacao.getDataLocacao(), ehHoje());
    }


    @Test(expected = Exception.class)
    public void testeLocacao_filmeSemEstoque() throws Exception{

        // cenario
        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.umUsuarioDTO().agora();
        List<FilmeDTO> filmes = Arrays.asList(umFilmeSemEstoque().agora());
        service.alugarFilme(usuarioDTO, filmes);
    }

    @Test
    public void testLocacao_usuarioVazio2() throws FilmeSemEstoqueException { // Excecao tratada pelo JUnit

        // cenario
        List<FilmeDTO> filmes = Arrays.asList(new FilmeDTO("Filme 1", 1, 5.0));
        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.umUsuarioDTO().agora();

        System.out.println("Forma Robusta");
    }

    @Test
    public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException{
        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.umUsuarioDTO().agora();
        List<FilmeDTO> filmes = Arrays.asList(umFilme().agora());
        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        service.alugarFilme(usuarioDTO,null);

        System.out.println("Forma Nova");
    }
}
