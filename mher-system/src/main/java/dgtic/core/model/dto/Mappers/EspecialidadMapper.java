package dgtic.core.model.dto.Mappers;


import dgtic.core.model.Entities.Especialidad;
import dgtic.core.model.dto.Response.EspecialidadResponse;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadMapper {

    public static EspecialidadResponse ToDto(Especialidad especialidad) {
        EspecialidadResponse especialidadResponse = new EspecialidadResponse();
        if (especialidad != null) {
            especialidadResponse.setNombreEspecialidad(especialidad.getNombreEspecialidad());
        }
        return especialidadResponse;
    }

    public  static Especialidad ToEntity(EspecialidadResponse especialidadResponse) {
        Especialidad especialidad = new Especialidad();
        especialidad.setNombreEspecialidad(especialidadResponse.getNombreEspecialidad());
        especialidad.setId(especialidadResponse.getEspecialidadId());
        return especialidad;

    }
}
