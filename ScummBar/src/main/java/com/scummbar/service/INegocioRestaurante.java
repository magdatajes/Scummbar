package com.scummbar.service;

import java.util.Date;
import java.util.List;

import com.scummbar.modelo.dto.ReservarDto;
import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;

public interface INegocioRestaurante {

	boolean cancelarReserva(Reserva reserva);

	boolean reservar(Restaurante restaurante, Reserva reserva);

	void addRestaurante(Restaurante restaurante);

	List<Restaurante> getRestaurantes();

	public void initialData();

	public Restaurante ponerNombreRestaurante(int id);

	List<Turno> getTurnos();

	Turno ponerTurno(int turnoId);
	
	List<Reserva> getReservas();

	Mesa asignarMesa(ReservarDto dto);
	
	public int getPlazasReservadas(Date fecha, Turno turno);
	
	public boolean editarReserva(Reserva reserva);
	
}