package co.edu.umanizales.poyectofinalprog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.umanizales.poyectofinalprog.entity.Materia;
public interface MateriaDao extends JpaRepository<MateriaDao, Long>{


    @Query(value="SELECT e.* FROM materia e " +
            "WHERE e.nombre ilike %:nombre% ", nativeQuery = true)
    List<Materia> materiaFiltro(@Param("nombre") String nombre);
}
