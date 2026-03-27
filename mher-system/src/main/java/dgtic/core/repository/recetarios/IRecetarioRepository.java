package dgtic.core.repository.recetarios;

import dgtic.core.model.Entities.Recetario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecetarioRepository extends JpaRepository<Recetario,Integer> {
}
