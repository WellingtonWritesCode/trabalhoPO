package pesquisa;

import java.util.LinkedList;

import Av1C2.Conta;

public class NoAVL {
	
	private LinkedList<Conta> info;
	private String nome;
	private NoAVL esq, dir;
	private byte fatorBalanceamento;
	
	public NoAVL(Conta conta) {
		info = new LinkedList<>();
		nome = conta.getNome();
		info.add(conta);
	}
	
	public NoAVL(LinkedList<Conta> contas) {
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

	public NoAVL getEsq() {
		return esq;
	}

	public void setEsq(NoAVL esq) {
		this.esq = esq;
	}

	public NoAVL getDir() {
		return dir;
	}

	public void setDir(NoAVL dir) {
		this.dir = dir;
	}

	public byte getFatorBalanceamento() {
		return fatorBalanceamento;
	}

	public void setFatorBalanceamento(byte fatorBalanceamento) {
		this.fatorBalanceamento = fatorBalanceamento;
	}
	
}
