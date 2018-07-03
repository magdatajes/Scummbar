package com.scummbar.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scummbar.modelo.entities.Turno;

@Service
@Transactional
public class TurnoDAOImpl implements TurnoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addRTurno(Turno turno) {
		getCurrentSession().save(turno);

	}

	public void updateTurno(Turno turno) {
		Turno turnoToUpdate = getTurno(turno.getId());
		turnoToUpdate.setDescripcion(turno.getDescripcion());
		getCurrentSession().update(turnoToUpdate);
	}

	public Turno getTurno(int id) {
		Turno turno = (Turno) getCurrentSession().get(Turno.class, id);
		return turno;
	}

	public void deleteTurno(int id) {
		Turno turno = getTurno(id);
		if (turno != null)
			getCurrentSession().delete(turno);

	}

	@SuppressWarnings("unchecked")
	public List<Turno> getTurno() {
		return getCurrentSession().createQuery("from Turno").list();
	}

}
