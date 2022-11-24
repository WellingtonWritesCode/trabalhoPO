package pesquisa;

import java.util.LinkedList;

import Av1C2.Conta;

public class Hashing extends Metodo {
	private LinkedList<Conta>[] lista;
	
	@SuppressWarnings("unchecked")
	public Hashing(int tamanho) {
		int aux;
		switch(tamanho) {
		case 1000:
			aux = 1103;
			break;
		case 5000:
			aux = 5501;
			break;
		case 10000:
			aux = 11001;
			break;
		case 50000:
			aux = 55001;
			break;
		default:
			aux = 557;
		}
		lista = new LinkedList[aux];
	}
	
	public Hashing() {
		this(500);
	}
	
	@Override
	public void insere(Conta conta) {
		int index = getIndex(conta.getNome());
		if(lista[index] == null) {
			lista[index] = new LinkedList<Conta>();
		}
		lista[index].add(conta);
	}
	
	@Override
	public LinkedList<Conta> pesquisa(String nome) {
		int index = getIndex(nome);
		return lista[index];
	}
	
	private int getIndex(String str) {
		return Math.abs(str.hashCode()%lista.length);
	}
}