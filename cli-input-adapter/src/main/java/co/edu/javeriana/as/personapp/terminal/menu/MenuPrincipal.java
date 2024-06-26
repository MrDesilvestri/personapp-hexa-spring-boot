package co.edu.javeriana.as.personapp.terminal.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.terminal.adapter.PersonaInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.adapter.TelefonoInputAdapterCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MenuPrincipal {

	private final PersonaInputAdapterCli personaInputAdapterCli;
	private final TelefonoInputAdapterCli telefonoInputAdapterCli;
	private final TelefonoMenu telefonoMenu;
	private final PersonaMenu personaMenu;
	private final Scanner keyboard;

	@Autowired
	public MenuPrincipal(@Lazy TelefonoInputAdapterCli telefonoInputAdapterCli, PersonaInputAdapterCli personaInputAdapterCli) {
		this.telefonoInputAdapterCli = telefonoInputAdapterCli;
		this.personaInputAdapterCli = personaInputAdapterCli;
		this.telefonoMenu = new TelefonoMenu();
		this.personaMenu = new PersonaMenu();
		this.keyboard = new Scanner(System.in);
	}

	private static final int SALIR = 0;
	private static final int MODULO_PERSONA = 1;
	private static final int MODULO_PROFESION = 2;
	private static final int MODULO_TELEFONO = 3;
	private static final int MODULO_ESTUDIO = 4;

	public void inicio() throws NoExistException {
		boolean isValid = false;
		do {
			mostrarMenu();
			int opcion = leerOpcion();
			switch (opcion) {
				case SALIR:
					isValid = true;
					break;
				case MODULO_PERSONA:
					personaMenu.iniciarMenu(personaInputAdapterCli, keyboard);
					log.info("volvio");
					break;
				case MODULO_PROFESION:
					log.warn("Implementar Menu");
					break;
				case MODULO_TELEFONO:
					log.warn("Implementar Menu");
					telefonoMenu.iniciarMenu(personaInputAdapterCli, telefonoInputAdapterCli, keyboard);
					break;
				case MODULO_ESTUDIO:
					log.warn("Implementar Menu");
					break;
				default:
					log.warn("La opción elegida no es válida.");
			}
		} while (!isValid);
		keyboard.close();
	}

	private void mostrarMenu() {
		System.out.println("----------------------");
		System.out.println(MODULO_PERSONA + " para trabajar con el Modulo de Personas");
		System.out.println(MODULO_PROFESION + " para trabajar con el Modulo de Profesiones");
		System.out.println(MODULO_TELEFONO + " para trabajar con el Modulo de Telefonos");
		System.out.println(MODULO_ESTUDIO + " para trabajar con el Modulo de Estudios");
		System.out.println(SALIR + " para Salir");
	}

	private int leerOpcion() {
		try {
			System.out.print("Ingrese una opción: ");
			return keyboard.nextInt();
		} catch (InputMismatchException e) {
			log.warn("Solo se permiten números.");
			keyboard.next(); // clear the invalid input
			return leerOpcion();
		}
	}
}
