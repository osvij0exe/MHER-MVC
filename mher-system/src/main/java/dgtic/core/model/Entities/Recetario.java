package dgtic.core.model.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@jakarta.persistence.Entity
@Table(name = "RECETARIO")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recetario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "FECHA_DE_EMISION")
    private LocalDate fechaEmision;
    @Column(name = "INDICACION_MEDICA")
    private String indicacionMedica;
    @Column(name = "VIA_DE_ADMINISTRACION")
    private String viaDeAdministracion;
    @Column(name = "DURACION_DEL_TRATAMIENTO")
    private String duracionDelTratamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCTOR_ID")
    public Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PACIENTE_ID")
    public Paciente paciente;

    public static String nameOf()
    {
        return Recetario.class.getSimpleName().toLowerCase();
    }
}
