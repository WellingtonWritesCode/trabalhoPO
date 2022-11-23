package pesquisa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Av1C2.Conta;
import Av1C2.ContaEspecial;

public class TesteMetodos {

	public static void main(String[] args) {
		String[] metodos = { "ABB", "AVL", "Hashing" };
		int[] tamanhos = { 500, 1000, 5000, 10000, 50000 };
		String[] tipos = { "alea", "inv", "ord" };
		String arquivo;
		long tempoInicial;
		ArrayList<String> saida = new ArrayList<>();
		try {
			for (String metodo : metodos) {
				for (int tamanho : tamanhos) {
					for (String tipo : tipos) {
						tempoInicial = System.currentTimeMillis();
						Metodo contas;
						for (int i = 0; i < 5; i++) {
							arquivo = tamanho + tipo + ".txt";
							ArrayList<String> nomes = leNomes();
							ArrayList<String> pesquisa = new ArrayList<>();
							System.out.println(metodo + ", " + tamanho + ", " + tipo);
							switch (metodo) {
							case "ABB":
								contas = new ABB();
								contas.insereTodos(leContas(arquivo));
								contas = ((ABB) contas).balancear();
								break;
							case "AVL":
								contas = new AVL();
								contas.insereTodos(leContas(arquivo));
								break;
							default:
								contas = new Hashing(tamanho);
								contas.insereTodos(leContas(arquivo));
							}
							for (String nome : nomes) {
								pesquisa.add(formataPesquisa(nome, contas.pesquisa(nome)));
							}
							escreveSaida("src/pesquisa/Arquivos/" + metodo + arquivo, pesquisa);
						}
						double tempoMedio = (System.currentTimeMillis() - tempoInicial) / 5;
						saida.add(String.format("Tempo medio (%s|%d|%s): %.2f ms", metodo, tamanho, tipo, tempoMedio));
					}
				}
			}
			escreveSaida("src/pesquisa/Arquivos/tempos.txt", saida);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado");
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Arquivo nao pode ser aberto para gravacao");
		}
	}

	private static ArrayList<String> leNomes() throws FileNotFoundException {
		ArrayList<String> nomes = new ArrayList<>();
		Scanner entrada = new Scanner(new File("src/Entrada/nome.txt"));
		while (entrada.hasNext()) {
			nomes.add(entrada.nextLine());
		}
		entrada.close();
		return nomes;
	}

	private static ArrayList<Conta> leContas(String arquivo) throws FileNotFoundException {
		ArrayList<Conta> contas = new ArrayList<>();
		Scanner entrada = new Scanner(new File("src/Entrada/cliente"+arquivo));
		while (entrada.hasNext()) {
			contas.add(parseLine(entrada.nextLine()));
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
		if (valores.length == 5) {
			double limite = Double.parseDouble(valores[4]);
			novaConta = new ContaEspecial(numero, nome, cpf, limite);
		} else {
			novaConta = new Conta(numero, nome, cpf);
		}
		novaConta.deposito(valor_em_conta);
		return novaConta;
	}

	private static void escreveSaida(String nome, ArrayList<String> linhas) throws IOException {
		FileWriter writer = new FileWriter(new File(nome), false);
		PrintWriter saida = new PrintWriter(writer);
		for (String linha : linhas) {
			saida.println(linha + "\n");
		}
		saida.close();
	}

	private static String formataPesquisa(String nome, List<Conta> pesquisa) {
		String out = "NOME " + nome + ":";
		if (pesquisa == null) {
			out += "\nNAO HA NENHUMA CONTA COM O NOME " + nome;
		} else {
			for (Conta c : pesquisa) {
				out += "\nConta " + c.getNumero() + "\tSaldo R$" + c.saldo();
			}
		}
		return out;
	}

}
