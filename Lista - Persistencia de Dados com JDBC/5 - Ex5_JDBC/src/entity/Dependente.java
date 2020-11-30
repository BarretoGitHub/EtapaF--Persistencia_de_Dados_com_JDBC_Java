package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jaxb.TipoDependenteAdapter;

@XmlAccessorType(XmlAccessType.NONE)

public class Dependente {

    @XmlElement(name = "nome")
    private String nome;

    @XmlElement(name = "tipo")
    @XmlJavaTypeAdapter(TipoDependenteAdapter.class)
    private TipoEnum tipo;

    public String getNome() {
        return nome;
    }

    public TipoEnum getTipo() {
        return tipo;
    }
}
