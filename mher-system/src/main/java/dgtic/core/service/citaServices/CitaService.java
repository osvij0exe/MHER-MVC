package dgtic.core.service.citaServices;

import dgtic.core.model.Entities.Cita;
import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.Especialidad;
import dgtic.core.model.dto.Mappers.CitasMapper;
import dgtic.core.model.dto.Request.CitasRequest;
import dgtic.core.model.dto.Response.CitasResponse;
import dgtic.core.repository.citas.ICitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService implements  ICitaService{

    @Autowired
    private ICitaRepository citaRepository;

    @Autowired
    private CitasMapper citasMapper;


    @Override
    public List<CitasResponse> findAll() {
        List<Cita> citas =  citaRepository.findAll();

        return CitasMapper.ToDtoCollection(citas);
    }

    @Override
    public CitasResponse findById(Integer id) {
        Optional<Cita>  cita = citaRepository.findById(id);

        //Validar

        CitasResponse response = CitasMapper.ToDto(cita.get());

        return response;
    }

    @Override
    public CitasResponse findByIdWithRelations(Integer id) {
         Cita cita = citaRepository.findByIdWithRelations(id).orElse(null);
         return  CitasMapper.ToDto(cita);
    }

    @Override
    public CitasResponse findByIdWithDoctorAndSpecialities(Integer id) {
        Cita cita = citaRepository.findByIdWithDoctorAndEspecialidad(id);
        return  CitasMapper.ToDto(cita);
    }

    @Override
    public CitasResponse save(CitasRequest citaRequest) {

        Cita cita = CitasMapper.ToEntity((citaRequest));


        Cita citaSaved = citaRepository.save(cita);


        return CitasMapper.ToDto(citaSaved);
    }

    @Override
    public void deleteById(Integer id) {
        citaRepository.deleteById(id);

    }

    @Override
    public Page<CitasResponse> findByPage(Pageable pageable, @Param("search") String search) {


        List<CitasResponse> citas = citaRepository.obtenerCitasPendientes(search)
                .stream()
                .map(CitasMapper::ToDto)
                .toList();

        int start = Math.min((int)pageable.getOffset(), citas.size());
        int end = Math.min((start + pageable.getPageSize()), citas.size());
        return new PageImpl<>(citas.subList(start,end),pageable, citas.size());

    }

    @Override
    public boolean existeCita(Integer doctorId, Integer pacienteId, LocalDateTime citaInicio, LocalDateTime citaFin) {
        boolean existe = citaRepository.existeCita(doctorId,pacienteId,citaInicio,citaFin);

        return  existe;
    }

    public void update(CitasRequest request) {
        // Busca la cita existente por ID
        Cita citaExistente = citaRepository.findById(request.getCitaId())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        // Actualiza solo los campos modificables
        citaExistente.setCitaInicio(request.getCitaInicio());
        citaExistente.setCitaFin(request.getCitaInicio().plusMinutes(request.getAddMinutes()));
        Doctor doctor = new Doctor();
        doctor.setId(request.getDoctor().getDoctorId());
        citaExistente.setDoctor(doctor);
        Especialidad especialidad = new Especialidad();
        especialidad.setId(request.getEspecialidad().getEspecialidadId());
        doctor.setEspecialidad(especialidad);
        citaRepository.save(citaExistente);
    }
}
