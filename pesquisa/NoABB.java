package pesquisa;

import java.util.LinkedList;

import Av1C2.Conta;

public class NoABB {
	
	private LinkedList<Conta> info;
	private String nome;
	private NoABB esq, dir;
	
	public NoABB(Conta conta) {
		info = new LinkedList<>();
		nome = conta.getNome();
		info.add(conta);
	}
	
	public NoABB(LinkedList<Conta> contas) {
		info = contas;
		nome = contas.get(0).getNome();
	}
	
	public LinkedList<Conta> getInfo(){
		return info;
	}
	
	public void setInfo(Conta conta) {
		info.add(conta);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public NoABB getEsq() {
		return esq;
	}

	public void setEsq(NoABB esq) {
		this.esq = esq;
	}

	public NoABB getDir() {
		return dir;
	}

	public void setDir(NoABB dir) {
		this.dir = dir;
	}
	
}
