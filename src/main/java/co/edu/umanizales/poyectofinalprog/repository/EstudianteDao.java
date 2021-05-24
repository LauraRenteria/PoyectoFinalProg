package co.edu.umanizales.poyectofinalprog.repository;

import java.util.List;

import java.util.List;
        import java.util.Map;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

        import co.edu.umanizales.poyectofinalprog.entity.Estudiante;

public interface EstudianteDao extends JpaRepository<Estudiante, Long> {


    @Query(value="SELECT e.* FROM estudiantes e " +
            "WHERE e.nombre ilike %:nombre% and e.apellido ilike %:apellido% and e.documento ilike %:identificacion% ", nativeQuery = true)
    List<Estudiante> estudianteFiltro(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("identificacion") String id);

}