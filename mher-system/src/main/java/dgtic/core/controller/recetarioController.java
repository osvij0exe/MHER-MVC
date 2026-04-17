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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
