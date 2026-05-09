package dgtic.core.controller;

import dgtic.core.model.Entities.Recetario;
import dgtic.core.model.dto.Request.RecetaRequest;
import dgtic.core.model.dto.Response.*;
import dgtic.core.service.doctorService.DoctorService;
import dgtic.core.service.especialidadService.EspecialidadService;
import dgtic.core.service.pacienteService.PacienteService;
import dgtic.core.service.recetarioService.RecetarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    @GetMapping("agregar-receta/paciente/{pacienteId}")
    public String recetaVista(@PathVariable("pacienteId")Integer pacienteId,Model model)
    {
        PacienteResponse paciente = pacienteService.findById(pacienteId);
        RecetaRequest receta = new RecetaRequest();
        receta.setPaciente(paciente);
        List<EspecialidadResponse> especialidades = especialidadService.findAll();

        model.addAttribute("contenido","Agregar nueva Receta");

        model.addAttribute("receta",receta);
        model.addAttribute("especialidades",especialidades);
        model.addAttribute("doctores",List.of());

        return "recetario/agregar-receta";

    }

    @PostMapping("guardar-receta/paciente/{pacienteId}")
    public String guardarReceta(
            @PathVariable Integer pacienteId,
            @ModelAttribute("receta")RecetaRequest recetaRequest,
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

    @GetMapping("modificar-receta/paciente/{pacienteId}/receta/{Id}")
    public String modificarRecetaVista(@PathVariable("pacienteId")Integer pacienteId,
                                       @PathVariable("Id")Integer recetaId
                                       ,Model model) {
        RecetaResponse receta = recetarioService.findRecetaById(recetaId);


        model.addAttribute("contenido", "Modificar Receta");

        model.addAttribute("receta",receta);
        model.addAttribute("especialidades",especialidadService.findAll());

        if(receta.getEspecialidad() != null
         && receta.getEspecialidad().getEspecialidadId() != null)
        {
            model.addAttribute("doctores",
                    doctorService.findDoctorByEspecialidad(
                            receta.getEspecialidad().getEspecialidadId()
                    ));
        }else {
            model.addAttribute("doctores", List.of());
        }

        return "recetario/modificar-receta";

    }

    @PostMapping("modificar-receta/paciente/{pacienteId}/receta/{Id}")
    public String guardarModificacionReceta(
            @PathVariable Integer pacienteId,
            @PathVariable Integer Id,
            @ModelAttribute("receta")RecetaRequest recetaRequest,
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
                return "recetario/modificar-receta";
            }
        } else{
            model.addAttribute("doctores", List.of());
            model.addAttribute("warning", "Seleciona una especialidad");
            return "recetario/modificar-receta";
        }

        if (recetaRequest.getDoctor() == null ||
                recetaRequest.getDoctor().getDoctorId() == null) {
            model.addAttribute("warning", "Selecciona un doctor");
            return "recetario/modificar-receta";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("warning", "Corrige los errores del formulario");
            return "recetario/modificar-receta";
        }
        recetarioService.update(recetaRequest);
        return "redirect:/pacientes/detalle-recetas-paciente/" + pacienteId;

    }




    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable Integer id)
    {
        Recetario receta = recetarioService.findById(id);

        byte[] pdf = recetarioService.generatePdf(receta);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("receta-" + id + ".pdf")
                        .build()
        );

        return  new ResponseEntity<>(pdf,headers, HttpStatus.OK);

    }

}

