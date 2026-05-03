package dgtic.core.repository.doctores;

import dgtic.core.model.Entities.Cita;
import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.dto.Request.DoctorRequest;
import dgtic.core.model.dto.Response.DoctorNameResponse;
import dgtic.core.model.dto.Response.DoctorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDoctorRepository extends JpaRepository<Doctor,Integer> {

    @Query("""
            SELECT new dgtic.core.model.dto.Response.DoctorNameResponse(d.id,CONCAT(d.nombre,' ',d.apellidos))
            FROM Doctor d
            JOIN d.especialidad e
            WHERE e.id = :especialidadId
            """)
    public List<DoctorNameResponse> findDoctorByEspecialidad(@Param("especialidadId")Integer especialidadId);

//    @Query("""
//            SELECT c FROM Cita c
//            JOIN FETCH c.paciente p
//            JOIN FETCH d.doctor d
//            WHERE c.id = :doctorId
//            AND d.user.useId = :userId
//            """)
//    public List<Cita> findDoctorsPatientsByDoctorId(Integer doctorId, Long userId);

    @Query("""
            SELECT c FROM Cita c
            JOIN FETCH c.paciente p
            WHERE c.doctor.id = :doctorId
            """)
    public List<Cita> findDoctorsPatientsByDoctorId(Integer doctorId);


}
