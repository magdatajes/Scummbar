package com.scummbar.dao;

import java.util.List;

import com.scummbar.modelo.entities.Turno;

public interface TurnoDAO {
	public void addRTurno(Turno turno);

	public void updateTurno(Turno turno);

	public Turno getTurno(int id);

	public void deleteTurno(int id);

	public List<Turno> getTurno();
}
