package Av1C2;

public class ContaEspecial extends Conta implements Transacao{
	private double limite;

	public ContaEspecial(String numero, String nome, String cpf, double limite) {
		super(numero, nome, cpf);
		this.limite = limite;
	}

	public Double saldo() {
		return valor_na_conta+limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	@Override
	public String toString() {
		return super.toString() + ";"+limite;
	}




}
