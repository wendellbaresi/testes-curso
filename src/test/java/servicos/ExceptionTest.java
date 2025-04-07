package servicos;

import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.servicos.LocacaoServiceSemEstoque;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExceptionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException{ // As Excecoes de extensao sao FilmeSemEstoqueException e LocacaoException

        // cenario
        LocacaoServiceSemEstoque service = new LocacaoServiceSemEstoque();
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //acao
        try {
            service.alugarFilme_SemEstoque(null, filme);
            Assert.fail();
        } catch (LocadoraException e){
            assertThat(e.getMessage(), is("Usuario vazio"));
        }
    }

    @Test
    public void testLocacao_usuarioVazio2() throws FilmeSemEstoqueException { // Excecao tratada pelo JUnit

        // cenario
        // cenario
        LocacaoServiceSemEstoque service = new LocacaoServiceSemEstoque();
        Filme filme = new Filme("Filme 1", 2, 5.0);
        Usuario usuario = new Usuario("Usuario 1");

        //acao
        try {
            service.alugarFilme_SemEstoque(usuario, filme);
            Assert.fail();
        } catch (LocadoraException e){
            assertThat(e.getMessage(), is("Usuario vazio"));
        }

        System.out.println("Forma Robusta");
    }

    @Test
    public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException{
        LocacaoServiceSemEstoque service = new LocacaoServiceSemEstoque();
        Usuario usuario = new Usuario("Usuario 1");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        service.alugarFilme_SemEstoque(usuario,null);

        System.out.println("Forma Nova");
    }
}
