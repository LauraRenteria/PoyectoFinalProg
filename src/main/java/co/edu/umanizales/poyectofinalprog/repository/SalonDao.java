package co.edu.umanizales.poyectofinalprog.repository;
import java.util.List;
import co.edu.umanizales.poyectofinalprog.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalonDao extends JpaRepository<Salon, Long> {

    @Query(value="SELECT e.* FROM materia e " +
            "WHERE e.nombre ilike %:nombre% ", nativeQuery = true)
    List<Salon> materiaFiltro(@Param("nombre") String nombre);
}
