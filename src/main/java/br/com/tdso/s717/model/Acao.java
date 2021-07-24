package br.com.tdso.s717.model;

import javax.persistence.Entity;

@Entity
public class Acao extends Ativo{
	
	// vai usar o ID da classe pai
	
	public Acao() {
		super("", "");
	}
		
//	public Acao(Long id, String nome, String cod_negociacao) {
//		// colocar as validacoes no construtor
//		super(id, nome, cod_negociacao);
//	}
	
	public Acao(String nome, String cod_negociacao) {
		// colocar as validacoes no construtor
		super(nome, cod_negociacao);
	}
}
