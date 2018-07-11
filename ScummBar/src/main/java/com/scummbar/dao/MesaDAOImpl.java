package com.scummbar.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scummbar.modelo.entities.Mesa;

@Repository
public class MesaDAOImpl implements MesaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addMesa(Mesa mesa) {
		getCurrentSession().save(mesa);
	}

	public void updateMesa(Mesa mesa) {
		Mesa mesaToUpdate = getMesa(mesa.getId());
		mesaToUpdate.setNumero(mesa.getNumero());
		mesaToUpdate.setCapacidad(mesa.getCapacidad());
		getCurrentSession().update(mesaToUpdate);
	}

	public Mesa getMesa(int id) {
		Mesa mesa = (Mesa) getCurrentSession().get(Mesa.class, id);
		return mesa;

	}

	public void deleteMesa(int id) {
		Mesa mesa = getMesa(id);
		if (mesa != null)
			getCurrentSession().delete(mesa);
	}

	@SuppressWarnings("unchecked")
	public List<Mesa> getMesas() {
		String hql= "from Mesa";
		return getCurrentSession().createQuery(hql).list();

	}

}
