package com.scummbar.dao;

import java.util.List;

import com.scummbar.modelo.entities.Mesa;

public interface MesaDAO {
	public void addMesa(Mesa mesa);

	public void updateMesa(Mesa mesa);

	public Mesa getMesa(int id);

	public void deleteMesa(int id);

	public List<Mesa> getMesas();
}
