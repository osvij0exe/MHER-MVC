package dgtic.core.model.dto.Response;



import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorPacinetesCitasResponse {
    private Integer citaId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime citaInicio;
    private LocalDateTime citaFin;
    private Long addMinutes;
    private String citaEstado;

    private DoctorUserResponse doctor;
    private EspecialidadResponse especialidad;
    private PacienteResponse paciente;


    public static Long getDuration(LocalDateTime start, LocalDateTime end)
    {
        Long minutes = Duration.between(start,end).toMinutes();
        return  minutes;

    }

}
