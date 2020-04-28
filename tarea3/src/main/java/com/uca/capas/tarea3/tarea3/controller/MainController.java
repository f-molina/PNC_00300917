package com.uca.capas.tarea3.tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/ingresar")
	public String ingresar() {
		return "ingresar";
	}

	@RequestMapping("/validate")
	public ModelAndView validate(@RequestParam String names, @RequestParam String lastNames,
			@RequestParam String dateOfBirth, @RequestParam String birthPlace, @RequestParam String school,
			@RequestParam String telephone, @RequestParam String cellphone) throws ParseException {
		ModelAndView mav = new ModelAndView();
		List<String> list = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

		if (names.length() > 25) {
			list.add("Campo nombres, maximo 25 caracteres");
		}

		if (names.length() < 1) {
			list.add("Campo nombres, minimo 1 caracter");
		}

		if (lastNames.length() > 25) {
			list.add("Campo apellidos, maximo 25 caracteres");
		}

		if (lastNames.length() < 1) {
			list.add("Campo apellidos minimo 1 caracter");
		}

		if (!dateOfBirth.isEmpty()) {
			Date d1 = format.parse(dateOfBirth);
			Date d2 = format.parse("01-01-2003");

			if (d1.compareTo(d2) > 0) {
				list.add("La fecha de nacimiento no puede ser menor a 01-01-2003");
			}

		} else {
			list.add("Este campo no puede estar vacio");
		}

		if (birthPlace.length() > 25) {
			list.add("Campo lugar de nacimiento, maximo 25 caracteres");
		}

		if (birthPlace.length() < 1) {
			list.add("Campo lugar de nacimiento, minimo 1 caracter");
		}

		if (school.length() > 100) {
			list.add("Campo institucion, maximo 100 caracteres");
		}

		if (school.length() < 1) {
			list.add("Campo institucion, minimo 1 caracter");
		}

		if (!(telephone.length() == 8)) {
			list.add("Telefono fijo deber ser exactamente 8 digitos");
		}

		if (!(cellphone.length() == 8)) {
			list.add("Celular debe ser exactamente 8 digitos");
		}

		mav.addObject("errores", list);
		mav.setViewName("validate");
		return mav;
	}
}
