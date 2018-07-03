package com.scummbar.controlador;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scummbar.modelo.entities.Reserva;
import com.scummbar.service.INegocioRestaurante;

@Controller
public class ControladorMuestraReservas {
	
	@Autowired
	INegocioRestaurante negocioRestaurante;
	
	@RequestMapping(value = "/mostrar", method = RequestMethod.GET)
	public ModelAndView verReservas() {
		ModelAndView model = new ModelAndView("mostrar");
		Collection<Reserva> listaReservas = negocioRestaurante.getReservas();
		model.addObject("listaReservas", listaReservas);
		return model;
	}

}
