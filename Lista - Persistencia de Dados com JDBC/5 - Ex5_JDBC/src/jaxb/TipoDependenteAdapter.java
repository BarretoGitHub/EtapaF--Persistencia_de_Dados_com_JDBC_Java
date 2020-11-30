package jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import entity.TipoEnum;

public class TipoDependenteAdapter extends XmlAdapter<String, TipoEnum> {

    @Override
    public String marshal(TipoEnum arg0) throws Exception {
        return arg0.getSigla();
    }

    @Override
    public TipoEnum unmarshal(String arg0) throws Exception {
        return TipoEnum.getTipoPelaSigla(arg0).orElse(TipoEnum.DESCONHECIDO);
    }
}
