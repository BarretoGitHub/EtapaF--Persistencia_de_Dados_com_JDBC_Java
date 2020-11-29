 
package entity;
 
import java.util.HashSet;
import java.util.Set;

public class Disciplina {
    
    private String codigo;
    private String nome;
    private Set<Disciplina> requisitos;
    
    public Disciplina(String codigo, String nome) {        
        this.codigo = codigo;
        this.nome = nome;
        requisitos = new HashSet<>();
    }
    
    public Disciplina(String codigo) {
    	this.codigo = codigo;
    	requisitos = new HashSet<>();
    }

    public boolean addRequisito(Disciplina requisito) {
        return requisitos.add(requisito);
    }
    
    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public Set<Disciplina> getRequisitos() {
        return requisitos;
    }
    
    @Override
    public int hashCode() {
    	return codigo.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Disciplina) {
    		Disciplina disciplina = (Disciplina)obj;
    		return this.codigo.equals(disciplina.codigo);
    	}
    	return false;
    }
}
