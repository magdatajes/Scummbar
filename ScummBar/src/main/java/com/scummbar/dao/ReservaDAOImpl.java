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
		Reserva reservaToUpdate = new Reserva();
		reservaToUpdate = getReservaByLocalizador(reserva.getLocalizador());
		reservaToUpdate.setLocalizador(reserva.getLocalizador());
		// reservaToUpdate.setRestaurante(reserva.getRestaurante());
		// reservaToUpdate.setMesa(reserva.getMesa());
		reservaToUpdate.setTurno(reserva.getTurno());
		reservaToUpdate.setPersonas(reserva.getPersonas());
		reservaToUpdate.setDia(reserva.getDia());
		reservaToUpdate.setRestaurante(reserva.getRestaurante());

		getCurrentSession().update(reservaToUpdate);

	}

	@SuppressWarnings("unused")
	public boolean deleteReserva(Reserva reservaACancelar) {
		List<Reserva> reservas = getReservas();

		for (Iterator<Reserva> iterator = reservas.iterator(); iterator.hasNext();) {
			Reserva reserva = (Reserva) iterator.next();

			if (reserva.getLocalizador() == reservaACancelar.getLocalizador()
					&& reserva.getRestaurante().getId() == reservaACancelar.getRestaurante().getId()) {
				String hql = "delete Reserva where localizador = :Localizador ";
				int respuesta = getCurrentSession().createQuery(hql)
						.setParameter("Localizador", reservaACancelar.getLocalizador()).executeUpdate();
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> getReservas() {
		String hql="from Reserva";
		return getCurrentSession().createQuery(hql).list();
	}

	public Reserva getReserva(int id) {
		Reserva reserva = (Reserva) getCurrentSession().get(Reserva.class, id);
		return reserva;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Mesa> getMesasPorTurnoYRestaurante(Turno turno, Restaurante restaurante) {
		String hql = "select r.mesa from Reserva r where r.turno.descripcion= :turnoDescripcion and r.restaurante.nombre= :restauranteNombre";
		List respuesta = getCurrentSession().createQuery(hql).setParameter("turnoDescripcion", turno.getDescripcion())
				.setParameter("restauranteNombre", restaurante.getNombre()).list();
		return respuesta;
	}

	public int sumaPlazas(Date fecha, Turno turno) {
		String hql = "select sum(r.personas) from Reserva r where r.turno.descripcion= :turnoDescripcion and r.dia= :fecha";
		int respuesta = getCurrentSession().createQuery(hql).setParameter("turnoDescripcion", turno.getDescripcion())
				.setParameter("fecha", fecha).executeUpdate();
		return respuesta;
	}

	public Reserva getReservaByLocalizador(int localizador) {
		List<Reserva> reservas = getReservas();
		for (Reserva reserva : reservas) {
			if (reserva.getLocalizador() == localizador) {
				return reserva;
			}
		}
		return null;
	}

}
