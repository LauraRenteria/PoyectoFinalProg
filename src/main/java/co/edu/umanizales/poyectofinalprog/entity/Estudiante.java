package co.edu.umanizales.poyectofinalprog.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date nacimiento;

    private Integer edad;

    @Column(length = 20, nullable = false)
    private String estado;


    @PrePersist
    public void prePersist() {

        estado = "MATRICULADO";
        createAt = new Date();
    }


    @SuppressWarnings("deprecation")
    public void cambiarDia() {
        this.nacimiento.setDate(this.nacimiento.getDate() + 1);
        this.nacimiento.setHours(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Estudiante() {
        super();
    }

    public Estudiante(Long id, String documento, String nombre, String apellido, Date createAt,
                      Date nacimiento, Integer edad, String estado) {
        super();
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.createAt = createAt;
        this.nacimiento = nacimiento;
        this.edad = edad;
        this.estado = estado;
    }

}