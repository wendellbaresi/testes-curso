package matchers;

import java.util.Calendar;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(Integer diaSemana){
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static DataDiferencaDiasMatacher ehHojeComDiferencaDias(Integer quantDias){
        return new DataDiferencaDiasMatacher(quantDias);
    }

    public static DataDiferencaDiasMatacher ehHoje(){
        return new DataDiferencaDiasMatacher(0);
    }
}
