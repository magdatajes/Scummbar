package com.scummbar.modelo.dto;

import java.util.Date;
import java.util.List;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;

public class CancelarDto {

	private List<Restaurante> restaurantes;
	private List<Turno> turnos;
	private int restauranteId;
	private Date dia;
	private int turnoId;
	private int localizador;

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

	public int getLocalizador() {
		return localizador;
	}

	public void setLocalizador(int localizador) {
		this.localizador = localizador;
	}
}
