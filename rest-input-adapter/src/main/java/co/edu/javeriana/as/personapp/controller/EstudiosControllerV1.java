package co.edu.javeriana.as.personapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.javeriana.as.personapp.adapter.EstudiosInputAdapterRest;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.EstudiosRequest;
import co.edu.javeriana.as.personapp.model.response.EstudiosResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/estudios")
public class EstudiosControllerV1 {

    private final EstudiosInputAdapterRest estudiosInputAdapterRest;

    @Autowired
    public EstudiosControllerV1(EstudiosInputAdapterRest estudiosInputAdapterRest) {
        this.estudiosInputAdapterRest = estudiosInputAdapterRest;
    }

    @ResponseBody
    @GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EstudiosResponse> estudios(@PathVariable String database) {
        log.info("Into estudios REST API");
        return estudiosInputAdapterRest.historial(database.toUpperCase());
    }

    @ResponseBody
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstudiosResponse crearEstudios(@RequestBody EstudiosRequest request) {
        log.info("Esta en el metodo crearEstudios en el controller del API");
        return estudiosInputAdapterRest.crearEstudios(request);
    }

    @ResponseBody
    @PutMapping(path = "/actualizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstudiosResponse actualizarEstudios(@PathVariable String id, @RequestBody EstudiosRequest request) {
        log.info("Actualizando los estudios con ID: {}", id);
        return estudiosInputAdapterRest.actualizarEstudios(id, request);
    }

    @ResponseBody
    @DeleteMapping(path = "/eliminar/{id}")
    public void eliminarEstudios(@PathVariable String id, @RequestBody EstudiosRequest request) {
        log.info("Eliminando los estudios con ID: {}", id);
        estudiosInputAdapterRest.eliminarEstudios(id, request.getDatabase());
    }

    @ResponseBody
    @GetMapping(path = "/{id}/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstudiosResponse> getEstudiosById(@PathVariable String id, @PathVariable String database) {
        log.info("Getting estudios with ID: {} in database: {}", id, database);
        Study study = estudiosInputAdapterRest.obtenerPorID(id, database);
        if (study != null) {
            EstudiosResponse response = new EstudiosResponse(
                    study.getPerson().getIdentification().toString(),
                    study.getProfession().getIdentification().toString(),
                    study.getGraduationDate() != null ? study.getGraduationDate().toString() : null,
                    study.getUniversityName() != null ? study.getUniversityName() : "",
                    database,
                    "Success"
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
