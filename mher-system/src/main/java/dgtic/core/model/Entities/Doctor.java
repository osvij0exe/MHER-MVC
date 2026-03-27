package dgtic.core.model.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@jakarta.persistence.Entity
@Table(name = "DOCTORES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "GENERO")
    private char genero;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "CEDULA_PROFESIONAL")
    private String cedulaProfesional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESPECIALIDAD_ID")
    public Especialidad especialidad;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Cita> citas;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    public List<HistoriaClinica> historiasClinicas;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    public List<Recetario> recetas;

    public static String nameOf()
    {
        return Doctor.class.getSimpleName().toLowerCase();
    }
}
