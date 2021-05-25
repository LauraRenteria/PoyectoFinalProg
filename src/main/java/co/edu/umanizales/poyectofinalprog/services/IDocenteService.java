package co.edu.umanizales.poyectofinalprog.services;

import co.edu.umanizales.poyectofinalprog.entity.Docente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IDocenteService {
    public Docente save(Docente docente);

    public List<Docente> findAll();

    public Page<Docente> findAll(Pageable pegeable);

    public Docente findById(Long id);

    public Page<Docente> filtrado( String id,String nombre, int page);

    public void delete(Long id);

    public List<Map<String, Object>> findAyudas(Long id);

}
