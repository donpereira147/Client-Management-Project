package gestao;

public class Cliente 
{
	private int nif, telefone;
	private String nome, morada;
	
	public Cliente()
	{
		nif= telefone = -1;
		nome = morada = "";
	}
	
	public Cliente(int nif, int telefone, String nome, String morada) {
		super();
		this.nif = nif;
		this.telefone = telefone;
		this.nome = nome;
		this.morada = morada;
	}
	
	public Cliente(Cliente c)
	{
		this.nif = c.getNIF();
		this.telefone = c.getTelefone();
		this.nome = c.getNome();
		this.morada = c.getMorada();
	
	}
	
	public int getNIF() {
		return nif;
	}
	public void setNIF(int nif) {
		this.nif = nif;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Cliente:")
			.append(nif).append(", ")
			.append(telefone).append(", ")
			.append(nome).append(", ")
			.append(morada);
		return sb.toString();
	}
	
	public Cliente clone()
	{
		return new Cliente(nif, telefone, nome, morada);
	}
}
