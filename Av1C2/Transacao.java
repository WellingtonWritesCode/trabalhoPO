package Av1C2;

public interface Transacao {
	void deposito(Double valor);
	Boolean saque(Double valor);
	Double saldo();
	Boolean transferencia(Double valor, Conta outraConta);
}
