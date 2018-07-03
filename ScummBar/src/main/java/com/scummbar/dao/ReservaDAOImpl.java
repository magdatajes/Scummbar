package com.scummbar.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;

@Service
@Transactional
public class ReservaDAOImpl implements ReservaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addReserva(Reserva reserva) {
		getCurrentSession().save(reserva);
	}

	public void updateReserva(Reserva reserva) {
		Reserva reservaToUpdate = getReserva(reserva.getId());
		reservaToUpdate.setLocalizador(reserva.getLocalizador());
		reservaToUpdate.setPersonas(reserva.getPersonas());
		reservaToUpdate.setDia(reserva.getDia());
		reservaToUpdate.setRestaurante(reserva.getRestaurante());

		getCurrentSession().update(reservaToUpdate);

	}

	public void deleteReserva(int localizador) {
		List<Reserva> reservas = getReservas();
		for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {
			Reserva reserva = (Reserva) iterator.next();
			if (reserva.getLocalizador() == localizador)
				getCurrentSession().createQuery("delete Reserva where localizador = '" + localizador + "'").executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservas() {
		return getCurrentSession().createQuery("from Reserva").list();
	}

	public Reserva getReserva(int id) {
		Reserva reserva = (Reserva) getCurrentSession().get(Reserva.class, id);
		return reserva;
	}

	@SuppressWarnings("unchecked")
	public List<Mesa> getMesasPorTurnoYRestaurante(Turno turno, Restaurante restaurante) {
		return getCurrentSession().createQuery("select r.mesa from Reserva r where r.turno.descripcion= '" + turno.getDescripcion()
				+ "' and r.restaurante.nombre= '" + restaurante.getNombre() + "'").list();
	}
	
	public int sumaPlazas(Date fecha, Turno turno) {
		return getCurrentSession().createQuery("select sum(r.personas) from Reserva r where r.turno.descripcion= '" + turno.getDescripcion()
				+ "' and r.dia= '" + fecha + "'").executeUpdate();
	}
	
	
}
