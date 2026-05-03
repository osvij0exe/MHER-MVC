package dgtic.core.auth.repository.citas;

import dgtic.core.model.Entities.Cita;

import dgtic.core.model.dto.Response.CitasResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ICitaRepository extends JpaRepository<Cita,Integer> {

    @Query("""
        SELECT c
        FROM Cita c
        LEFT JOIN c.paciente p
        LEFT JOIN c.doctor d
        WHERE LOWER(c.citaEstado) = 'pendiente'
        AND (
             :search IS NULL
             OR p.nombre LIKE %:search%
             OR p.apellidos LIKE %:search%
             OR d.nombre LIKE %:search%
             OR d.apellidos LIKE %:search%
        )
        """)
    public List<Cita> obtenerCitasPendientes(@Param("search")String search);

    @Query("""
        SELECT c
        FROM Cita c
        LEFT JOIN FETCH c.paciente p
        LEFT JOIN FETCH c.doctor d
        LEFT JOIN FETCH d.especialidad e
        WHERE c.id = :id
        """)
    public Optional<Cita> findByIdWithRelations(Integer id);

    @Query("""
        SELECT COUNT(c) > 0
        FROM Cita c
        WHERE c.doctor.id = :doctorId
        AND c.paciente.id = :pacienteId
        AND c.citaInicio < :citaFin
        AND c.citaFin > :citaInicio
        """)
    boolean existeCita(
            @Param("doctorId") Integer doctorId,
            @Param("pacienteId") Integer pacienteId,
            @Param("citaInicio") LocalDateTime citaInicio,
            @Param("citaFin") LocalDateTime citaFin
    );

    @Query("""
            SELECT c FROM Cita c
            JOIN FETCH c.doctor d
            JOIN FETCH d.especialidad
            WHERE c.id = :id
            """)
    Cita findByIdWithDoctorAndEspecialidad(Integer id);


}
