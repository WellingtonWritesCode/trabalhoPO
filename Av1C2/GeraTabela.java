package Av1C2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeraTabela {

	public static void main(String[] args) {
		int[] tamanhos = {500, 1000, 5000, 10000, 50000};
		String[] tipos = {"alea", "inv", "ord"};
		String nome;
		double[][] tabelaShell = new double[3][5];
		double[][] tabelaQuick = new double[3][5];
	
		try {
			for(int i = 0; i < tipos.length; i++) {
				for(int j = 0; j < tamanhos.length; j++) {
					nome = "src/Saida/shell" + tamanhos[j] + tipos[i] + ".txt";
					tabelaShell[i][j] = getTempo(nome);
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado");
		}
		
		try {
			for(int i = 0; i < tipos.length; i++) {
				for(int j = 0; j < tamanhos.length; j++) {
					nome = "src/Saida/quick" + tamanhos[j] + tipos[i] + ".txt";
					tabelaQuick[i][j] = getTempo(nome);
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado");
		}
		System.out.println("Shell:");
		printTabela(tabelaShell);
		System.out.println("\nQuick:");
		printTabela(tabelaQuick);
	}
	
	private static double getTempo(String arquivo) throws FileNotFoundException {
		Scanner entrada = new Scanner(new File(arquivo));
		double tempo = Double.parseDouble(entrada.nextLine().split(" ")[4]);
		entrada.close();
		return tempo;
	}
	
	private static void printTabela(double[][] tabela) {
		String[] linhas = {"Alea", "Inv", "Ord"};
		System.out.println("\t|500\t|1000\t|5000\t|10000\t|50000");
		for(int i = 0; i < tabela.length; i++) {
			for(double tempo : tabela[i]) {
				linhas[i] += "\t|" + tempo;
			}
		}
		for(String linha : linhas) {
			System.out.println(linha);
		}
	}
	
}
