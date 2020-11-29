package entity;

public class Servidor {

	private String cpf;
	private String nome;
	private Servidor chefe;
	
	public Servidor(String cpf) {
		this(cpf, null, null);
	}
	
	public Servidor(String cpf, String nome) {
		this(cpf, nome, null);
	}
	
	public Servidor(String cpf, String nome, Servidor chefe) {
		this.cpf = cpf;
		this.nome = nome;
		this.chefe = chefe;
	}
	
	public Servidor getChefe() {
		return chefe;
	}
	
	public void setChefe(Servidor chefe) {
		this.chefe = chefe;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Object[] toArray() {
		return new Object[] {
				cpf,
				nome,
				chefe != null? chefe.cpf: null,
				chefe != null? chefe.nome: null
		};
	}
}

