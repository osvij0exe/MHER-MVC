package dgtic.core.auth.dto;

import dgtic.core.model.dto.Request.DoctorRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorUserRegister {
    DoctorRequest doctor = new DoctorRequest();
    UserInfoDTO user = new UserInfoDTO();
}
