package suites;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import servicos.CalculoValorLocacaoTest;
import servicos.LocacaoServiceTest;
import servicos.PercentualTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculoValorLocacaoTest.class,
        PercentualTest.class,
        LocacaoServiceTest.class
})
public class SuiteExecucao {
    //Remova se puder

    @BeforeClass
    public static void before(){
        System.out.println("Before");
    }

    @AfterClass
    public static void after(){
        System.out.println("After");
    }
}

