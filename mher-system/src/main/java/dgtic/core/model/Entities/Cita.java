package dgtic.core.model.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "CITAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CITA_INICIO")
    private LocalDateTime citaInicio;
    @Column(name = "CITA_FIN")
    private LocalDateTime citaFin;
    @Column(name = "CITA_ESTADO")
    private String citaEstado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCTOR_ID")
    public Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PACIENTE_ID")
    public Paciente paciente;


    public static String nameOf()
    {
        return Cita.class.getSimpleName().toLowerCase();
    }
}
