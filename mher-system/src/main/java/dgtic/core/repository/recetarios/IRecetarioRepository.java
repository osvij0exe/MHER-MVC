package dgtic.core.repository.recetarios;

import dgtic.core.model.Entities.Recetario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRecetarioRepository extends JpaRepository<Recetario,Integer> {


    @Query("""
            SELECT r
            FROM Recetario r
            LEFT JOIN FETCH r.paciente p
            LEFT JOIN FETCH r.docto d
            WHERE p.id = :pacienteId
            AND r.id = :recetaId
            """)
    public Recetario findByPacienteIdAndRecetarioId(Integer pacienteId, Integer recetaId);
}
