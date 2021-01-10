package com.abb;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class ABB<Chave extends Comparable<Chave>, Valor> implements IArvoreBB<Chave, Valor> {

	private No<Chave, Valor> raiz;

	public ABB() {
		raiz = null;
	}

	// INSERÇÃO
	@Override
	public void insercao(Chave chave, Valor valor) {
		No<Chave, Valor> novoNo = new No<>(chave, valor);
		if (this.raiz == null) {
			this.raiz = novoNo;
			return;
		} else {
			No<Chave, Valor> pai = raiz;
			No<Chave, Valor> paiAtual;
			while (true) {
				novoNo.setDistancia(novoNo.getDistancia() + 1);
				paiAtual = pai;
				if (novoNo.getChave().compareTo(paiAtual.getChave()) < 0) {
					pai = paiAtual.getEsq();
					if (pai == null) {
						paiAtual.setEsq(novoNo);
						novoNo.setPai(paiAtual);
						return;
					}
				} else if (novoNo.getChave().compareTo(paiAtual.getChave()) > 0) {
					pai = paiAtual.getDir();
					if (pai == null) {
						paiAtual.setDir(novoNo);
						novoNo.setPai(paiAtual);
						return;
					}
				} else {
					pai = paiAtual.getDir();
					if (pai == null) {
						paiAtual.setDir(novoNo);
						novoNo.setPai(paiAtual);
						return;
					} else {
						if (novoNo.getChave().compareTo(paiAtual.getChave()) < 0) {
							pai = paiAtual.getEsq();
							if (pai == null) {
								paiAtual.setEsq(novoNo);
								novoNo.setPai(paiAtual);
								return;
							}
						} else if (novoNo.getChave().compareTo(paiAtual.getChave()) > 0) {
							pai = paiAtual.getDir();
							if (pai == null) {
								paiAtual.setDir(novoNo);
								novoNo.setPai(paiAtual);
								return;
							}
						}
					}
				}
			}
		}
	}

	// IMPRESSÃO
	@Override
	public void imprime(ABB<Chave, Valor> arvore, String ordem) {
		// Scanner s = new Scanner(System.in);
		try {
			// System.out.println("Ordem de impressão:" + "\ne - Em Ordem " + "\nr - Pre
			// Ordem " + "\np - Pos Ordem ");
			// String ordem = s.nextLine();
			if (ordem.equals("e") || ordem.equals("E")) {
				emOrdem(arvore.raiz);
			} else if (ordem.equals("r") || ordem.equals("R")) {
				preOrdem(arvore.raiz);
			} else if (ordem.equals("p") || ordem.equals("P")) {
				posOrdem(arvore.raiz);
			} else {
				System.out.println("Opção inválida!");
				imprime(arvore, ordem);
			}
		} finally {
			// s.close();
		}
	}

	private void emOrdem(No<Chave, Valor> no) {
		if (no != null) {
			emOrdem(no.getEsq());
			System.out.println(no.getValor().toString());
			emOrdem(no.getDir());
		}
	}

	private void preOrdem(No<Chave, Valor> no) {
		if (no != null) {
			System.out.print(no.getValor().toString() + " ");
			preOrdem(no.getEsq());
			preOrdem(no.getDir());
		}
	}

	private void posOrdem(No<Chave, Valor> no) {
		if (no != null) {
			posOrdem(no.getEsq());
			posOrdem(no.getDir());
			System.out.print(no.getValor().toString() + " ");
		}
	}

	// BUSCA
	@Override
	public No<Chave, Valor> buscaIterativa(Chave chave) {
		No<Chave, Valor> no = this.raiz;
		while (!(no.getChave().equals(chave))) {
			if (chave.compareTo(no.getChave()) < 0) {
				no = no.getEsq();
			} else if (chave.compareTo(no.getChave()) > 0) {
				no = no.getDir();
			}
			if (no == null)
				throw new IllegalArgumentException("o nó não existe na arvore!");
		}
		return no;
	}

	@Override
	public No<Chave, Valor> buscaRecursiva(Chave chave) {
		return bRecursiveAux(this.raiz, chave);
	}

	private No<Chave, Valor> bRecursiveAux(No<Chave, Valor> no, Chave chave) {
		if (no == null) {
			return null;
		}
		if (no.getChave().equals(chave)) {
			return no;
		}
		if (chave.compareTo(no.getChave()) < 0) {
			No<Chave, Valor> esq = bRecursiveAux(no.getEsq(), chave);
			if (esq != null) {
				return esq;
			}
		} else if (chave.compareTo(no.getChave()) > 0) {
			No<Chave, Valor> dir = bRecursiveAux(no.getDir(), chave);
			if (dir != null) {
				return dir;
			}
		}
		return no;
	}

	// REMOÇÃO
	@Override
	public boolean remove(Chave chave) {
		No<Chave, Valor> pai = raiz;
		No<Chave, Valor> atual = raiz;
		boolean paiEsq = true;

		while (atual.getChave() != chave) {
			pai = atual;
			if ((chave.compareTo(atual.getChave()) < 0)) {
				paiEsq = true;
				atual = atual.getEsq();
			} else {
				paiEsq = false;
				atual = atual.getDir();
			}
			if (atual == null) {
				return false;
			}
		}

		if (grauNo(atual) == 0) {
			if (atual == raiz) {
				raiz = null;
			} else if (paiEsq) {
				pai.setEsq(null);
			} else {
				pai.setDir(null);
			}
		} else if (grauNo(atual) == 1) {
			if (atual.getDir() == null) {
				if (atual == raiz) {
					raiz = atual.getEsq();
				} else if (paiEsq) {
					pai.setEsq(atual.getEsq());
				} else {
					pai.setDir(atual.getEsq());
				}
			} else if (atual.getEsq() == null) {
				if (atual == raiz) {
					raiz = atual.getDir();
				} else if (paiEsq) {
					pai.setEsq(atual.getDir());
				} else {
					pai.setDir(atual.getDir());
				}
			}
		} else if (grauNo(atual) == 2) {
			No<Chave, Valor> sucessor = getSucessor(atual);
			if (atual == raiz) {
				raiz = sucessor;
			} else if (paiEsq) {
				pai.setEsq(sucessor);
			} else {
				pai.setDir(sucessor);
			}
			sucessor.setEsq(atual.getEsq());
		}
		return true;
	}

	private No<Chave, Valor> getSucessor(No<Chave, Valor> no) {
		No<Chave, Valor> sucessor = no;
		No<Chave, Valor> sucessorPai = no;
		No<Chave, Valor> atual = no.getDir();

		while (atual != null) {
			sucessorPai = sucessor;
			sucessor = atual;
			atual = atual.getEsq();
		}
		if (sucessor != no.getDir()) {
			sucessorPai.setEsq(sucessor.getDir());
			sucessor.setDir(no.getDir());
		}
		return sucessor;
	}

	// OUTROS METODOS
	@Override
	public int grauNo(No<Chave, Valor> no) {
		int cont = 0;
		if (no == null) {
			return 0;
		} else {
			if (no.getEsq() != null) {
				cont++;
			}
			if (no.getDir() != null) {
				cont++;
			}
			return cont;
		}
	}

	@Override
	public int grauArvore(ABB<Chave, Valor> arvore) {
		return maxGrau(arvore.raiz);
	}

	private int maxGrau(No<Chave, Valor> no) {
		if (no == null) {
			return 0;
		}
		int esq = grauNo(no.getEsq());
		int dir = grauNo(no.getDir());

		int maior = Math.max(esq, dir);
		return maior;
	}

	@Override
	public int alturaArvore(ABB<Chave, Valor> arvore) {
		return alturaAux(arvore.raiz) + 1;
	}

	private int alturaAux(No<Chave, Valor> no) {
		if (no == null) {
			return -1;
		}

		int esq = alturaAux(no.getEsq());
		esq++;
		int dir = alturaAux(no.getDir());
		dir++;
		int maior = Math.max(esq, dir);

		return maior;
	}

	@Override
	public int tamanho() {
		return contador();
	}

	public boolean isEmpty() {
		return null == raiz;
	}

	public void limpar() {
		raiz = null;
	}

	private int contador() {
		No<Chave, Valor> atual = raiz;
		int tam = 0;
		Stack<No<Chave, Valor>> pilha = new Stack<No<Chave, Valor>>();
		while (!pilha.isEmpty() || atual != null) {
			if (atual != null) {
				pilha.push(atual);
				atual = atual.getEsq();
			} else {
				tam++;
				atual = pilha.pop();
				atual = atual.getDir();
			}
		}
		return tam;
	}

	public ABB<Chave, Valor> clone() {
		ABB<Chave, Valor> clone = new ABB<>();
		clone.raiz = cloneArvore(raiz);

		return clone;
	}

	private No<Chave, Valor> cloneArvore(No<Chave, Valor> raiz) {
		if (raiz == null) {
			return null;
		}

		No<Chave, Valor> raizClone = new No<>(raiz.getChave(), raiz.getValor());
		raizClone.setEsq(cloneArvore(raiz.getEsq()));
		raizClone.setDir(cloneArvore(raiz.getDir()));

		return raizClone;
	}

	@Override
	public Iterator<No<Chave, Valor>> iterator() {
		return new IteradorABB<>(raiz);
	}

	// MAIOR E MENOR CHAVE
	public Chave minimo() {
		if (isEmpty())
			throw new NoSuchElementException("Arvore vazia!");
		return minimo(raiz).getChave();
	}

	private No<Chave, Valor> minimo(No<Chave, Valor> no) {
		if (no.getEsq() == null) {
			return no;
		} else {
			return minimo(no.getEsq());
		}
	}

	public Chave maximo() {
		if (isEmpty())
			throw new NoSuchElementException("Arvore vazia!");
		return maximo(raiz).getChave();
	}

	private No<Chave, Valor> maximo(No<Chave, Valor> no) {
		if (no.getDir() == null) {
			return no;
		} else {
			return maximo(no.getDir());
		}
	}

	// SUCESSOR E ANTECESSOR
	public No<Chave, Valor> sucessor(No<Chave, Valor> no) {
		if (no == null) {
			return null;
		}
		if (no.getDir() != null) {
			return minimo(no.getDir());
		}
		No<Chave, Valor> sucessor = no.getPai();
		No<Chave, Valor> atual = no;
		while (sucessor != null && atual == sucessor.getDir()) {
			atual = sucessor;
			sucessor = sucessor.getPai();
		}
		return sucessor;
	}

	public No<Chave, Valor> antecessor(No<Chave, Valor> no) {
		if (no == null) {
			return null;
		}
		if (no.getEsq() != null) {
			return maximo(no.getEsq());
		}
		No<Chave, Valor> pai = no.getPai();

		No<Chave, Valor> antecessor = pai;
		No<Chave, Valor> atual = no;
		while (antecessor != null && atual == antecessor.getEsq()) {
			atual = antecessor;
			antecessor = antecessor.getPai();
		}
		return antecessor;
	}

	// ARVORE CRESCENTE E ARVORE DECRESCENTE
	public ABB<Chave, Valor> arvoreCrescente(ABB<Chave, Valor> a) {
		ABB<Chave, Valor> nova = new ABB<>();
		while (a.raiz != null) {
			No<Chave, Valor> no = minimo(raiz);
			nova.insercao(no.getChave(), no.getValor());
			a.remove(no.getChave());
		}
		return nova;
	}

	public ABB<Chave, Valor> arvoreDecrescente(ABB<Chave, Valor> a) {
		ABB<Chave, Valor> nova = new ABB<>();
		while (a.raiz != null) {
			No<Chave, Valor> no = maximo(raiz);
			nova.insercao(no.getChave(), no.getValor());
			a.remove(no.getChave());
		}
		return nova;
	}

	// OBJECT IGUAIS
	public ABB<Chave, Valor> objIguais(ABB<Chave, Valor> a, ABB<Chave, Valor> b){
		return objIguais(a.raiz, b.raiz);
	}
	
	private ABB<Chave, Valor> objIguais(No<Chave, Valor> a, No<Chave, Valor> b) { 
		ABB<Chave, Valor> novaArvore = new ABB<>();
        if (a == null && b == null) {
            return null; 
        }   
        if (a != null && b != null) {
        	if(a.getValor().equals(b.getValor())) {
        		novaArvore.insercao(a.getChave(), a.getValor());
        	}
        	objIguais(a.getEsq(), b.getEsq());
            objIguais(a.getDir(), b.getDir()); 
        	
        }      
        return novaArvore;
    }	

	// ALCANCE MAXIMO
	public void alcanceMax(No<Chave, Valor> no, int dist) {
		if(no.getDistancia() <= dist) {
			System.out.println("Obj alcançável");
		} else {
			System.out.println("Obj não alcançável! Distância máx permitida de: " + no.getDistancia());
		}
	}


}
