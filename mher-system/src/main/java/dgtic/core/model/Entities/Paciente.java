package dgtic.core.model.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@jakarta.persistence.Entity
@Table(name = "PACIENTES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "FECHA_DE_NACIMIENTO")
    private LocalDate fechaDeNacimiento;
    @Column(name = "GENERO")
    private char genero;
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "NUMERO_DE_SEGURIDAD")
    private String numeroDeSeguridad;

    @OneToMany(mappedBy = "paciente" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Cita> citas;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<HistoriaClinica> historiasClinicas;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    public List<Recetario> recetas;


    public static String nameOf()
    {
        return Paciente.class.getSimpleName().toLowerCase();
    }
}
