package entity;

import java.util.Arrays;
import java.util.Optional;

public enum TipoEnum {
    FILHO("f"),
    COMPANHEIRO("c"),
    PAIS("p"),
    IRMAOS("i"),
    DESCONHECIDO("d");

    private String sigla;

    private TipoEnum(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public static Optional<TipoEnum> getTipoPelaSigla(String sigla) {
        return Arrays.stream(TipoEnum.values())
                .filter(s -> s.equals(sigla))
                .findFirst();
    }
}
