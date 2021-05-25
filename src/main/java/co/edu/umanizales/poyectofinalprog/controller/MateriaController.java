package co.edu.umanizales.poyectofinalprog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import co.edu.umanizales.poyectofinalprog.repository.MateriaDao;
import co.edu.umanizales.poyectofinalprog.services.MateriaService;
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

import co.edu.umanizales.poyectofinalprog.entity.Materia;
import co.edu.umanizales.poyectofinalprog.services.IMateriaService;

@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("api/materia")
public class MateriaController {
    @Autowired
    private IMateriaService materiaService;

    @GetMapping("/all")
    public List<Materia> index() {
        return materiaService.findAll();
    }

    @GetMapping("/filtro/{documento}/{nombre}/{page}")
    public Page<Materia> Filtrado(@PathVariable String nombre, @PathVariable int page) {

        if (nombre.equals("-")) {
            nombre = "";
        }
        return materiaService.filtrado( nombre, page);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Materia materia) {
        Materia materiaNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            materiaNew = materiaService.save(materia);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El docente ha sido creado con éxito");
        response.put("docente", materiaNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Materia materia, @PathVariable Long id) {
        Materia materiaActual = materiaService.findById(id);
        Materia materiaActualizado = null;

        Map<String, Object> response = new HashMap<>();

        if (materiaActual == null) {
            response.put("mensaje", "la materia ID: "
                    .concat(id.toString().concat(" no existe en la base de datos por tanto no se puede editar")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            if (materia.getNombre() != null)
                materiaActual.setNombre(materia.getNombre());


        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la materia en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La materia ha sido actualizado con éxito");
        response.put("estudiante", materiaActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @GetMapping("/page/{page}")
    public Page<Materia> index(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return materiaService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            materiaService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la materia en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "la materia ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

}
