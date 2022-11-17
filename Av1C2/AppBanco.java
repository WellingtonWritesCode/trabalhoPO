package Av1C2;

import io.InOut;

public class AppBanco {
	public static Vetor movements = new CadConta();
	public static void main(String[] args){

		int opcao = 1000;
		while(opcao!=6) {
			opcao = InOut.leInt("MENU \n"
					+ "1 - CADASTRAR CONTA.\n"
					+ "2 - SALDO.\n"
					+ "3 - SAQUE. \n"
					+ "4 - DEPOSITO. \n"
					+ "5 - TRANSFERENCIA. \n"
					+ "6 - SAIR");

			switch(opcao) {
			case 1: cadastrarConta();
			break;
			case 2: mostrarSaldo();
			break;
			case 3: sacar();
			break;
			case 4: depositar();
			break;
			case 5: transferir();
			break;
			case 6: InOut.msgDeAviso("ATÉ LOGO!", "Saindo...");
			break;
			}

		}
	}
	public static void cadastrarConta() {
		String nome = InOut.leString("Digite o seu nome: ");
		String CPF = InOut.leString("Digite o seu CPF: ");
		Conta conta = new Conta(nome, CPF);
		movements.insere(conta);
		InOut.msgDeAviso("Sua conta foi criada!", "Numero da conta: "+conta.getNumero());
	}
	public static boolean mostrarSaldo() {
		String num = InOut.leString("Digite seu numero da conta: ");
		for(int i=0;i<movements.getTamanho();i++) {
			if(movements.getConta(i).getNumero().equals(num)) {
				InOut.msgDeAviso("SALDO:", ""+movements.getConta(i).saldo());
				return true;
			}
		}
		InOut.msgDeAviso("ERRO", "Esta conta não existe!");
		return false;
	}
	public static boolean sacar() {
		String num = InOut.leString("Digite seu numero da conta: ");
		double saca = 0;
		for(int i=0;i<movements.getTamanho();i++) {
			if(movements.getConta(i).getNumero().equals(num)) {
				InOut.msgDeAviso("SALDO:", "Seu saldo é: "+movements.getConta(i).saldo());
				saca = InOut.leDouble("Digite quanto irá sacar: ");
				movements.getConta(i).saque(saca);
				InOut.msgDeAviso("Saque realizado com sucesso!", null);
				return true;
			}
		}
		InOut.msgDeAviso("ERRO", "Esta conta não existe!");
		return false;
	}
	public static boolean depositar() {
		double valor;
		String num = InOut.leString("Digite seu numero da conta: ");
		for(int i=0;i<movements.getTamanho();i++) {
			if(movements.getConta(i).getNumero().equals(num)) {
				valor = InOut.leDouble("Digite o valor a ser depositado: ");
				movements.getConta(i).deposito(valor);
				return true;

			}
		}
		InOut.msgDeAviso("ERRO", "Esta conta não existe!");
		return false;
	}
	public static boolean transferir() {
		double valor = 0;
		String num1 = InOut.leString("Digite seu numero da conta: "); 
		String num2;
		for(int i=0;i<movements.getTamanho();i++) {
			if(movements.getConta(i).getNumero().equals(num1)) {

				valor = InOut.leDouble("Digite o valor a ser transferido: ");
				movements.getConta(i).saque(valor);
			}
			if(i==movements.getTamanho()-1) {
				InOut.msgDeAviso("ERRO", "Esta conta não existe!");
				return false;
			}
		}
		for(int i=0;i<movements.getTamanho();i++) {
			num2 = InOut.leString("Digite o numero da conta a ser transferido: ");
			if(movements.getConta(i).getNumero().equals(num2)) {
				movements.getConta(i).deposito(valor);
				return true;
			}
			if(i==movements.getTamanho()-1) {
				InOut.msgDeAviso("ERRO", "Esta conta não existe!");
			}
		}
		return false;
	}
}



