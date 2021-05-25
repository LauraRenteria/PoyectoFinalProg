package co.edu.umanizales.poyectofinalprog.entity;
import javax.persistence.*;
import co.edu.umanizales.poyectofinalprog.entity.Materia;

@Entity
@Table(name = "docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne()
    @JoinColumn(name = "materia", nullable = false, foreignKey = @ForeignKey(name = "FK_DOCENTE_MATERIA"))
    private Materia materia;

    public Docente(Long id, String documento, String nombre, Materia materia) {
        super();
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.materia = materia;
    }

    public Docente() {
        super();
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public Materia getMateria() {
        return materia;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
