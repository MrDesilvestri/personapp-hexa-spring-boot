package co.edu.javeriana.as.personapp.terminal.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.terminal.adapter.PersonaInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonaMenu {

	private static final int OPCION_REGRESAR_MODULOS = 0;
	private static final int PERSISTENCIA_MARIADB = 1;
	private static final int PERSISTENCIA_MONGODB = 2;

	private static final int OPCION_REGRESAR_MOTOR_PERSISTENCIA = 0;
	private static final int OPCION_VER_TODO = 1;
	private static final int OPCION_CREAR = 2;
	private static final int OPCION_ELIMINAR = 3;
	private static final int OPCION_ACTUALIZAR = 4;
	private static final int OPCION_OBTENER_PERSONA = 5;
	// mas opciones

	public void iniciarMenu(PersonaInputAdapterCli personaInputAdapterCli, Scanner keyboard) throws NoExistException {
		boolean isValid = false;
		do {
			try {
				mostrarMenuMotorPersistencia();
				int opcion = leerOpcion(keyboard);
				switch (opcion) {
				case OPCION_REGRESAR_MODULOS:
					isValid = true;
					break;
				case PERSISTENCIA_MARIADB:
					personaInputAdapterCli.setPersonOutputPortInjection("MARIA");
					menuOpciones(personaInputAdapterCli,keyboard);
					break;
				case PERSISTENCIA_MONGODB:
					personaInputAdapterCli.setPersonOutputPortInjection("MONGO");
					menuOpciones(personaInputAdapterCli,keyboard);
					break;
				default:
					log.warn("La opción elegida no es válida.");
				}
			}  catch (InvalidOptionException e) {
				log.warn(e.getMessage());
			}
		} while (!isValid);
	}

	private void menuOpciones(PersonaInputAdapterCli personaInputAdapterCli, Scanner keyboard) throws NoExistException {
		PersonaModelCli persona = new PersonaModelCli();
		boolean isValid = false;
		do {
			try {
				mostrarMenuOpciones();
				int opcion = leerOpcion(keyboard);
				switch (opcion) {
				case OPCION_REGRESAR_MOTOR_PERSISTENCIA:
					isValid = true;
					break;
				case OPCION_VER_TODO:
					personaInputAdapterCli.historial();					
					break;
				case OPCION_CREAR:
					System.out.print("Ingrese el número de cédula: ");
					persona.setCc(keyboard.nextInt());
					System.out.print("Ingrese el nombre: ");
					persona.setNombre(keyboard.next());
					System.out.print("Ingrese el apellido: ");
					persona.setApellido(keyboard.next());
					System.out.print("Ingrese el género, valores aceptados MALE, FEMALE, OTHER: ");
					persona.setGenero(keyboard.next());
					System.out.print("Ingrese la edad: ");
					persona.setEdad(keyboard.nextInt());

					System.out.println(persona.toString());

					personaInputAdapterCli.crearPersona(persona);
					break;
				case OPCION_ELIMINAR:
					System.out.print("Ingrese el número de cédula: ");
					personaInputAdapterCli.eliminarPersona(keyboard.nextInt());
					break;	
				case OPCION_ACTUALIZAR:
					System.out.print("Ingrese el número de cédula de la pesona que desea actualizar la informacion: ");
					persona.setCc(keyboard.nextInt());
					System.out.print("Ingrese el nombre: ");
					persona.setNombre(keyboard.next());
					System.out.print("Ingrese el apellido: ");
					persona.setApellido(keyboard.next());
					System.out.print("Ingrese el género, valores aceptados MALE, FEMALE, OTHER: ");
					persona.setGenero(keyboard.next());
					System.out.print("Ingrese la edad: ");
					persona.setEdad(keyboard.nextInt());

					personaInputAdapterCli.actualizarPersona(persona.getCc(), persona);
					break;
				case OPCION_OBTENER_PERSONA:
					System.out.print("Ingrese el número de cédula: ");
					try{
						persona.setCc(keyboard.nextInt());
					}catch (Exception e) {
						log.warn("Solo se permiten números.");
					}
					personaInputAdapterCli.buscarPersonaPorCC(persona.getCc());
					break;
				default:
					log.warn("La opción elegida no es válida.");
				}
			} catch (InputMismatchException e) {
				log.warn("Solo se permiten números.");
			}
		} while (!isValid);
	}

	private void mostrarMenuOpciones() {
		System.out.println("----------------------");
		System.out.println(OPCION_VER_TODO + " para ver todas las personas");
		System.out.println(OPCION_CREAR + " para crear persona");
		System.out.println(OPCION_ELIMINAR + " para eliminar persona");
		System.out.println(OPCION_ACTUALIZAR + " para actualizar persona");
		System.out.println(OPCION_OBTENER_PERSONA + " para obtener una persona");
		// implementar otras opciones
		System.out.println(OPCION_REGRESAR_MOTOR_PERSISTENCIA + " para regresar");
	}

	private void mostrarMenuMotorPersistencia() {
		System.out.println("----------------------");
		System.out.println(PERSISTENCIA_MARIADB + " para MariaDB");
		System.out.println(PERSISTENCIA_MONGODB + " para MongoDB");
		System.out.println(OPCION_REGRESAR_MODULOS + " para regresar");
	}

	private int leerOpcion(Scanner keyboard) {
		try {
			System.out.print("Ingrese una opción: ");
			return keyboard.nextInt();
		} catch (InputMismatchException e) {
			log.warn("Solo se permiten números.");
			return leerOpcion(keyboard);
		}
	}

}
