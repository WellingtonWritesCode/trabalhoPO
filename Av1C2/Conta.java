package Av1C2;

public class Conta implements Transacao {
	private static int sequence = 0;
	
	private String nome, cpf;
	private String numero;
	protected Double valor_na_conta;

	public Conta(String numero, String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.numero = numero;
		this.valor_na_conta = (double) 0;
	}
	
	public Conta(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.numero = "01" + sequence++;
		this.valor_na_conta = (double) 0;
	}

	public void deposito(Double valor){
		valor_na_conta += valor;
	}

	public Boolean saque(Double valor) {
		if(valor_na_conta>valor) {
			valor_na_conta -= valor;
			return true;
		}
		return false;
	}

	public Double saldo() {
		return valor_na_conta;
	}

	public Boolean transferencia(Double valor, Conta outraConta) {
		if(valor_na_conta>=valor) {
			this.valor_na_conta-=valor;
			outraConta.deposito(valor);
			return true;
		}
		return false;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setValor_na_conta(double valor_na_conta) {
		this.valor_na_conta = valor_na_conta;
	}
	
	public int compareTo(Conta outra) {
		String aux1 = this.nome + this.cpf + this.numero;
		String aux2 = outra.getNome() + outra.getCpf() + outra.getNumero();
		return aux1.compareTo(aux2);
	}

	public String toString() {
		return String.format("%s;%s;%s;%s", numero, nome, cpf, valor_na_conta);
	}
	
}
