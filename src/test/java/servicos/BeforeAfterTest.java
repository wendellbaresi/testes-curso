package servicos;

import com.projetotestes.Exception.FilmeSemEstoqueException;
import com.projetotestes.Exception.LocadoraException;
import com.projetotestes.entidades.Filme;
import com.projetotestes.entidades.Usuario;
import com.projetotestes.servicos.LocacaoServiceSemEstoque;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BeforeAfterTest {

    private LocacaoServiceSemEstoque serviceSemEstoque;

    // Contador
    private static int c;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
        System.out.println("Before");
        serviceSemEstoque = new LocacaoServiceSemEstoque();
        c++;
        System.out.println(c);
    }

    @After
    public void setDown(){
        System.out.println("After");
    }

    @BeforeClass
    public static void setUpClass(){
        System.out.println("Before Class");
    }

    @AfterClass
    public static void setDownClass(){
        System.out.println("After Class");
    }

    @Test(expected = Exception.class)
    public void testeLocacao_filmeSemEstoque() throws Exception{

        // cenario

        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);
        System.out.println("Teste");

        serviceSemEstoque.alugarFilme_SemEstoque(usuario, filme);

    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void testeLocacal_filmesSemEstoque2() throws FilmeSemEstoqueException, LocadoraException {

        // cenario
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        System.out.println("Teste");

        serviceSemEstoque.alugarFilme_SemEstoque(usuario,filme);
    }

    @Test
    public void testeLocacal_filmesSemEstoque3() throws Exception{

        // cenario
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 1, 5.0);
        System.out.println("Teste");

        serviceSemEstoque.alugarFilme_SemEstoque(usuario, filme);
    }


}
