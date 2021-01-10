package com.abb;

public interface IArvoreBB<Chave extends Comparable<Chave>, Valor>  extends Iterable<No<Chave, Valor>> {
	
	public void insercao(Chave chave, Valor valor);
	public void imprime(ABB<Chave, Valor> arvore, String ordem);
	public No<Chave, Valor> buscaIterativa(Chave chave);
	public No<Chave, Valor> buscaRecursiva(Chave chave);
	public boolean remove(Chave chave);
	public int grauNo(No<Chave, Valor> no);
	public int grauArvore(ABB<Chave, Valor> arvore);
	public int alturaArvore(ABB<Chave, Valor> arvore);
	public int tamanho();

	

}
