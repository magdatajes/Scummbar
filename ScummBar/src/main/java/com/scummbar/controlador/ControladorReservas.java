package com.scummbar.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scummbar.modelo.dto.ReservarDto;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.modelo.entities.Restaurante;
import com.scummbar.service.INegocioRestaurante;

@Controller
public class ControladorReservas {

	@Autowired
	INegocioRestaurante negocioRestaurante;

	@RequestMapping(value = "/reservar", method = RequestMethod.GET)
	public ModelAndView verFormulario() {
		ModelAndView model = new ModelAndView("reservar");
		ReservarDto dto = new ReservarDto();
		dto.setRestaurantes(negocioRestaurante.getRestaurantes());
		dto.setTurnos(negocioRestaurante.getTurnos());
		model.addObject("command", dto);
		return model;
	}

	@RequestMapping(value = "/reservar", method = RequestMethod.POST)
	public ModelAndView submitFormulario(ReservarDto dto) {
		ModelAndView model = new ModelAndView("reservado");

		Reserva reserva = new Reserva();
		Restaurante restaurante = new Restaurante();

		reserva.setDia(dto.getDia());
		reserva.setPersonas(dto.getPersonas());
		reserva.setRestaurante(negocioRestaurante.ponerNombreRestaurante(dto.getRestauranteId()));
		reserva.setTurno(negocioRestaurante.ponerTurno(dto.getTurnoId()));
		reserva.setMesa(negocioRestaurante.asignarMesa(dto));
		model.addObject("localizador", negocioRestaurante.reservar(restaurante, reserva));

		return model;
	}
}
