package Av1C2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TesteMetodos {

	public static void main(String[] args) {
		String[] metodos = {"shell", "quick"};
		int[] tamanhos = {500, 1000, 5000, 10000, 50000};
		String[] tipos = {"alea", "inv", "ord"};
		String nome;
		long tempoInicial;
		double tempoFinal;
		CadConta contas;
		
		try {
			for(String metodo : metodos) {
				for(int tamanho : tamanhos) {
					for(String tipo : tipos) {
						nome = "src/Entrada/cliente" + tamanho + tipo + ".txt";
						tempoInicial = System.currentTimeMillis();
						contas = leContas(nome);
						if(metodo.equals("shell")) {
							contas.shellSort();
						} else {
							contas.quickSort();
						}
						tempoFinal = (System.currentTimeMillis() - tempoInicial)/1000.0;
						nome = "src/Saida/" + metodo + tamanho + tipo + ".txt";
						escreveSaida(contas, nome, tempoFinal);
					}
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado");
		} catch (IOException e) {
			System.out.println("Arquivo nao pode ser aberto para gravacao");
		}
	}
	
	private static void escreveSaida(CadConta contas, String nome, double tempoFinal) throws IOException {
		FileWriter writer = new FileWriter(new File(nome), false);
		PrintWriter saida = new PrintWriter(writer);
		saida.println("Tempo levado para ordenar: " + tempoFinal + " segundos.\n");
		for(int i = 0; i < contas.getTamanho(); i++) {
			saida.println(contas.getConta(i));
		}
		saida.close();
	}

	private static CadConta leContas(String arquivo) throws FileNotFoundException {
		CadConta contas = new CadConta();
		Scanner entrada = new Scanner(new File(arquivo));
		while(entrada.hasNext()) {
			contas.insere(parseLine(entrada.nextLine()));
		}
		entrada.close();
		return contas;
	}
	
	private static Conta parseLine(String line) {
		Conta novaConta;
		String[] valores = line.split(";");
		String numero = valores[0];
		String nome = valores[1];
		String cpf = valores[2];
		double valor_em_conta = Double.parseDouble(valores[3]);
		if(valores.length == 5) {
			double limite = Double.parseDouble(valores[4]);
			novaConta = new ContaEspecial(numero, nome, cpf, limite);
		} else {
			novaConta = new Conta(numero, nome, cpf);
		}
		novaConta.deposito(valor_em_conta);
		return novaConta;
	}

}
