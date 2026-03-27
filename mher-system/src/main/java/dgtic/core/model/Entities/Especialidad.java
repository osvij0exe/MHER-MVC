package dgtic.core.model.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@jakarta.persistence.Entity
@Table(name = "ESPECIALIDADES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOMBRE_ESPECIALIDAD")
    private String nombreEspecialidad;

    @OneToMany(mappedBy = "especialidad",fetch = FetchType.LAZY)
    public List<Doctor> doctores;

    public static String nameOf()
    {
        return Especialidad.class.getSimpleName().toLowerCase();
    }
}
