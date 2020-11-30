package jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class ManipuladorJAXB {
    
    private String arquivo;
    
    public ManipuladorJAXB(String arquivo) {
        this.arquivo = arquivo;
    }
    
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    
    public <T>T ler(Class<T> clazz) {
    	JAXBElement<T> element = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            element = unmarshaller.unmarshal(new StreamSource(new File(arquivo)), clazz);
        }
        catch(JAXBException erro) {
            erro.printStackTrace();
        }
        return element.getValue();
    }
    
    public void gravar(Object object) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, new File(arquivo));
            //marshaller.marshal(object, System.out);
        }
        catch(JAXBException erro) {
            erro.printStackTrace();
        }
    }
} 
