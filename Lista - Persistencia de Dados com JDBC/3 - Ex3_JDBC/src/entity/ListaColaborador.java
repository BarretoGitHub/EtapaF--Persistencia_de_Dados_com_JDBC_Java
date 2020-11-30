package entity;

import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListaColaborador {

    private Map<Month, List<Colaborador>> colaboradoresPorMes;

    public ListaColaborador(List<Colaborador> colaboradores) {
        colaboradoresPorMes = agruparColaboradoresPorMesNascimento(colaboradores);
    }

    private Map<Month, List<Colaborador>> agruparColaboradoresPorMesNascimento(List<Colaborador> colaboradores) {
        return colaboradores.stream()
                .sorted(Comparator.comparing(Colaborador::getDataNascimento)
                        .thenComparing(Comparator.comparing(Colaborador::getNome)))
                .collect(Collectors.groupingBy(Colaborador::getMonth));
    }

    public List<Colaborador> getColaboradoresPorMes(Month month) {
        List<Colaborador> subconjunto = colaboradoresPorMes.getOrDefault(month, new ArrayList<>());
        return subconjunto;
    }
}
