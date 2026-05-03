package dgtic.core.controller;

import dgtic.core.model.dto.Request.RecetaRequest;
import dgtic.core.model.dto.Response.DoctorResponse;
import dgtic.core.model.dto.Response.PacienteRecetasResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
import dgtic.core.model.dto.Response.RecetaResponse;
import dgtic.core.service.doctorService.DoctorService;
import dgtic.core.service.especialidadService.EspecialidadService;
import dgtic.core.service.pacienteService.PacienteService;
import dgtic.core.service.recetarioService.RecetarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("recetario")
public class recetarioController {

    @Autowired
    RecetarioService recetarioService;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    EspecialidadService especialidadService;

    @GetMapping("agregar-receta/paciente/{pacienteId}/receta/{recetaId}")
    public String recetaVista(@PathVariable("pacienteId")Integer pacienteId,@PathVariable("recetaId")Integer recetaId,Model model)
    {
        RecetaResponse recetaResponse = recetarioService.findByPacienteIdAndRecetaId(pacienteId,recetaId);
        model.addAttribute("receta",recetaResponse);
        model.addAttribute("contenido","Agregar nueva Receta");
        return "recetario/agregar-receta";

    }

    @PostMapping("guardar-receta/paciente/{pacienteId}/receta/{recetaId}")
    public String guardarReceta(
            @PathVariable Integer pacienteId,
            @PathVariable Integer recetaId,
            @ModelAttribute("recetaRequest")RecetaRequest recetaRequest,
            BindingResult bindingResult,
            @RequestParam(value= "especialidadChanged",defaultValue = "false") boolean especialidadChanged,
            Model model) {
        model.addAttribute(("especialidades"), especialidadService.findAll());
        recetaRequest.setPaciente(pacienteService.findById(pacienteId));

        if (recetaRequest.getEspecialidad() != null &&
                recetaRequest.getEspecialidad().getEspecialidadId() != null) {
            model.addAttribute("doctores",
                    doctorService.findDoctorByEspecialidad(
                            recetaRequest.getEspecialidad().getEspecialidadId()
                    ));
            if (especialidadChanged) {
                recetaRequest.setDoctor(null);
                model.addAttribute("warning", "Selecciona un doctor");
                return "recetario/agregar-receta";
            }
        } else{
            model.addAttribute("doctores", List.of());
            model.addAttribute("warning", "Seleciona una especialidad");
            return "recetario/agregar-receta";
        }

        if (recetaRequest.getDoctor() == null ||
                recetaRequest.getDoctor().getDoctorId() == null) {
                model.addAttribute("warning", "Selecciona un doctor");
                return "recetario/agregar-receta";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("warning", "Corrige los errores del formulario");
            return "recetario/agregar-receta";
        }
        recetarioService.save(recetaRequest);
        return "redirect:/pacientes/detalle-recetas-paciente/" + pacienteId;

    }

}

