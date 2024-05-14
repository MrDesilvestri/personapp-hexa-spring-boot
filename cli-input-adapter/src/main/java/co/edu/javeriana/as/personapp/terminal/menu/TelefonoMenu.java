package co.edu.javeriana.as.personapp.terminal.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.terminal.adapter.PersonaInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.adapter.TelefonoInputAdapterCli;
import co.edu.javeriana.as.personapp.terminal.mapper.PersonaMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TelefonoMenu {
    private static final int OPCION_REGRESAR_MODULOS = 0;
	private static final int PERSISTENCIA_MARIADB = 1;
	private static final int PERSISTENCIA_MONGODB = 2;

	private static final int OPCION_REGRESAR_MOTOR_PERSISTENCIA = 0;
	private static final int OPCION_VER_TELEFONO = 1;
	private static final int OPCION_CREAR = 2;
	private static final int OPCION_ELIMINAR = 3;
	private static final int OPCION_ACTUALIZAR = 4;
	// mas opciones

	public void iniciarMenu(PersonaInputAdapterCli personaInputAdapterCli ,TelefonoInputAdapterCli telefonoInputAdapterCli, Scanner keyboard) throws NoExistException {
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
					telefonoInputAdapterCli.setPhoneOutputPortInjection("MARIA");
					menuOpciones(personaInputAdapterCli,telefonoInputAdapterCli,keyboard);
					break;
				case PERSISTENCIA_MONGODB:
					telefonoInputAdapterCli.setPhoneOutputPortInjection("MONGO");
					menuOpciones(personaInputAdapterCli,telefonoInputAdapterCli,keyboard);
					break;
				default:
					log.warn("La opción elegida no es válida.");
				}
			}  catch (InvalidOptionException e) {
				log.warn(e.getMessage());
			}
		} while (!isValid);
	}

	private void menuOpciones(PersonaInputAdapterCli personaInputAdapterCli ,TelefonoInputAdapterCli telefonoInputAdapterCli, Scanner keyboard) throws NoExistException {
		TelefonoModelCli telefono = new TelefonoModelCli();
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
				case OPCION_VER_TELEFONO:
                    System.out.print("Ingrese el número de telefono: ");
                    System.out.println(telefonoInputAdapterCli.obtenerNumero(keyboard.nextInt()));			
					break;
				case OPCION_CREAR:
					System.out.print("Ingrese el número de telefono: ");
					telefono.setNum(keyboard.next());
                    System.out.print("Ingrese el operador: ");
                    telefono.setOperador(keyboard.next());
                    System.out.print("Ingrese la cedula del dueño al que pertenece el telefono: ");
                    try{
						persona.setCc(keyboard.nextInt());
					}catch (Exception e) {
						log.warn("Solo se permiten números.");
					}
	
					
					telefonoInputAdapterCli.crearTelefono(telefono, personaInputAdapterCli.buscarPersonaPorCC(persona.getCc()));
                    
					break;
				case OPCION_ELIMINAR:
					
					break;	
				case OPCION_ACTUALIZAR:
					
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
		System.out.println(OPCION_VER_TELEFONO + " para ver el telefono");
		System.out.println(OPCION_CREAR + " para crear un telefono");
		System.out.println(OPCION_ELIMINAR + " para eliminar un telefono");
		System.out.println(OPCION_ACTUALIZAR + " para actualizar un telefono");
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
