package dgtic.core.model.dto.Response;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitasPacienteResponse {
    private Integer citaId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime citaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime citaFin;
    private String citaEstado;
    public DoctorResponse doctor;
}
