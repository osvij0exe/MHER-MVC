package dgtic.core.service.pacienteService;

import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.dto.Mappers.CitasMapper;
import dgtic.core.model.dto.Mappers.PacienteCitasMapper;
import dgtic.core.model.dto.Mappers.PacienteMapper;
import dgtic.core.model.dto.Request.PacienteRequest;
import dgtic.core.model.dto.Response.CitasResponse;
import dgtic.core.model.dto.Response.PacienteCitasResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
import dgtic.core.repository.pacientes.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService{

    @Autowired
    IPacienteRepository pacienteRepository;

    @Override
    public List<PacienteResponse> findAll() {
        return List.of();
    }

    @Override
    public PacienteResponse findById(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(!paciente.isPresent())
        {
           return  null;
        }

        PacienteResponse pacienteResponse = PacienteMapper.ToDto(paciente.get());
        return pacienteResponse;

    }

    @Override
    public PacienteResponse save(PacienteRequest pacienteRequest) {
        Paciente paciente = PacienteMapper.ToEntity(pacienteRequest);
        pacienteRepository.save(paciente);
        return PacienteMapper.ToDto(paciente);
    }
    @Override
    public PacienteResponse save(Integer id,PacienteRequest pacienteRequest) {
        Paciente paciente = PacienteMapper.ToEntity(pacienteRequest);
        paciente.setId(id);
        pacienteRepository.save(paciente);
        return PacienteMapper.ToDto(paciente);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Page<PacienteResponse> findByPage(Pageable pageable, String search) {
        List<PacienteResponse> citas = pacienteRepository.listarPacientes(search)
                .stream()
                .map(PacienteMapper::ToDto)
                .toList();
        int start = Math.min((int)pageable.getOffset(), citas.size());
        int end = Math.min((start + pageable.getPageSize()), citas.size());
        return new PageImpl<>(citas.subList(start,end),pageable, citas.size());
    }

    @Override
    public PacienteCitasResponse findPacienteCitasById(Integer id) {
        Paciente paciente = pacienteRepository.FindPacienteWithCitas(id);

        return PacienteCitasMapper.ToDto(paciente);

    }


}
