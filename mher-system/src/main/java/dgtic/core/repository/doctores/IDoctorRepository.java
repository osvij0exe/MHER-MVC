package dgtic.core.repository.doctores;

import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.dto.Request.DoctorRequest;
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

}
