package co.edu.umanizales.poyectofinalprog.entity;
import javax.persistence.*;


@Entity
@Table(name = "materia")
public class Materia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String nombre;

    public Materia(Long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public Materia() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}