package pesquisa;

import java.util.LinkedList;

import Av1C2.Conta;

public class AVL extends Metodo {

	private NoAVL raiz;
	private boolean h = true;

	@Override
	public void insere(Conta conta) {
		raiz = insere(conta, raiz);
	}

	private NoAVL insere(Conta conta, NoAVL no) {
		if (no == null) {
			h = true;
			return new NoAVL(conta);
		} else if (no.getNome().equals(conta.getNome())) {
			no.setInfo(conta);
			return no;
		}
		if (no.getNome().compareTo(conta.getNome()) > 0) {
			no.setEsq(insere(conta, no.getEsq()));
			no = balancearDir(no);
			return no;
		} else {
			no.setDir(insere(conta, no.getDir()));
			no = balancearEsq(no);
			return no;
		}
	}

	private NoAVL balancearDir(NoAVL no) {
		if (h) {
			switch (no.getFatorBalanceamento()) {
			case 1:
				no.setFatorBalanceamento((byte) 0);
				h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) -1);
				break;
			case -1:
				no = rotacaoDireita(no);
			}
		}
		return no;
	}

	private NoAVL balancearEsq(NoAVL no) {
		if (h) {
			switch (no.getFatorBalanceamento()) {
			case -1:
				no.setFatorBalanceamento((byte) 0);
				h = false;
				break;
			case 0:
				no.setFatorBalanceamento((byte) 1);
				break;
			case 1:
				no = rotacaoEsquerda(no);
			}
		}
		return no;
	}

	private NoAVL rotacaoDireita(NoAVL no) {
		NoAVL temp1, temp2;
		temp1 = no.getEsq();
		if (temp1.getFatorBalanceamento() == -1) {
			no.setEsq(temp1.getDir());
			temp1.setDir(no);
			no.setFatorBalanceamento((byte) 0);
			no = temp1;
		} else {
			temp2 = temp1.getDir();
			temp1.setDir(temp2.getEsq());
			temp2.setEsq(temp1);
			no.setEsq(temp2.getDir());
			temp2.setDir(no);
			if (temp2.getFatorBalanceamento() == -1) {
				no.setFatorBalanceamento((byte) 1);
			} else {
				no.setFatorBalanceamento((byte) 0);
			}
			if (temp2.getFatorBalanceamento() == 1) {
				temp1.setFatorBalanceamento((byte) -1);
			} else {
				temp1.setFatorBalanceamento((byte) 0);
			}
			no = temp2;
		}
		no.setFatorBalanceamento((byte) 0);
		this.h = false;
		return no;
	}

	private NoAVL rotacaoEsquerda(NoAVL no) {
		NoAVL temp1, temp2;
		temp1 = no.getDir();
		if (temp1.getFatorBalanceamento() == 1) {
			no.setDir(temp1.getEsq());
			temp1.setEsq(no);
			no.setFatorBalanceamento((byte) 0);
			no = temp1;
		} else {
			temp2 = temp1.getEsq();
			temp1.setEsq(temp2.getDir());
			temp2.setDir(temp1);
			no.setDir(temp2.getEsq());
			temp2.setEsq(no);
			if (temp2.getFatorBalanceamento() == 1) {
				no.setFatorBalanceamento((byte) -1);
			} else {
				no.setFatorBalanceamento((byte) 0);
			}
			if (temp2.getFatorBalanceamento() == -1) {
				temp1.setFatorBalanceamento((byte) 1);
			} else {
				temp1.setFatorBalanceamento((byte) 0);
			}
			no = temp2;
		}
		no.setFatorBalanceamento((byte) 0);
		h = false;
		return no;
	}

	@Override
	public LinkedList<Conta> pesquisa(String nome) {
		NoAVL no = pesquisa(nome, raiz);
		if (no == null) {
			return null;
		}
		return no.getInfo();
	}

	private NoAVL pesquisa(String nome, NoAVL no) {
		if (no == null) {
			return null;
		}
		if (no.getNome().compareTo(nome) < 0) {
			return pesquisa(nome, no.getEsq());
		}
		if (no.getNome().compareTo(nome) > 0) {
			return pesquisa(nome, no.getDir());
		}
		return no;
	}

}
