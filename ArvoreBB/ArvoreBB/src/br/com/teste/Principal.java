package br.com.teste;

import br.com.abb.ABB;

public class Principal {

	public static void main(String[] args) {
		ABB<Integer, Integer> arvore = new ABB<>();

		arvore.insercao(15, 15);
		arvore.insercao(30, 30);
		arvore.insercao(10, 10);
		arvore.insercao(12, 12);
		arvore.insercao(3, 3);
		arvore.insercao(11, 11);
		arvore.insercao(13, 13);	
		
		arvore.imprime(arvore);
		
		arvore.buscaIterativa(11);
		arvore.buscaIterativa(11);
		arvore.buscaIterativa(11);
		
		arvore.buscaIterativa(12);
		arvore.buscaIterativa(12);
		
		arvore.buscaIterativa(3);
		
		arvore.buscaIterativa(13);
		
		arvore.attArvore(arvore);
		
		System.out.println();
		arvore.imprime(arvore);
	}
}
