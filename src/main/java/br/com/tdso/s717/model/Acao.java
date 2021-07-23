package br.com.tdso.s717.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Acao extends Ativo{
	
	// vai usar o ID da classe pai
	private Integer quantidade;
	private BigDecimal precoMedio;
	
	public Acao() {
		super("", "");
	}
		
	public Acao(Long id, String nome, String cod_negociacao, Integer quantidade) {
		// colocar as validacoes no construtor
		super(id, nome, cod_negociacao);
		this.quantidade = quantidade;
	}
	
	public Acao(String nome, String cod_negociacao) {
		// colocar as validacoes no construtor
		super(nome, cod_negociacao);
		this.quantidade = 0;
		this.precoMedio = BigDecimal.ZERO;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoMedio() {
		return precoMedio;
	}

}
