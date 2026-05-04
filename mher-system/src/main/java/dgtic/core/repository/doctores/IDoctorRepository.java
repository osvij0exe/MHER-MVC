package dgtic.core.repository.doctores;

import dgtic.core.model.Entities.Cita;
import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.dto.Response.DoctorNameResponse;
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

    @Query("""
    SELECT c FROM Cita c
    JOIN FETCH c.paciente p
    JOIN FETCH c.doctor d
    JOIN FETCH d.user u
    WHERE d.id = :doctorId
    AND u.useEmail = :email
    """)
    List<Cita> findDoctorsPatientsByDoctorId(
            @Param("doctorId") Integer doctorId,
            @Param("email") String email
    );

    @Query("""
            SELECT c FROM Cita c
            JOIN FETCH c.paciente p
            WHERE c.doctor.id = :doctorId
            """)
    public List<Cita> findDoctorsPatientsByDoctorId(@Param("doctorId")Integer doctorId);

    @Query("""
            SELECT d FROM Doctor d
            JOIN FETCH d.user u
            WHERE u.useEmail = :email
            """)
    public Doctor findByDoctorUserEmail(@Param("email")String email);
}
