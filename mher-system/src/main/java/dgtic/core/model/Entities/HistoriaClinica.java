package dgtic.core.model.Entities;

import jakarta.persistence.*;
import lombok.*;

@jakarta.persistence.Entity
@Table(name = "HISTORIAS_CLINICAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "MOTIVO_DE_LA_CONSULTA")
    public String motivoDeLaConsulta;
    @Column(name = "DESCRIPCION_ENFERMEDAD")
    public String descripcionEnfermedad;
    @Column(name = "ANTECEDENTES_MEDICOS")
    public String antecedentesMedicos;
    @Column(name = "DIAGNOSTICO")
    public String diagnostico;
    @Column(name = "TRATAMIENTO_MEDICO")
    public String tratamientoMedico;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCTOR_ID")
    public Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PACIENTE_ID")
    public Paciente paciente;

    public static String nameOf()
    {
        return HistoriaClinica.class.getSimpleName().toLowerCase();
    }

}
