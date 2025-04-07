package servicos;

import com.projetotestes.entidades.Filme;
import com.projetotestes.entidades.Locacao;
import com.projetotestes.entidades.Usuario;
import com.projetotestes.servicos.LocacaoService;
import com.projetotestes.utils.DataUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class LocacaoServiceTest {



    @Test
    public void teste() throws Exception{

        Assert.assertTrue(true);
        Assert.assertFalse(false);
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        System.out.println("Teste");

        //acao
        Locacao locacao = service.alugarFilme(usuario,filme);

        //verificacao
        Assert.assertTrue(locacao.getValor() == 5.0);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }
}
