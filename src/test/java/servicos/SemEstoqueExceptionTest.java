package servicos;

import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoServiceSemEstoque;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SemEstoqueExceptionTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test(expected = Exception.class)
    public void testeLocacao_filmeSemEstoque() throws Exception{

        // cenario
        LocacaoServiceSemEstoque serviceSemEstoque = new LocacaoServiceSemEstoque();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        serviceSemEstoque.alugarFilme_SemEstoque(usuario, filme);

    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void testeLocacal_filmesSemEstoque2(){

        // cenario
        LocacaoServiceSemEstoque serviceSemEstoque = new LocacaoServiceSemEstoque();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        try {
            serviceSemEstoque.alugarFilme_SemEstoque(usuario, filme);
            Assert.fail("Deveria ter lancado uma excecao");
        }catch (Exception e){
            assertThat(e.getMessage(), is("Filmes sem estoque") );
        }

    }

    @Test
    public void testeLocacal_filmesSemEstoque3() throws Exception{

        // cenario
        LocacaoServiceSemEstoque serviceSemEstoque = new LocacaoServiceSemEstoque();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");

        serviceSemEstoque.alugarFilme_SemEstoque(usuario, filme);
    }
}
