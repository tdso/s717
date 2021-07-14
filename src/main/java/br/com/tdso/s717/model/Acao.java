package br.com.tdso.s717.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Acao extends Ativo{
	
	// vai usar o ID da classe pai
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoMedio;
	
	public Acao() {
		super("", "");
	}
		
	public Acao(Long id, String nome, String cod_negociacao, Integer quantidade, BigDecimal precoUnitario) {
		// colocar as validacoes no construtor
		super(id, nome, cod_negociacao);
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		calculaPrecoMedio();
	}
	
	public Acao(String nome, String cod_negociacao, Integer quantidade, BigDecimal precoUnitario) {
		// colocar as validacoes no construtor
		super(nome, cod_negociacao);
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		calculaPrecoMedio();
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoMedio() {
		return precoMedio;
	}
	
	
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	private void calculaPrecoMedio() {
		precoMedio = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
	}
	

}
