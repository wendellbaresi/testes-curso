package servicos;

import com.projetotestes.entidades.Filme;
import com.projetotestes.entidades.Locacao;
import com.projetotestes.entidades.Usuario;
import com.projetotestes.servicos.LocacaoService;
import com.projetotestes.utils.DataUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.Date;

import static com.projetotestes.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class AssertTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void testeLocacao() throws Exception{

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
        assertThat(isMesmaData(locacao.getDataLocacao(),new Date()),is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));

        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getValor(), is(not(6.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()),is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));

        // CASO LANCE UM EXCECAO NO METODO ALUGAR FILME, AI TEM QUE LANCAR UM TRY..CATCH
        try {
            Locacao newLocacao = service.alugarFilme(usuario,filme);

            assertThat(newLocacao.getValor(), is(equalTo(5.0)));
            assertThat(newLocacao.getValor(), is(not(6.0)));
            assertThat(isMesmaData(locacao.getDataLocacao(),new Date()),is(true));
            assertThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));

            error.checkThat(newLocacao.getValor(), is(equalTo(5.0)));
            error.checkThat(newLocacao.getValor(), is(not(6.0)));
            error.checkThat(isMesmaData(newLocacao.getDataLocacao(),new Date()),is(true));
            error.checkThat(isMesmaData(newLocacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),is(true));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
