package entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)

@XmlType(propOrder = {"titulo", "pagInicial", "pagFinal", "autores"})

public class Artigo {

    @XmlElement(name = "titulo")
    private String titulo;

    @XmlElement(name = "pag_inicial")
    private int pagInicial;

    @XmlElement(name = "pag_final")
    private int pagFinal;

    @XmlElementWrapper(name = "autores")
    @XmlElement(name = "autor")
    private List<Autor> autores;

    public Artigo(String titulo, int pagInicial, int pagFinal) {
        this.titulo = titulo;
        this.pagInicial = pagInicial;
        this.pagFinal = pagFinal;
        autores = new ArrayList<>();
    }

    public void addAutor(Autor autor) {
        autores.add(autor);
    }
}
