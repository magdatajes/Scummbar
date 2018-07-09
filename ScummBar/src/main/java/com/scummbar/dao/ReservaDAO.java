package com.scummbar.dao;

import java.util.Date;
import java.util.List;

import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;

public interface ReservaDAO {
	public void addReserva(Reserva reserva);

	public void updateReserva(Reserva reserva);

	public Reserva getReserva(int id);

	public boolean deleteReserva(Reserva reserva);

	public List<Reserva> getReservas();

	public List<Mesa> getMesasPorTurnoYRestaurante(Turno turno, Restaurante restaurante);
	
	public int sumaPlazas(Date fecha, Turno turno);
	
	public Reserva getReservaByLocalizador(int localizador);
}
