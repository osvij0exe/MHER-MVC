package dgtic.core.repository.historiasClinicas;

import dgtic.core.model.Entities.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Integer> {
}
