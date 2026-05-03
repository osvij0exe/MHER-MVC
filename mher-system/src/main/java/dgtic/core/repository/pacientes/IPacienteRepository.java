package dgtic.core.repository.pacientes;

import dgtic.core.model.Entities.Paciente;
import dgtic.core.model.dto.Response.PacienteNameResposne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPacienteRepository extends JpaRepository<Paciente,Integer> {

    @Query("""
    SELECT new dgtic.core.model.dto.Response.PacienteNameResposne(
        p.id,
        CONCAT(p.nombre, ' ', p.apellidos)
    )
    FROM Paciente p
    WHERE (:search IS NULL OR LOWER(CONCAT(p.nombre, ' ', p.apellidos)) LIKE LOWER(CONCAT('%', :search, '%')))
""")
    List<PacienteNameResposne> findByNombre(@Param("search") String search);


    @Query("""
        SELECT p
        FROM Paciente p
        WHERE (
             :search IS NULL
             OR p.nombre LIKE %:search%
             OR p.apellidos LIKE %:search%
             OR p.numeroDeSeguridad LIKE %:search%
        )
        """)
    public List<Paciente> listarPacientes(@Param("search")String search);


    @Query("""
            SELECT DISTINCT p
            FROM Paciente p
            LEFT JOIN FETCH p.citas c
            LEFT JOIN FETCH c.doctor d
            LEFT JOIN FETCH d.especialidad
            WHERE p.id = :id
            """)
    public Paciente findPacienteWithCitas(Integer id);

    @Query("""
            SELECT DISTINCT p
            FROM Paciente p
            LEFT JOIN FETCH p.recetas r
            LEFT JOIN FETCH r.doctor d
            LEFT JOIN FETCH d.especialidad
            WHERE p.id = :id
            """)
    public Paciente findPacienteWithRecetas(Integer id);


    @Query("""
            SELECT DISTINCT p
            FROM Paciente p
            LEFT JOIN FETCH p.historiasClinicas h
            LEFT JOIN FETCH h.doctor d
            LEFT JOIN FETCH d.especialidad
            WHERE p.id = :id
            """)
    public Paciente findPacienteWithHsitoriaClinica(Integer id);
}
