package co.edu.umanizales.poyectofinalprog.services;

import co.edu.umanizales.poyectofinalprog.entity.Materia;
import co.edu.umanizales.poyectofinalprog.repository.MateriaDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IMateriaService {

    public Materia save(Materia materia);

    public List<MateriaDao> findAll();

    public Page<Materia> findAll(Pageable pegeable);

    public Materia findById(Long id);

    public Page<Materia> filtrado( String nombre, int page);

    public void delete(Long id);

    public List<Map<String, Object>> findAyudas(Long id);
}
