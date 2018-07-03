package com.scummbar.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scummbar.dao.MesaDAO;
import com.scummbar.dao.ReservaDAO;
import com.scummbar.dao.RestauranteDAO;
import com.scummbar.dao.TurnoDAO;
import com.scummbar.modelo.dto.ReservarDto;
import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;
import com.scummbar.modelo.entities.horariosTurnos;

@Service
@Transactional
public class NegocioRestaurante implements INegocioRestaurante {

	@Autowired
	RestauranteDAO restauranteDAO;
	@Autowired
	ReservaDAO reservaDAO;
	@Autowired
	TurnoDAO turnoDAO;
	@Autowired
	MesaDAO mesaDAO;

//	public NegocioRestaurante(RestauranteDAO restauranteDAO, ReservaDAO reservaDAO) {
//		this.restauranteDAO = restauranteDAO;
//		this.reservaDAO = reservaDAO;
//	}

	public boolean editarReserva(Reserva reserva) {
		List <Reserva> reservas= reservaDAO.getReservas();
		int existe =0;
//		if (reserva.getMesa()==null) {
//			return false;
//		}
		for (Reserva reserva2 : reservas) {
			if (reserva2.getLocalizador()== reserva.getLocalizador()) {
				reserva.setId(reserva2.getId());
				existe =1;
			}
		}
		if (existe==0) {
			return false;
		}else {
			reservaDAO.updateReserva(reserva);
			return true;
		}
	}
	
	public boolean cancelarReserva(Reserva reserva) {
		if (reserva.getLocalizador() < 0) {
			return false;
		} else {
			int localizador = reserva.getLocalizador();
			reservaDAO.deleteReserva(localizador);
			return true;
		}
	}

	public boolean reservar(Restaurante restaurante, Reserva reserva) {

		Random rnd = new Random();
		int localizador = rnd.nextInt(2000);
		reserva.setLocalizador(localizador);

		if (reserva.getMesa() == null) {
			return false;
		} else {
			reservaDAO.addReserva(reserva);
			return true;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Mesa asignarMesa(ReservarDto dto) {
		Restaurante restauranteReserva = restauranteDAO.getRestaurante(dto.getRestauranteId());
		Turno turnoReserva = turnoDAO.getTurno(dto.getTurnoId());
		List<Mesa> mesasDelRestaurante = restauranteReserva.getMesas();

		Collections.sort(mesasDelRestaurante, new Comparator() {
			public int compare(Object m1, Object m2) {
				return new Integer(((Mesa) m1).getCapacidad()).compareTo(new Integer(((Mesa) m2).getCapacidad()));
			}
		});

		if (reservaDAO.getReservas().isEmpty()) {
			for (Mesa mesa : mesasDelRestaurante) {
				if (mesa.getCapacidad() >= dto.getPersonas()) {
					return mesa;
				}
			}
		} else {
			List<Mesa> mesasReservadas = reservaDAO.getMesasPorTurnoYRestaurante(turnoReserva, restauranteReserva);
			mesasDelRestaurante.removeAll(mesasReservadas);
			for (Mesa mesa : mesasDelRestaurante) {
				if (mesa.getCapacidad() >= dto.getPersonas()) {
					return mesa;
				}
			}
		}
		return null;
	}

	ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();

	public void addRestaurante(Restaurante restaurante) {
		restaurantes.add(restaurante);
	}

	public List<Restaurante> getRestaurantes() {
		return restauranteDAO.getRestaurante();
	}

	public List<Turno> getTurnos() {

		return turnoDAO.getTurno();
	}

	public void initialData() {
		Restaurante r1 = new Restaurante();
		Restaurante r2 = new Restaurante();

		r1.setNombre("Sushi");
		r1.setDireccion("calle alguna");
		r1.setDescripcion("japones");

		r2.setNombre("Mexicano");
		r2.setDireccion("calle ninguna");
		r2.setDescripcion("tacoss");

		r1.setId(restauranteDAO.addRestaurante(r1));
		r2.setId(restauranteDAO.addRestaurante(r2));

		Mesa m1 = new Mesa();
		Mesa m2 = new Mesa();
		Mesa m3 = new Mesa();
		Mesa m4 = new Mesa();
		Mesa m5 = new Mesa();

		m1.setCapacidad(4);
		m2.setCapacidad(2);
		m3.setCapacidad(4);
		m4.setCapacidad(6);
		m5.setCapacidad(3);

		m1.setRestaurante(r1);
		m2.setRestaurante(r1);
		m3.setRestaurante(r2);
		m4.setRestaurante(r2);
		m5.setRestaurante(r2);

		List<Mesa> mesasR1 = new ArrayList<Mesa>();
		List<Mesa> mesasR2 = new ArrayList<Mesa>();

		mesasR1.add(m1);
		mesasR1.add(m2);
		mesasR2.add(m3);
		mesasR2.add(m4);
		mesasR2.add(m5);

		mesaDAO.addMesa(m1);
		mesaDAO.addMesa(m2);
		mesaDAO.addMesa(m3);
		mesaDAO.addMesa(m4);
		mesaDAO.addMesa(m5);

		ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
		restaurantes.add(r1);
		restaurantes.add(r2);

		if (turnoDAO.getTurno().isEmpty()) {
			Turno turno1 = new Turno();
			turno1.setDescripcion(horariosTurnos.TURNO_13_15.toString());
			turnoDAO.addRTurno(turno1);

			Turno turno2 = new Turno();
			turno2.setDescripcion(horariosTurnos.TURNO_15_17.toString());
			turnoDAO.addRTurno(turno2);

			Turno turno3 = new Turno();
			turno3.setDescripcion(horariosTurnos.TURNO_20_22.toString());
			turnoDAO.addRTurno(turno3);

			Turno turno4 = new Turno();
			turno4.setDescripcion(horariosTurnos.TURNO_22_00.toString());
			turnoDAO.addRTurno(turno4);
		}
	}

	public Restaurante ponerNombreRestaurante(int id) {
		Restaurante restaurante = restauranteDAO.getRestaurante(id);
		return restaurante;

	}

	public Turno ponerTurno(int turnoId) {
		Turno turno = turnoDAO.getTurno(turnoId);
		return turno;
	}

	public int getPlazasReservadas(Date fecha, Turno turno) {
		int plazas = reservaDAO.sumaPlazas(fecha, turno);
		return plazas;
	}

	public List<Reserva> getReservas(){
		return reservaDAO.getReservas();
	}
	
	public Reserva getReserva(int localizador) {
		return reservaDAO.getReservaByLocalizador(localizador);
	}

	public boolean comprobarReservaExiste(int localizador) {
		List<Reserva> reservas= getReservas();
		for (Reserva reserva : reservas) {
			if (reserva.getLocalizador()==localizador){
				return true;
			}
		}
		return false;
	}
}
