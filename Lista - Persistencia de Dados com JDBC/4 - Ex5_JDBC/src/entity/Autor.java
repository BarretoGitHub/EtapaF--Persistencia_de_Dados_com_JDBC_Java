package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)

@XmlType(propOrder = {"id", "nome"})

public class Autor {

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "nome")
    private String nome;

    public Autor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
