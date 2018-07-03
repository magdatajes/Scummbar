package com.scummbar.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scummbar.modelo.entities.Restaurante;

@Service
@Transactional
public class RestauranteDAOImpl implements RestauranteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public int addRestaurante(Restaurante resto) {
		return (Integer) getCurrentSession().save(resto);

	}

	public void updateRestaurante(Restaurante resto) {
		Restaurante restoToUpdate = getRestaurante(resto.getId());
		restoToUpdate.setNombre(resto.getNombre());
		restoToUpdate.setDireccion(resto.getDireccion());
		restoToUpdate.setDescripcion(resto.getDescripcion());
		restoToUpdate.setMesas(resto.getMesas());
		restoToUpdate.setReservas(resto.getReservas());

		getCurrentSession().update(restoToUpdate);
	}

	public Restaurante getRestaurante(int id) {
		Restaurante resto = (Restaurante) getCurrentSession().get(Restaurante.class, id);
		return resto;
	}

	public void deleteRestaurante(int id) {
		Restaurante resto = getRestaurante(id);
		if (resto != null)
			getCurrentSession().delete(resto);

	}

	@SuppressWarnings("unchecked")
	public List<Restaurante> getRestaurante() {
		return getCurrentSession().createQuery("from Restaurante").list();
	}

}
