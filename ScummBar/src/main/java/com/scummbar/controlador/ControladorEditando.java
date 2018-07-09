package com.scummbar.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scummbar.modelo.dto.EditarDto;
import com.scummbar.modelo.entities.Reserva;
import com.scummbar.service.INegocioRestaurante;

@Controller
public class ControladorEditando {

	@Autowired
	INegocioRestaurante negocioRestaurante;


	@RequestMapping(value = "/editando", method = RequestMethod.GET)
	public ModelAndView verFormulario(EditarDto dto) {
		ModelAndView model = new ModelAndView("editando");
		dto.setTurnos(negocioRestaurante.getTurnos());
		model.addObject("command", dto);
		return model;
	}
	
	
	@RequestMapping(value = "/editando", method = RequestMethod.POST)
	public ModelAndView submitFormulario(EditarDto dto) {
		ModelAndView model = new ModelAndView("editando");
		Reserva reserva = new Reserva();
		reserva.setLocalizador(dto.getLocalizador());
		reserva.setPersonas(dto.getPersonas());
		reserva.setDia(dto.getDia());
		reserva.setTurno(negocioRestaurante.ponerTurno(dto.getTurnoId()));
		model.addObject("editado", negocioRestaurante.editarReserva(reserva));
		return model;
	}
}
