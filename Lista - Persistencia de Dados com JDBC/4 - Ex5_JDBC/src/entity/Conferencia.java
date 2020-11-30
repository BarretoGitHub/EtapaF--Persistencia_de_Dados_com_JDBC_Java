package entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="artigos")

@XmlAccessorType(XmlAccessType.NONE)

public class Conferencia {

	@XmlElement(name="artigo")
	private List<Artigo> artigos;
	
	public Conferencia() {
	}
	
	public Conferencia(List<Artigo> artigos) {
		this.artigos = artigos;
	}
}
