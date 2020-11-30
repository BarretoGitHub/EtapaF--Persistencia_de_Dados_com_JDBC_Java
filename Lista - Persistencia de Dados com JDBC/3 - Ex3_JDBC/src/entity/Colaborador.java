package entity;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Colaborador {

    private String nome;
    private LocalDate dataNascimento;

    public final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Colaborador(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Month getMonth() {
        return dataNascimento.getMonth();
    }

    public Object[] toArray() {
        return new Object[]{
            nome,
            getDataNascimento().format(FORMATTER)
        };
    }
}
