package co.edu.umanizales.poyectofinalprog.services;

import co.edu.umanizales.poyectofinalprog.entity.Estudiante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IEstudianteService {

    public Estudiante save(Estudiante estudiante);

    public List<Estudiante> findAll();


    public Page<Estudiante> findAll(Pageable pegeable);

    public List<Estudiante> porGrupo(Long grupo);

    public Page<Estudiante> porGrupoPage(Long grupo, int page);

    public Estudiante findById(Long id);

    public Integer calcularEdad(Estudiante estudiante);

    public Page<Estudiante> filtrado( String nombre,String id, int page);

    public void delete(Long id);

    public List<Map<String, Object>> findAyudas(Long id);

}
