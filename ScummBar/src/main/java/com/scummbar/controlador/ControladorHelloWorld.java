package com.scummbar.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scummbar.service.INegocioRestaurante;

@Controller
public class ControladorHelloWorld {

	@Autowired
	INegocioRestaurante negocioRestaurante;

	@RequestMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "helloworld";
	}

	@RequestMapping("/menu")
	public String menu(Model model) {
		model.addAttribute("homeUrl", "menu");
		model.addAttribute("bookingUrl", "reservar");
		model.addAttribute("cancelUrl", "cancelar");
		model.addAttribute("mostrarUrl", "mostrar");
		model.addAttribute("editarUrl", "editar");
		return "menu";
	}

}
