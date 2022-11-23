package pesquisa;

import java.util.ArrayList;
import java.util.LinkedList;

import Av1C2.Conta;

public class ABB extends Metodo {

	private NoABB raiz;
	private int nElem = 0;
	
	public NoABB getRaiz() {
		return raiz;
	}
	public int getnElem() {
		return nElem;
	}
	
	@Override
	public LinkedList<Conta> pesquisa(String nome){
		NoABB no = pesquisa(nome, raiz);
		if(no == null) {
			return null;
		}
		return no.getInfo();
	}
	
	private NoABB pesquisa(String nome, NoABB no) {
		if(no == null) {
			return null;
		}
		if(no.getNome().compareTo(nome) < 0) {
			return pesquisa(nome, no.getEsq());
		}
		if(no.getNome().compareTo(nome) > 0) {
			return pesquisa(nome, no.getDir());
		}
		return no;
	}
	
	@Override
	public void insere(Conta conta) {
		raiz = insere(conta, raiz);
		nElem++;
	}
	
	private NoABB insere(Conta conta, NoABB no) {
		if(no == null) {
			return new NoABB(conta);
		} else if(no.getNome().equals(conta.getNome())) {
			no.setInfo(conta);
			return no;
		}
		if(no.getNome().compareTo(conta.getNome()) > 0) {
			no.setEsq(insere(conta, no.getEsq()));
		} else if(no.getNome().compareTo(conta.getNome()) < 0) {
			no.setDir(insere(conta, no.getDir()));
		}
		return no;
	}
	
	public ArrayList<Conta> camInOrdem(){
		return fazerCaminho(raiz, new ArrayList<>());
	}
	
	private ArrayList<Conta> fazerCaminho(NoABB no, ArrayList<Conta> caminho) {
		if(no != null) {
			caminho = fazerCaminho(no.getEsq(), caminho);
			caminho.addAll(no.getInfo());
			caminho = fazerCaminho(no.getDir(), caminho);
		}
		return caminho;
	}
	
	public ABB balancear() {
		ABB arv = new ABB();
		ArrayList<Conta> contas = camInOrdem();
		balancear(arv, contas, 0, contas.size()-1);
		return arv;
	}
	
	private void balancear(ABB arv, ArrayList<Conta> contas, int inicio, int fim) {
		if(fim >= inicio) {
			int meio = (fim+inicio)/2;
			arv.insere(contas.get(meio));
			balancear(arv, contas, inicio, meio-1);
			balancear(arv, contas, meio+1, fim);
		}
	}
	
}
