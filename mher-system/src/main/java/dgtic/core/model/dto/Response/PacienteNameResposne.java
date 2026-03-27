package dgtic.core.model.dto.Response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteNameResposne {
    private Integer pacienteId;
    private String nombreCompleto;
}
