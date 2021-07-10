package br.com.tdso.s717.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Estoque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name="ativo_id") // nomeando a coluna de join
	@MapsId  // o identificador da entidade sera o mapeado pelo OnoToOne
	private Ativo ativo;
	private Integer quantidade;
	private BigDecimal precoMedio;
	
	public Estoque(Ativo ativo, Integer quantidade, BigDecimal precoMedio) {
		this.ativo = ativo;
		this.quantidade = quantidade;
		this.precoMedio = precoMedio;
	}

	public Long getId() {
		return id;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoMedio() {
		return precoMedio;
	}
	

}
