package dgtic.core.model.dto.Mappers;



import dgtic.core.model.Entities.Cita;
import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.Especialidad;
import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.dto.Request.CitasRequest;
import dgtic.core.model.dto.Response.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CitasMapper {
    public static Cita ToEntity(CitasRequest request)
    {
        Cita cita = new Cita();
        if(request.getCitaId() != null)
        {
            cita.setId(request.getCitaId());
        }
        Doctor doctor = new Doctor();
        doctor.setId(request.getDoctor().getDoctorId());
        Especialidad especialidad = new Especialidad();
        especialidad.setId(request.getEspecialidad().getEspecialidadId());
        doctor.setEspecialidad(especialidad);
        cita.setDoctor(doctor);
        Paciente paciente = new Paciente();
        paciente.setId(request.getPaciente().getPacienteId());
        cita.setPaciente(paciente);
        cita.setCitaEstado("pendiente");
        cita.setCitaInicio(request.getCitaInicio());
        cita.setCitaFin(request.getCitaInicio().plusMinutes(request.getAddMinutes()));
        return cita;
    };

    public static CitasResponse ToDto(Cita cita)
    {
        CitasResponse response = new CitasResponse();

        response.setCitaId(cita.getId());
        response.setCitaEstado(cita.getCitaEstado());
        response.setCitaInicio(cita.getCitaInicio());
        response.setCitaFin(cita.getCitaFin());
        Long duration = CitasResponse.getDuration(cita.getCitaInicio(), cita.getCitaFin());
        response.setAddMinutes(duration);
        response.setCitaEstado(cita.getCitaEstado());
        if (cita.getDoctor() != null && cita.getDoctor().getEspecialidad() != null) {
            DoctorNameResponse doctorResponse = DoctorMapper.OnlyNameToDto(cita.getDoctor());
            response.setDoctor(doctorResponse);
        }
        if (cita.getPaciente() != null) {
            PacienteResponse pacienteResponse = PacienteMapper.ToDto(cita.getPaciente());
            response.setPaciente(pacienteResponse);
        }
        if (cita.getDoctor() != null && cita.getDoctor().getEspecialidad() != null) {
            DoctorNameResponse doctorResponse = DoctorMapper.OnlyNameToDto(cita.getDoctor());
            response.setDoctor(doctorResponse);

            EspecialidadResponse especialidadResponse = EspecialidadMapper.ToDto(cita.getDoctor().getEspecialidad());
            response.setEspecialidad(especialidadResponse);
        }
            return response;
    }

    public static List<CitasResponse> ToDtoCollection(List<Cita> citas)
    {

        Paciente pacientes = new Paciente();
        PacienteResponse pacienteResponse = PacienteMapper.ToDto(pacientes);

        List<CitasResponse> response = citas.stream()
                .map(CitasMapper::ToDto)
                .collect(Collectors.toList());
        return  response;
    };
}
