package br.com.tdso.s717.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;
@Entity
@Table
public class Negociacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="ativo_id")
	private Ativo ativo;
	private LocalDate dataNegociacao;
	private BigDecimal valorNegociacao;
	
	public Negociacao(Ativo ativo, LocalDate dataNegociacao, BigDecimal valorNegociacao) {
		this.ativo = ativo;
		this.dataNegociacao = dataNegociacao;
		this.valorNegociacao = valorNegociacao;
	}

	public Negociacao(Long id, Ativo ativo, LocalDate dataNegociacao, BigDecimal valorNegociacao) {
		this.id = id;
		this.ativo = ativo;
		this.dataNegociacao = dataNegociacao;
		this.valorNegociacao = valorNegociacao;
	}

	public Long getId() {
		return id;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public LocalDate getDataNegociacao() {
		return dataNegociacao;
	}

	public BigDecimal getValorNegociacao() {
		return valorNegociacao;
	}
	

}
