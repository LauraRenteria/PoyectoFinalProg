package co.edu.umanizales.poyectofinalprog.repository;
import java.util.List;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.umanizales.poyectofinalprog.entity.Docente;




public interface DocenteDao extends JpaRepository<Docente, Long>{

    @Query(value="SELECT e.* FROM docentes e " +
            "WHERE e.documento ilike %:documento% and e.nombre ilike %:nombre%", nativeQuery = true)
    List<Docente> docenteFiltro(@Param("documento") String documento, @Param("nombre") String nombre);
}
