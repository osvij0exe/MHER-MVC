package dgtic.core.model.dto.Mappers;

import dgtic.core.model.Entities.Cita;
import dgtic.core.model.Entities.Paciente;

import dgtic.core.model.dto.Response.PacienteCitasResponse;

public class PacienteCitasMapper {


    public  static Cita ToEntity(){
        Cita cita= new Cita();
//        cita.setPaciente();

        return cita;

    }


    public static PacienteCitasResponse ToDto(Paciente paciente)
    {
        PacienteCitasResponse resposne = new PacienteCitasResponse();
        resposne.setPacienteId(paciente.getId());
        resposne.setNombre(paciente.getNombre());
        resposne.setApellidos(paciente.getApellidos());
        resposne.setGenero(paciente.getGenero());
        resposne.setActive(paciente.isActive());
        resposne.setFechaDeNacimiento(paciente.getFechaDeNacimiento());
        resposne.setNumeroDeSeguridad(paciente.getNumeroDeSeguridad());
        if(paciente.getCitas() != null)
        {
            resposne.setCitas(CitasPacienteMapper.ToDtoCollection(paciente.getCitas()));
        }


        return  resposne;
    }


}
