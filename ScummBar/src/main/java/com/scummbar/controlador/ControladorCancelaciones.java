package com.scummbar.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scummbar.modelo.dto.CancelarDto;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.service.INegocioRestaurante;

@Controller
public class ControladorCancelaciones {

	@Autowired
	INegocioRestaurante negocioRestaurante;

	@RequestMapping(value = "/cancelar", method = RequestMethod.GET)
	public ModelAndView verFormulario() {
		ModelAndView model = new ModelAndView("cancelar");
		CancelarDto dto = new CancelarDto();
		
		dto.setRestaurantes(negocioRestaurante.getRestaurantes());
		dto.setTurnos(negocioRestaurante.getTurnos());
		model.addObject("command", dto);
		return model;
	}

	@RequestMapping(value = "/cancelar", method = RequestMethod.POST)
	public ModelAndView submitFormulario(CancelarDto dto) {
		ModelAndView model = new ModelAndView("cancelado");
		Reserva reserva = new Reserva();
		
		reserva.setDia(dto.getDia());
		reserva.setLocalizador(dto.getLocalizador());
	
		model.addObject("cancelado", negocioRestaurante.cancelarReserva(reserva));
		return model;
	}
}
