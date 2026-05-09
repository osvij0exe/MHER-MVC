package dgtic.core.model.dto.Mappers;

import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.Especialidad;
import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.dto.Request.HistoriaClinicaRequest;
import dgtic.core.model.dto.Response.EspecialidadResponse;
import dgtic.core.model.dto.Response.HistoriaClinicaResponse;

import java.util.List;

public class HistoriaClinicaMapper {

    public static HistoriaClinica ToEntity(HistoriaClinicaRequest request)
    {
        if(request == null) return null;

        HistoriaClinica historia = new HistoriaClinica();

        if(request.getId() != null)
        {
            historia.setId(request.getId());
        }

        historia.setAntecedentesMedicos(request.getAntecedentesMedicos());
        historia.setDiagnostico(request.getDiagnostico());
        historia.setMotivoDeLaConsulta(request.getMotivoDeLaConsulta());
        historia.setTratamientoMedico(request.getTratamientoMedico());
        historia.setDescripcionEnfermedad(request.getDescripcionEnfermedad());

        Doctor doctor = new Doctor();
        doctor.setId(request.getDoctor().getDoctorId());
        Especialidad especialidad = new Especialidad();
        especialidad.setId(request.getEspecialidad().getEspecialidadId());
        doctor.setEspecialidad(especialidad);
        historia.setDoctor(doctor);

        Paciente paciente = new Paciente();
        paciente.setId(request.getPaciente().getPacienteId());
        historia.setPaciente(paciente);
        return historia;
    }

    public static HistoriaClinicaResponse ToDto(HistoriaClinica historia)
    {
        if(historia == null) return null;

        HistoriaClinicaResponse response = new HistoriaClinicaResponse();
        response.setId(historia.getId());
        response.setAntecedentesMedicos(historia.getAntecedentesMedicos());
        response.setDiagnostico(historia.getDiagnostico());
        response.setMotivoDeLaConsulta(historia.getMotivoDeLaConsulta());
        response.setTratamientoMedico(historia.getTratamientoMedico());
        response.setDescripcionEnfermedad(historia.getDescripcionEnfermedad());

        if(historia.getDoctor() != null)
        {
            Doctor doctor = historia.getDoctor();
            response.setDoctor(DoctorMapper.ToDto(doctor));
        }

        if(historia.getPaciente() != null)
        {
            Paciente paciente = historia.getPaciente();
            response.setPaciente(PacienteMapper.ToDto(paciente));
        }

        EspecialidadResponse especialidadResponse = EspecialidadMapper.ToDto(historia.getDoctor().getEspecialidad());
        response.setEspecialidad(especialidadResponse);

        return response;
    }

    public static List<HistoriaClinicaResponse> ToDtoCollection(List<HistoriaClinica> historias)
    {
        List<HistoriaClinicaResponse> responses = historias
                .stream()
                .map(HistoriaClinicaMapper ::ToDto)
                .toList();

        return responses;
    }


}
