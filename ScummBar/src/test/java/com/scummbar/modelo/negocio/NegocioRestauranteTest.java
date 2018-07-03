package com.scummbar.modelo.negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.scummbar.dao.MesaDAO;
import com.scummbar.dao.ReservaDAO;
import com.scummbar.dao.RestauranteDAO;
import com.scummbar.modelo.entities.Mesa;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.modelo.entities.Turno;
import com.scummbar.modelo.entities.horariosTurnos;
import com.scummbar.service.NegocioRestaurante;

public class NegocioRestauranteTest {

	private SimpleDateFormat sdf;
	
	@Mock
	private Restaurante restaurante;
	@Mock
	private ReservaDAO reservaDAO = Mockito.mock(ReservaDAO.class);
	@Mock
	private RestauranteDAO restauranteDAO;
	
	@Mock
	private MesaDAO mesaDAO;
	
	private NegocioRestaurante negocioRestaurante= new NegocioRestaurante(restauranteDAO, reservaDAO);
	
	@Before
	public void initialize() {

		sdf = new SimpleDateFormat("ddMMyyyy");
		restaurante = new Restaurante();
		Mesa mesa1 = new Mesa();
		Mesa mesa2 = new Mesa();
		mesa1.setCapacidad(4);
		mesa2.setCapacidad(4);
		List<Mesa> mesas= new ArrayList<Mesa>();
		mesas.add(mesa1);
		mesas.add(mesa2);
		restaurante.setMesas(mesas);
		negocioRestaurante.addRestaurante(restaurante);
	}

	@Test
	public void comprobarDisponibilidad() throws ParseException {
		Date fechaReserva = sdf.parse("01052014");
		Turno turno=new Turno();
		turno.setDescripcion(horariosTurnos.TURNO_20_22.toString());
		Mockito.when(reservaDAO.sumaPlazas(fechaReserva,turno)).thenReturn(0);
		Assert.assertSame(0, negocioRestaurante.getPlazasReservadas(fechaReserva, turno));
		Reserva reserva = new Reserva();
		reserva.setDia(fechaReserva);
		Turno turno1= new Turno();
		turno1.setDescripcion(horariosTurnos.TURNO_20_22.toString());
		reserva.setTurno(turno1);
		reserva.setPersonas(6);
		reserva.setLocalizador(001);
		Mesa mesa= new Mesa();
		mesa.setId(1);
		mesa.setCapacidad(8);
		reserva.setMesa(mesa);
		Assert.assertTrue(negocioRestaurante.reservar(restaurante, reserva));
		Mockito.verify(reservaDAO).addReserva(reserva);
		Mockito.when(reservaDAO.sumaPlazas(fechaReserva,turno1)).thenReturn(6);
		Assert.assertSame(6, negocioRestaurante.getPlazasReservadas(fechaReserva, turno1));	
	}

	@Test
	public void reservar(){
		Reserva reserva = new Reserva();
		reserva.setMesa(new Mesa());
		boolean response = negocioRestaurante.reservar(restaurante, reserva);
		Assert.assertTrue(response);
	}

	@Test
	public void cancelar() throws ParseException {
		
		Reserva reserva = new Reserva();
		reserva.setLocalizador(001);
		reserva.setMesa(new Mesa());
		Assert.assertTrue(negocioRestaurante.reservar(restaurante, reserva));
		Assert.assertTrue(negocioRestaurante.cancelarReserva(reserva));
	}
}