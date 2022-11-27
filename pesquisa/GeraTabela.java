package pesquisa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeraTabela {

	public static void main(String[] args) {
		String[] metodos = { "ABB", "AVL", "Hashing" };
		int[] tamanhos = { 500, 1000, 5000, 10000, 50000 };
		String[] tipos = { "alea", "inv", "ord" };
		String[][] tabela = new String[3][5];
		for(String metodo : metodos) {
			try {
				for(int i = 0; i < tipos.length; i++) {
					for(int j = 0; j < tamanhos.length; j++) {
						tabela[i][j] = getTempo(metodo, tipos[i], tamanhos[j]);
					}
				}
			} catch(FileNotFoundException e) {
				System.out.println("Arquivo nao encontrado");
			}
			System.out.println("\n" + metodo + ":");
			printTabela(tabela);
		}
	}
	
	private static String getTempo(String metodo, String tipo, int tamanho) throws FileNotFoundException {
		
		Scanner entrada = new Scanner(new File("src/pesquisa/Arquivos/tempos.txt"));
		String linha = "";
		do {
			linha = entrada.nextLine();
		}while(!valido(linha, metodo, tipo, tamanho));
		double tempo = Double.parseDouble(linha.split(";")[3]);
		
		if(tempo < 1000) {
			return String.format("%.0f ms", tempo);
		}
		return String.format("%.2f s", tempo/1000);
	}
	
	private static boolean valido(String linha, String metodo, String tipo, int tamanho) {
		String[] valores = linha.split(";");
		return valores[0].equals(metodo)
			&& Integer.parseInt(valores[1]) == tamanho
			&& valores[2].equals(tipo);
	}

	private static void printTabela(String[][] tabela) {
		String[] linhas = {"Alea", "Inv", "Ord"};
		System.out.println("\t|500\t|1000\t|5000\t|10000\t|50000");
		for(int i = 0; i < tabela.length; i++) {
			for(String tempo : tabela[i]) {
				linhas[i] += "\t|" + tempo;
			}
		}
		for(String linha : linhas) {
			System.out.println(linha);
		}
	}
	
}

