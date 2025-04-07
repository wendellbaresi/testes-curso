package servicos;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdenarTest {

    public static int c = 0;

    @Test
    public void inicia(){
        c = 1;
    }

    @Test
    public void termina(){
        assertEquals(1, c);
    }


    // Essa forma nao e a mais desejada
    @Test
    public void testeGeral(){
        inicia();
        termina();
    }

}
