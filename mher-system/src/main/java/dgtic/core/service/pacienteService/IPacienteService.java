package dgtic.core.service.pacienteService;

import dgtic.core.model.dto.Request.PacienteRequest;
import dgtic.core.model.dto.Response.PacienteCitasResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPacienteService {
    List<PacienteResponse> findAll();
    PacienteResponse findById(Integer id);
    PacienteResponse save(PacienteRequest clienteDTO);
    PacienteResponse save(Integer id,PacienteRequest clienteDTO);
    void deleteById(Integer id);
    Page<PacienteResponse> findByPage(Pageable pageable, @Param("search")String search);
    PacienteCitasResponse findPacienteCitasById(Integer id);
}
