package com.unit.complementar;

import javax.swing.JOptionPane;
import com.abb.ABB;
import com.abb.No;

public class Main {

	ABB<String, Suspeito> suspeitos = new ABB<>();
	Suspeito s = new Suspeito();

	public static void main(String[] args) {
		Main principal = new Main();
		principal.Menu();
	}

	public void Menu() {
		int opcao;
		do {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(
							"++++++ Menu +++++++" + 
							"\n1. Cadastrar Suspeito" + 
							"\n2. Adcionar Cumplice" + 
							"\n3. Listar cumplices" + 
							"\n4. Listar cumplices" + 
							"\n5. Cumplices em comum" + 
							"\n6. Alcance máximo" + 
							"\n7. SAIR"));
			switch (opcao) {
			case 1:
				cadastrarSuspeito();
				break;
			case 2:
				addCumplice();
				break;
			case 3:
				listarCumplices();
				break;
			case 4:
				listarCrimes();
				break;
			case 5:
				cumplicesEmComum();
				break;
			case 6:
				alcanceSuspeito();
				break;
			case 7:
				// SAIR
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcao invalida. Tente novamente!");
				break;
			}
		} while (opcao != 7);
	}

	public void cadastrarSuspeito() {
		/*
		 * String nome = JOptionPane.showInputDialog("Nome do suspeito:"); Suspeito
		 * suspeito = new Suspeito(nome); addCrimes(suspeito);
		 * 
		 * this.suspeitos.insercao(suspeito.getNome(), suspeito);
		 */
		Suspeito s1 = new Suspeito("Eliseu");
		Suspeito s2 = new Suspeito("Marcelo");
		Suspeito s3 = new Suspeito("Wellington");
		Suspeito s4 = new Suspeito("Lula");
		Suspeito s5 = new Suspeito("Aecio");
		Suspeito s6 = new Suspeito("Eunicio");
		Suspeito s7 = new Suspeito("Romero");
		Suspeito s8 = new Suspeito("Gedel");

		this.suspeitos.insercao(s1.getNome(), s1);
		this.suspeitos.insercao(s2.getNome(), s2);
		this.suspeitos.insercao(s3.getNome(), s3);
		this.suspeitos.insercao(s4.getNome(), s4);
		this.suspeitos.insercao(s5.getNome(), s5);
		this.suspeitos.insercao(s6.getNome(), s6);
		this.suspeitos.insercao(s7.getNome(), s7);
		this.suspeitos.insercao(s8.getNome(), s8);

	}

	public void addCumplice() {
		Suspeito suspeito = buscaSuspeito();
		Suspeito cumplice = buscaSuspeito();

		s.addCumplice(suspeito, cumplice);
	}

	public void addCrimes(Suspeito suspeito) {
		int opcao;
		do {
			opcao = Integer.parseInt(
					JOptionPane.showInputDialog("======== MENU ========" + "\n1. Cadastrar crimes" + "\n2. SAIR"));
			switch (opcao) {
			case 1:
				String descr = JOptionPane.showInputDialog("Descrição do crime:");
				Crime crime = new Crime(descr);
				suspeito.getCrimes().insercao(descr, crime);
				break;
			case 2:
				// Sair
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcao invalida. Tente novamente!");
				break;
			}
		} while (opcao != 2);
	}

	public void listarCumplices() {
		Suspeito suspeito = buscaSuspeito();

		s.listarCumplices(suspeito);
	}

	public void listarCrimes() {
		Suspeito suspeito = buscaSuspeito();

		s.listarCrimes(suspeito);
	}

	public void cumplicesEmComum() {
		Suspeito suspeito1 = buscaSuspeito();
		Suspeito suspeito2 = buscaSuspeito();

		ABB<String, Suspeito> emComum = suspeitos.objIguais(suspeito1.getCumplices(), suspeito2.getCumplices());

		emComum.imprime(emComum, "e");
	}
	// OPÇÃO 2
	public void cumplicesEmComum1() {
		Suspeito suspeito1 = buscaSuspeito();
		Suspeito suspeito2 = buscaSuspeito();

		ABB<String, Suspeito> emComum = new ABB<>();

		for (No<String, Suspeito> s : suspeito1.getCumplices()) {
			for (No<String, Suspeito> s2 : suspeito2.getCumplices()) {
				if (s.getChave().equals(s2.getChave())) {
					emComum.insercao(s.getChave(), s.getValor());
				}
			}
		}
		emComum.imprime(emComum, "e");
	}
	//
	public void alcanceSuspeito() {
		String nome  = JOptionPane.showInputDialog("Nome do suspeito:");
		No<String, Suspeito> no = suspeitos.buscaIterativa(nome);
		int dist = Integer.parseInt(JOptionPane.showInputDialog("Distância máxima:"));
		
		suspeitos.alcanceMax(no, dist);
	}

	// método de buscar um suspeito
	public Suspeito buscaSuspeito() {
		String nome = JOptionPane.showInputDialog("Nome do suspeito:");
		No<String, Suspeito> suspeito = suspeitos.buscaIterativa(nome);
		return suspeito.getValor();
	}

}
