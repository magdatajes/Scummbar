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
public class ControladorEditar {

	@Autowired
	INegocioRestaurante negocioRestaurante;

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView verFormulario() {
		ModelAndView model = new ModelAndView("editar");
		EditarDto dto = new EditarDto();
		model.addObject("command", dto);
		return model;
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ModelAndView submitFormulario(EditarDto dto) {
		ModelAndView model = new ModelAndView("editando");
		Reserva reserva= new Reserva();
		reserva.setLocalizador(dto.getLocalizador());
		if(negocioRestaurante.comprobarReservaExiste(dto.getLocalizador())) {
			model.addObject("editando", dto);
			return model;
		}else {
			return null;
		}
	}

}
