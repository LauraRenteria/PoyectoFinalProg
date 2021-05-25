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

import co.edu.umanizales.poyectofinalprog.entity.Docente;
import co.edu.umanizales.poyectofinalprog.services.IDocenteService;
@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("api/docente")

public class DocenteController {
    @Autowired
    private IDocenteService docenteService;

    @GetMapping("/all")
    public List<Docente> index() {
        return docenteService.findAll();
    }

    @GetMapping("/filtro/{documento}/{nombre}/{page}")
    public Page<Docente> Filtrado(@PathVariable String documento,
                                     @PathVariable String nombre, @PathVariable int page) {

        if (nombre.equals("-")) {
            nombre = "";
        }

        if (documento.equals("-")) {
            documento = "";
        }
        return docenteService.filtrado(documento, nombre, page);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Docente docente) {
        Docente docenteNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            docenteNew = docenteService.save(docente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El docente ha sido creado con éxito");
        response.put("docente", docenteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Docente docente, @PathVariable Long id) {
        Docente docenteActual = docenteService.findById(id);
        Docente docenteActualizado = null;

        Map<String, Object> response = new HashMap<>();

        if (docenteActual == null) {
            response.put("mensaje", "el docente ID: "
                    .concat(id.toString().concat(" no existe en la base de datos por tanto no se puede editar")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            if (docente.getDocumento() != null)
                docenteActual.setDocumento(docente.getDocumento());
            if (docente.getNombre()!= null) {
                docenteActual.setNombre(docente.getNombre());
            }
            if (docente.getMateria() != null)
                docenteActual.setMateria(docente.getMateria());

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el docente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El docente ha sido actualizado con éxito");
        response.put("estudiante", docenteActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @GetMapping("/page/{page}")
    public Page<Docente> index(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return docenteService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            docenteService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el docente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El docente ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

}
