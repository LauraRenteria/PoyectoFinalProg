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

import co.edu.umanizales.poyectofinalprog.services.ISalonService;
import co.edu.umanizales.poyectofinalprog.entity.Salon;
@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("api/salon")
public class SalonController {
    @Autowired
    private ISalonService salonService;

    @GetMapping("/all")
    public List<Salon> index() {
        return salonService.findAll();
    }

    @GetMapping("/filtro/{documento}/{nombre}/{page}")
    public Page<Salon> Filtrado(@PathVariable String nombre, @PathVariable int page) {

        if (nombre.equals("-")) {
            nombre = "";
        }
        return salonService.filtrado( nombre, page);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Salon salon) {
        Salon salonNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            salonNew = salonService.save(salon);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El salon ha sido creado con éxito");
        response.put("salon", salonNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Salon salon, @PathVariable Long id) {
        Salon salonActual = salonService.findById(id);
        Salon salonActualizado = null;

        Map<String, Object> response = new HashMap<>();

        if (salonActual == null) {
            response.put("mensaje", "el salon ID: "
                    .concat(id.toString().concat(" no existe en la base de datos por tanto no se puede editar")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            if (salonActual.getNombre() != null)
                salonActual.setNombre(salon.getNombre());


        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el salon en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El salon ha sido actualizado con éxito");
        response.put("estudiante", salonActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @GetMapping("/page/{page}")
    public Page<Salon> index(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return salonService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            salonService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el salon la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "el salon  ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }
}
