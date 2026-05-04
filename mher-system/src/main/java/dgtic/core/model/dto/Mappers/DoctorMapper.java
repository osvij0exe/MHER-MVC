package dgtic.core.model.dto.Mappers;



import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.Especialidad;
import dgtic.core.model.dto.Request.DoctorRequest;
import dgtic.core.model.dto.Response.DoctorNameResponse;
import dgtic.core.model.dto.Response.DoctorResponse;
import dgtic.core.model.dto.Response.DoctorUserResponse;
import dgtic.core.model.dto.Response.EspecialidadResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {
    public static Doctor ToEntity(DoctorRequest request)
    {
        Doctor doctores = new Doctor();
        doctores.setNombre(request.getNombre());
        doctores.setApellidos(request.getApellido());
        doctores.setGenero(request.getGenero());
        doctores.setCedulaProfesional(request.getCedulaProfesional());
        doctores.setActive(true);
        EspecialidadResponse especialidadResponse = new EspecialidadResponse();
        especialidadResponse.setEspecialidadId(request.getEspecialidadId());
        Especialidad especialidad =  EspecialidadMapper.ToEntity(especialidadResponse);
        doctores.setEspecialidad(especialidad);
        return doctores;

    };

    public static DoctorResponse ToDto(Doctor doctores)
    {
        DoctorResponse response = new DoctorResponse();
        response.setNombre(doctores.getNombre());
        response.setApellidos(doctores.getApellidos());
        response.setGenero(doctores.getGenero());
        response.setActive(doctores.isActive());
        response.setCedulaProfesional(doctores.getCedulaProfesional());
        if (doctores.getEspecialidad() != null) {
            EspecialidadResponse especialidadResponse = EspecialidadMapper.ToDto(doctores.getEspecialidad());
            response.setEspecialidad(especialidadResponse);
        }
        return response;
    }

    public static DoctorNameResponse OnlyNameToDto(Doctor doctores)
    {
        DoctorNameResponse response = new DoctorNameResponse();
        response.setDoctorId(doctores.getId());
        response.setNombreCompleto(doctores.getNombre()+" "+doctores.getApellidos());
        return response;
    }

    public static DoctorUserResponse ToDoctorsUserDto(Doctor doctor)
    {
        DoctorUserResponse response = new DoctorUserResponse();
        response.setDoctorId(doctor.getId());
        response.setNombreCompleto(doctor.getNombre()+" "+doctor.getApellidos());
        response.setUserId(doctor.getUser().getUseId());
        response.setEmail(doctor.getUser().getUseEmail());
        return response;
    }


    public static List<DoctorResponse> ToDtoCollection(List<Doctor> doctores)
    {
        List<DoctorResponse> response = doctores.stream()
                .map(DoctorMapper::ToDto)
                .collect(Collectors.toList());
        return  response;
    };

}
