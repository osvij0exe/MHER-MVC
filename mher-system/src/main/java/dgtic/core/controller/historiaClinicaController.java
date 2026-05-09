package dgtic.core.controller;

import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.HistoriaClinica;
import dgtic.core.model.dto.Request.DoctorRequest;
import dgtic.core.model.dto.Request.HistoriaClinicaRequest;
import dgtic.core.model.dto.Request.RecetaRequest;
import dgtic.core.model.dto.Response.EspecialidadResponse;
import dgtic.core.model.dto.Response.HistoriaClinicaResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
import dgtic.core.model.dto.Response.RecetaResponse;
import dgtic.core.service.doctorService.IDoctorService;
import dgtic.core.service.especialidadService.IEspecialidadService;
import dgtic.core.service.historiaClinicaService.IHistoriaClinicaService;
import dgtic.core.service.pacienteService.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("historiasClinicas")
public class historiaClinicaController {
    @Autowired
    IHistoriaClinicaService historiaClinicaService;

    @Autowired
    IPacienteService pacienteService;

    @Autowired
    IEspecialidadService especialidadService;

    @Autowired
    IDoctorService doctorService;

    @GetMapping("agregar-historia-clinica/paciente/{pacienteId}")
    public String historiaClinicaVista(@PathVariable("pacienteId")Integer pacienteId,Model model)
    {
        PacienteResponse paciente = pacienteService.findById(pacienteId);
        HistoriaClinicaRequest historia = new HistoriaClinicaRequest();
        historia.setPaciente(paciente);
        List<EspecialidadResponse> especialidades = especialidadService.findAll();

        model.addAttribute("contenido","Agregar nueva Receta");

        model.addAttribute("historia",historia);
        model.addAttribute("especialidades",especialidades);
        model.addAttribute("doctores",List.of());

        return "historiasClinicas/agregar-historia-clinica";

    }

    @PostMapping("guardar-historia-clinica/paciente/{pacienteId}")
    public String guardarHistoria(
            @PathVariable Integer pacienteId,
            @ModelAttribute("historia")HistoriaClinicaRequest historiaClinicaRequest,
            BindingResult bindingResult,
            @RequestParam(value= "especialidadChanged",defaultValue = "false") boolean especialidadChanged,
            Model model) {
        model.addAttribute(("especialidades"), especialidadService.findAll());

        historiaClinicaRequest.setPaciente(pacienteService.findById(pacienteId));

        if (historiaClinicaRequest.getEspecialidad() != null &&
                historiaClinicaRequest.getEspecialidad().getEspecialidadId() != null) {
            model.addAttribute("doctores",
                    doctorService.findDoctorByEspecialidad(
                            historiaClinicaRequest.getEspecialidad().getEspecialidadId()
                    ));
            if (especialidadChanged) {
                historiaClinicaRequest.setDoctor(null);
                model.addAttribute("warning", "Selecciona un doctor");
                return "historiasClinicas/agregar-historia-clinica";
            }
        } else{
            model.addAttribute("doctores", List.of());
            model.addAttribute("warning", "Seleciona una especialidad");
            return "historiasClinicas/agregar-historia-clinica";
        }

        if (historiaClinicaRequest.getDoctor() == null ||
                historiaClinicaRequest.getDoctor().getDoctorId() == null) {
            model.addAttribute("warning", "Selecciona un doctor");
            return "historiasClinicas/agregar-historia-clinica";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("warning", "Corrige los errores del formulario");
            return "historiasClinicas/agregar-historia-clinica";
        }
        historiaClinicaService.save(historiaClinicaRequest);
        return "redirect:/pacientes/detalle-historia-clinica-paciente/" + pacienteId;

    }


    @GetMapping("modificar-historia-clinica/paciente/{pacienteId}/historia/{Id}")
    public String modificarHistoria(@PathVariable("pacienteId")Integer pacienteId,
                                       @PathVariable("Id")Integer historiaId
            ,Model model) {
        HistoriaClinicaResponse historia = historiaClinicaService.findHistoriaById(historiaId);


        model.addAttribute("contenido", "Modificar Receta");

        model.addAttribute("historia",historia);
        model.addAttribute("especialidades",especialidadService.findAll());

        if(historia.getEspecialidad() != null
                && historia.getEspecialidad().getEspecialidadId() != null)
        {
            model.addAttribute("doctores",
                    doctorService.findDoctorByEspecialidad(
                            historia.getEspecialidad().getEspecialidadId()
                    ));
        }else {
            model.addAttribute("doctores", List.of());
        }

        return "historiasClinicas/modificar-historia-clinica";

    }

    @PostMapping("modificar-historia-clinica/paciente/{pacienteId}/historia/{Id}")
    public String guardarModificacionhistoria(
            @PathVariable Integer pacienteId,
            @PathVariable Integer Id,
            @ModelAttribute("historia")HistoriaClinicaRequest historiaClinicaRequest,
            BindingResult bindingResult,
            @RequestParam(value= "especialidadChanged",defaultValue = "false") boolean especialidadChanged,
            Model model) {
        model.addAttribute(("especialidades"), especialidadService.findAll());

        historiaClinicaRequest.setPaciente(pacienteService.findById(pacienteId));

        if (historiaClinicaRequest.getEspecialidad() != null &&
                historiaClinicaRequest.getEspecialidad().getEspecialidadId() != null) {
            model.addAttribute("doctores",
                    doctorService.findDoctorByEspecialidad(
                            historiaClinicaRequest.getEspecialidad().getEspecialidadId()
                    ));
            if (especialidadChanged) {
                historiaClinicaRequest.setDoctor(null);
                model.addAttribute("warning", "Selecciona un doctor");
                return "historiasClinicas/modificar-historia-clinica";
            }
        } else{
            model.addAttribute("doctores", List.of());
            model.addAttribute("warning", "Seleciona una especialidad");
            return "historiasClinicas/modificar-historia-clinica";
        }

        if (historiaClinicaRequest.getDoctor() == null ||
                historiaClinicaRequest.getDoctor().getDoctorId() == null) {
            model.addAttribute("warning", "Selecciona un doctor");
            return "historiasClinicas/modificar-historia-clinica";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("warning", "Corrige los errores del formulario");
            return "historiasClinicas/modificar-historia-clinica";
        }

        historiaClinicaRequest.setId(Id);
        historiaClinicaService.update(historiaClinicaRequest);
        return "redirect:/pacientes/detalle-historia-clinica-paciente/" + pacienteId;

    }







    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable Integer id) {

        HistoriaClinica historia =
                historiaClinicaService.findById(id);

        byte[] pdf = historiaClinicaService.generarPdf(historia);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("historia-clinica-" + id + ".pdf")
                        .build()
        );

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }


}
