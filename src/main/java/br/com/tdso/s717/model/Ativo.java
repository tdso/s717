package br.com.tdso.s717.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Ativo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String codigoNegociacao;
	@OneToMany(mappedBy = "ativo")
	@JsonIgnore
	private List<Negociacao> negociacoes = new ArrayList<>();
	@OneToOne (cascade=CascadeType.ALL, mappedBy = "ativo")
	private Estoque estoque;

	public Ativo(Long id, String nome, String cod_negociacao) {
		// colocar as validacoes no construtor
		this.id = id;
		this.nome = nome;
		this.codigoNegociacao = cod_negociacao;
	}

	public Ativo(String nome, String cod_negociacao) {
		this.nome = nome;
		this.codigoNegociacao = cod_negociacao;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCodigoNegociacao() {
		return codigoNegociacao;
	}

	public List<Negociacao> getNegociacoes() {
		return Collections.unmodifiableList(negociacoes);
	}

	public Estoque getEstoque() {
		return estoque;
	}

	@Override
	public String toString() {
		return "Ativo [id=" + id + ", nome=" + nome + ", codigoNegociacao=" + codigoNegociacao + ", negociacoes="
				+ negociacoes + ", estoque=" + estoque + "]";
	}
	
	
	
}
