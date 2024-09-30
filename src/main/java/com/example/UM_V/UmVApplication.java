package com.example.UM_V;

import com.example.UM_V.enumeraciones.Especialidades;
import com.example.UM_V.repositorio.AlumnoRepository;
import com.example.UM_V.repositorio.PersonaRepository;
import com.example.UM_V.repositorio.ProfesorRepository;
import com.example.UM_V.entidades.Profesor;
import com.example.UM_V.entidades.Alumno;
import com.example.UM_V.enumeraciones.Titulos;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class UmVApplication {
	private static final Logger logger = LoggerFactory.getLogger(UmVApplication.class);

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private ProfesorRepository profesorRepository;
	@Autowired
	private AlumnoRepository alumnoRepository;

	public static void main(String[] args) {
		SpringApplication.run(UmVApplication.class, args);

		System.out.println("funcionando");

	}

	@Bean
	@Transactional
	CommandLineRunner init(PersonaRepository personaRepository,
						   AlumnoRepository alumnoRepository,
						   ProfesorRepository profesorRepository) {
		return args -> {
			// Creo un objeto alumno
			Profesor pro1 = Profesor.builder()
					.nombre("Alberto")
					.apellido("Cortez")
					.fechaIngreso(LocalDate.of(2022, 1, 1))
					.cantHijos(2)
					.titulo(Titulos.MASTER)
					.sueldo(new BigDecimal("123.45"))
					.build();

			Profesor pro2 = Profesor.builder()
					.nombre("Roberto")
					.apellido("Rodriguez")
					.fechaIngreso(LocalDate.of(2020, 3, 28))
					.cantHijos(0)
					.titulo(Titulos.INGENIERO)
					.sueldo(new BigDecimal("678.34"))
					.build();

			// Lo grabo a través del repositorio de Spring
			profesorRepository.save(pro1);
			profesorRepository.save(pro2);

			Alumno al1 = Alumno.builder()
					.nombre("Marcelo")
					.apellido("Lopez")
					.legajo(44890)
					.especialidad(Especialidades.BACHILLER)
					.build();

			Alumno al2 = Alumno.builder()
					.nombre("Carolina")
					.apellido("Lucero")
					.legajo(89334)
					.especialidad(Especialidades.PERITO_MERCANTIL)
					.build();

			// Lo grabo a través del repositorio de Spring
			alumnoRepository.save(al1);
			alumnoRepository.save(al2);
		};
	};

}
