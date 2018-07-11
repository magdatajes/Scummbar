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
import com.scummbar.dao.TurnoDAO;
import com.scummbar.modelo.dto.ReservarDto;
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
	private Turno turno;
	
	@Mock
	private ReservaDAO reservaDAO = Mockito.mock(ReservaDAO.class);
	
	@Mock
	private RestauranteDAO restauranteDAO = Mockito.mock(RestauranteDAO.class);

	@Mock
	private MesaDAO mesaDAO;
	
	@Mock
	private TurnoDAO turnoDAO= Mockito.mock(TurnoDAO.class);

	private NegocioRestaurante negocioRestaurante = new NegocioRestaurante(restauranteDAO, reservaDAO, mesaDAO, turnoDAO);

	@Before
	public void initialize() {

		sdf = new SimpleDateFormat("ddMMyyyy");
		restaurante = new Restaurante();
		turno= new Turno();
		turno.setId(0);
		Mesa mesa1 = new Mesa();
		Mesa mesa2 = new Mesa();
		mesa1.setCapacidad(4);
		mesa2.setCapacidad(4);
		List<Mesa> mesas = new ArrayList<Mesa>();
		mesas.add(mesa1);
		mesas.add(mesa2);
		restaurante.setMesas(mesas);
		negocioRestaurante.addRestaurante(restaurante);
	}

	@Test
	public void comprobarDisponibilidad() throws ParseException {
		Date fechaReserva = sdf.parse("01052014");
		Turno turno = new Turno();
		turno.setDescripcion(horariosTurnos.TURNO_20_22.toString());
		Mockito.when(reservaDAO.sumaPlazas(fechaReserva, turno)).thenReturn(0);
		Assert.assertSame(0, negocioRestaurante.getPlazasReservadas(fechaReserva, turno));
		Reserva reserva = new Reserva();
		reserva.setDia(fechaReserva);
		Turno turno1 = new Turno();
		turno1.setDescripcion(horariosTurnos.TURNO_20_22.toString());
		reserva.setTurno(turno1);
		reserva.setPersonas(6);
		reserva.setLocalizador(001);
		Mesa mesa = new Mesa();
		mesa.setId(1);
		mesa.setCapacidad(8);
		reserva.setMesa(mesa);
		Assert.assertTrue(negocioRestaurante.reservar(restaurante, reserva));
		Mockito.verify(reservaDAO).addReserva(reserva);
		Mockito.when(reservaDAO.sumaPlazas(fechaReserva, turno1)).thenReturn(6);
		Assert.assertSame(6, negocioRestaurante.getPlazasReservadas(fechaReserva, turno1));
	}

	@Test
	public void reservar() {
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
		reserva.setRestaurante(restaurante);
		Assert.assertTrue(negocioRestaurante.reservar(restaurante, reserva));
		Mockito.when(reservaDAO.deleteReserva(reserva)).thenReturn(true);
		Assert.assertTrue(negocioRestaurante.cancelarReserva(reserva));
	}

	@Test
	public void asignacionDeMesasSinReservas() { 
		ReservarDto dto = new ReservarDto();
		dto.setPersonas(10);
		dto.setRestauranteId(0);
		dto.setTurnoId(0);
		Mockito.when(restauranteDAO.getRestaurante(0)).thenReturn(restaurante);
		Mockito.when(turnoDAO.getTurno(0)).thenReturn(turno);
		Assert.assertNull(negocioRestaurante.asignarMesa(dto));
		dto.setPersonas(4);
		Assert.assertNotNull(negocioRestaurante.asignarMesa(dto));
	}
	
	@Test
	public void asignacionMesasConReservasHechas() {
		Reserva reserva=new Reserva();
		Mesa mesa1 = new Mesa();
		reserva.setMesa(mesa1);
		negocioRestaurante.reservar(restaurante, reserva);
		Mockito.verify(reservaDAO).addReserva(reserva);
		
		ReservarDto dto= new ReservarDto();
		dto.setPersonas(2);
		dto.setRestauranteId(0);
		dto.setTurnoId(0);
		Mockito.when(restauranteDAO.getRestaurante(0)).thenReturn(restaurante);
		Mockito.when(turnoDAO.getTurno(0)).thenReturn(turno);
		
		List<Reserva> reservas= new ArrayList<Reserva>();
		reservas.add(reserva);
		Mockito.when(reservaDAO.getReservas()).thenReturn(reservas);
		
		Assert.assertNotNull(negocioRestaurante.asignarMesa(dto));
	}
}