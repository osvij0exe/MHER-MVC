package dgtic.core.service.citaServices;

import dgtic.core.model.Entities.Cita;
import dgtic.core.model.dto.Request.CitasRequest;
import dgtic.core.model.dto.Response.CitasResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;

public interface ICitaService {
    List<CitasResponse> findAll();
    CitasResponse findById(Integer id);
    CitasResponse findByIdWithRelations(Integer id);
    CitasResponse findByIdWithDoctorAndSpecialities(Integer id);
    CitasResponse save(CitasRequest clienteDTO);
    void deleteById(Integer id);
    Page<CitasResponse> findByPage(Pageable pageable, @Param("search") String search);
    public void update(CitasRequest request);
    public boolean existeCita(Integer doctorId, Integer pacienteId, LocalDateTime citaInicio, LocalDateTime citaFin);


}
