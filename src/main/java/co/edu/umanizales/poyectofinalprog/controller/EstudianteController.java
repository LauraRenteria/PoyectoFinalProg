package co.edu.umanizales.poyectofinalprog.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.umanizales.poyectofinalprog.entity.Estudiante;
import co.edu.umanizales.poyectofinalprog.services.IEstudianteService;

@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("api/estudiante")
public class EstudianteController {
    @Autowired
    private IEstudianteService estudianteService;

    @GetMapping("/all")
    public List<Estudiante> index() {
        return estudianteService.findAll();
    }

    @GetMapping("/filtro/{nombre}/{identificacion}/{page}")
    public Page<Estudiante> Filtrado(@PathVariable String nombre,
                                     @PathVariable String identificacion, @PathVariable int page) {

        if (nombre.equals("-")) {
            nombre = "";
        }

        if (identificacion.equals("-")) {
            identificacion = "";
        }
        return estudianteService.filtrado(nombre, identificacion, page);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Estudiante estudiante) {
        Estudiante estudianteNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println(estudiante.getNacimiento());
            estudianteNew = estudianteService.save(estudiante);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El estudiante ha sido creado con ??xito");
        response.put("estudiante", estudianteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Estudiante estudiante, @PathVariable Long id) {
        Estudiante estudianteActual = estudianteService.findById(id);
        Estudiante estudianteActualizado = null;

        Map<String, Object> response = new HashMap<>();

        if (estudianteActual == null) {
            response.put("mensaje", "el estudiante ID: "
                    .concat(id.toString().concat(" no existe en la base de datos por tanto no se puede editar")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            if (estudiante.getNombre() != null)
                estudianteActual.setNombre(estudiante.getNombre());
            if (estudiante.getApellido() != null) {
                estudianteActual.setApellido(estudiante.getApellido());
            }
            if (estudiante.getDocumento() != null)
                estudianteActual.setDocumento(estudiante.getDocumento());
            if (estudiante.getEstado() != null && (estudiante.getEstado().equals("MATRICULADO")
                    || estudiante.getEstado().equals("DESERCION") || estudiante.getEstado().equals("TRASLADO"))) {
                estudianteActual.setEstado(estudiante.getEstado());
            }

            if (estudiante.getNacimiento() != null) {
                estudianteActual.setNacimiento(estudiante.getNacimiento());
            }
            estudianteActualizado = estudianteService.save(estudianteActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el estudiante en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El estudiante ha sido actualizado con ??xito");
        response.put("estudiante", estudianteActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @GetMapping("/page/{page}")
    public Page<Estudiante> index(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return estudianteService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            estudianteService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el estudiante en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El estudiante ha sido eliminado con ??xito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

}
