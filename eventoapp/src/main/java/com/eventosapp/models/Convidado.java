package com.eventosapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Convidado {
	
	@Id
	private String rg;
	
	@NotNull
	private String convidado;
	
	@ManyToOne
	private Evento evento;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getConvidado() {
		return convidado;
	}

	public void setConvidado(String convidado) {
		this.convidado = convidado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
}
