package br.com.tdso.s717.model;


import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Selic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Blob texto;
	
	public Selic(Blob texto) {
		this.texto = texto;
	}
	
	public Selic(Long id, Blob texto) {
		this.id = id;
		this.texto = texto;
	}
	
	public Selic() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Blob getTexto() {
		return texto;
	}
	public void setTexto(Blob texto) {
		this.texto = texto;
	}
	
	

}
