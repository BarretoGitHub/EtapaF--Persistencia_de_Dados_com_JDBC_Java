package entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "servidores")

@XmlAccessorType(XmlAccessType.NONE)

public class Instituicao {

    @XmlElement(name = "servidor")
    private List<Servidor> servidores;

    public List<Servidor> getServidores() {
        return servidores;
    }

    //para testes
    public void setServidores(List<Servidor> servidores) {
        this.servidores = servidores;
    }
}
