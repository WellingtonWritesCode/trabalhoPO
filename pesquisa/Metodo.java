package pesquisa;

import java.util.List;

import Av1C2.Conta;

public abstract class Metodo {
	
	public abstract void insere(Conta conta);
	
	public void insereTodos(Iterable<Conta> contas) {
		for(Conta c : contas) {
			insere(c);
		}
	}
	
	public abstract List<Conta> pesquisa(String nome);
	
}
