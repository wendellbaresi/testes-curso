package matchers;

import com.projetotestes.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DataDiferencaDiasMatacher extends TypeSafeMatcher<Date> {


    private Integer quantDias;

    public DataDiferencaDiasMatacher(Integer quantDias) {
        this.quantDias = quantDias;
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(quantDias));
    }

    @Override
    public void describeTo(Description description) {

    }
}
