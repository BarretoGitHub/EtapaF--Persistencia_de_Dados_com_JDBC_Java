package entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.NONE)

public class Servidor {

    @XmlElement(name = "codigo")
    private int codigo;

    @XmlElement(name = "nome")
    private String nome;

    @XmlElement(name = "dependente")
    @XmlElementWrapper(name = "dependentes")
    private List<Dependente> dependentes;

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }
}
