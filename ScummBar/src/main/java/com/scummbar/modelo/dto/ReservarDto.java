package com.scummbar.modelo.dto;

import java.util.Date;
import java.util.List;

import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;

public class ReservarDto {

	private List<Restaurante> restaurantes;
	private List<Turno> turnos;
	private int restauranteId;
	private Date dia;
	private int turnoId;
	private Integer personas;
	private Mesa mesa;

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public int getRestauranteId() {
		return restauranteId;
	}

	public void setRestauranteId(int restauranteId) {
		this.restauranteId = restauranteId;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public int getTurnoId() {
		return turnoId;
	}

	public void setTurnoId(int turnoId) {
		this.turnoId = turnoId;
	}

	public Integer getPersonas() {
		return personas;
	}

	public void setPersonas(Integer personas) {
		this.personas = personas;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}
