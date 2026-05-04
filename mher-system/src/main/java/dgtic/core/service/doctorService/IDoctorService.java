package dgtic.core.service.doctorService;

import dgtic.core.model.dto.Request.DoctorRequest;
import dgtic.core.model.dto.Response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDoctorService {
    List<DoctorResponse> findAll();
    DoctorResponse findById(Integer id);
    DoctorResponse save(DoctorRequest clienteDTO);
    void deleteById(Integer id);
    Page<DoctorService> findByPage(Pageable pageable);
    List<DoctorNameResponse> findDoctorByEspecialidad(Integer id);
    List<CitasResponse> findDoctorsPatientsByDoctorId(Integer doctorId);
    List<CitasResponse> findDoctorsPatientsByDoctorId(Integer doctorId,String email);
    DoctorUserResponse findByDoctorUserEmail(String email);
}
