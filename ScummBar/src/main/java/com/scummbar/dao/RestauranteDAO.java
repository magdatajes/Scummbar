package com.scummbar.dao;

import java.util.List;
import com.scummbar.modelo.entities.Restaurante;

public interface RestauranteDAO {
	public int addRestaurante(Restaurante resto);

	public void updateRestaurante(Restaurante resto);

	public Restaurante getRestaurante(int id);

	public void deleteRestaurante(int id);

	public List<Restaurante> getRestaurantes();
}
