package dgtic.core.service.doctorService;

import dgtic.core.model.Entities.Cita;
import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.dto.Mappers.CitasMapper;
import dgtic.core.model.dto.Mappers.CitasPacienteMapper;
import dgtic.core.model.dto.Request.DoctorRequest;
import dgtic.core.model.dto.Response.CitasPacienteResponse;
import dgtic.core.model.dto.Response.CitasResponse;
import dgtic.core.model.dto.Response.DoctorNameResponse;
import dgtic.core.model.dto.Response.DoctorResponse;
import dgtic.core.repository.doctores.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService implements IDoctorService{

    @Autowired
    IDoctorRepository doctorRepository;

    @Override
    public List<DoctorResponse> findAll() {
        return List.of();
    }

    @Override
    public DoctorResponse findById(Integer id) {
        return null;
    }


    @Override
    public DoctorResponse save(DoctorRequest clienteDTO) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Page<DoctorService> findByPage(Pageable pageable) {
        return null;
    }

    @Override
    public List<DoctorNameResponse> findDoctorByEspecialidad(Integer id) {

        List<DoctorNameResponse> doctores = doctorRepository.findDoctorByEspecialidad(id);
        return doctores;
    }

    @Override
    public List<CitasResponse> findDoctorsPatientsByDoctorId(Integer doctorId) {
//        List<Cita> citas = doctorRepository.findDoctorsPatientsByDoctorId(doctorId,userId);
        List<Cita> citas = doctorRepository.findDoctorsPatientsByDoctorId(doctorId);

        return CitasMapper.ToDtoCollection(citas);

    }
}
