package dgtic.core.controller;

import dgtic.core.model.dto.Request.CitasRequest;
import dgtic.core.model.dto.Response.CitasResponse;
import dgtic.core.model.dto.Response.EspecialidadResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
import dgtic.core.service.citaServices.CitaService;
import dgtic.core.service.doctorService.DoctorService;
import dgtic.core.service.especialidadService.EspecialidadService;
import dgtic.core.service.pacienteService.PacienteService;
import dgtic.core.util.RenderPagina;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("citas")
public class citaController {

    @Autowired
    CitaService citaService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    EspecialidadService especialidadService;

    @GetMapping("lista-citas")
    public String listaProductos(
            @RequestParam(name="page",defaultValue = "0")int page,
            Model model,
            @RequestParam(required = false)String search){
        Pageable pageable= PageRequest.of(page,5);
        Page<CitasResponse> citasResposne =citaService.findByPage(pageable,search);
        RenderPagina<CitasResponse> renderPagina=
                new RenderPagina<>("lista-citas",citasResposne);
        model.addAttribute("citas",citasResposne);
        model.addAttribute("page",renderPagina);
        model.addAttribute("search",search);
        model.addAttribute("contenido","Lista de Citas");

        return "citas/lista-citas";
    }

    @GetMapping("agregar-cita/{pacienteId}")
    public String verAlta(@PathVariable("pacienteId") Integer pacienteId, Model model){
        model.addAttribute("contenido","Agregar Cita");

        PacienteResponse paciente =  pacienteService.findById(pacienteId);
        CitasRequest citasRequest = new CitasRequest();
        List<EspecialidadResponse> especialidades = especialidadService.findAll();
        citasRequest.setPaciente(paciente);
        model.addAttribute("citaRequest",citasRequest);
        model.addAttribute("especialidades",especialidades);
        model.addAttribute("doctores", List.of());

        return "citas/agregar-cita";
    }


    @PostMapping("guardar-cita/{pacienteId}")
    public String CitaRequest(
            @PathVariable Integer pacienteId,
            @Valid  @ModelAttribute("citaRequest") CitasRequest citasRequest,
            BindingResult bindingResult,
            @RequestParam(value= "especialidadChanged",defaultValue = "false") boolean especialidadChanged,
            Model model){

        model.addAttribute("especialidades", especialidadService.findAll());
        citasRequest.setPaciente(pacienteService.findById(pacienteId));

        if(citasRequest.getEspecialidad() != null &&
                citasRequest.getEspecialidad().getEspecialidadId() != null){

            model.addAttribute("doctores",
                    doctorService.findDoctorByEspecialidad(
                            citasRequest.getEspecialidad().getEspecialidadId()));

            if(especialidadChanged){
                citasRequest.setDoctor(null);
                model.addAttribute("warning","Selecciona un doctor");
                return "citas/agregar-cita";
            }

        } else {
            model.addAttribute("doctores", List.of());
            model.addAttribute("warning","Selecciona una especialidad");
            return "citas/agregar-cita";
        }

        if (citasRequest.getDoctor() == null ||
                citasRequest.getDoctor().getDoctorId() == null) {

            model.addAttribute("warning","Selecciona un doctor");
            return "citas/agregar-cita";
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("warning","Corrige los errores del formulario");
            return "citas/agregar-cita";
        }


        citaService.save(citasRequest);

        return "redirect:/pacientes/detalle-paciente/" + pacienteId;
    }

    @GetMapping("modificar-cita/paciente/{pacienteId}/cita/{citaId}")
    public String modificarCita(
            @PathVariable("pacienteId") Integer pacienteId,
            @PathVariable("citaId") Integer citaId,
            Model model) {

        CitasResponse citasResponse = citaService.findById(citaId);

        model.addAttribute("contenido", "Modificar cita");
        model.addAttribute("citaRequest", citasResponse);


        model.addAttribute("especialidades", especialidadService.findAll());


        if (citasResponse.getEspecialidad() != null) {
            model.addAttribute("doctores",
                    doctorService.findDoctorByEspecialidad(
                            citasResponse.getEspecialidad().getEspecialidadId()
                    ));
        } else {
            model.addAttribute("doctores", List.of());
        }

        return "citas/modificar-cita";
    }

    @PostMapping("modificar-cita/paciente/{pacienteId}/cita/{citaId}")
    public String guardarModificacion(
            @PathVariable Integer pacienteId,
            @PathVariable Integer citaId,
            @Valid @ModelAttribute("citaRequest") CitasRequest citasRequest,
            BindingResult bindingResult,
            Model model) {

        model.addAttribute("especialidades", especialidadService.findAll());
        citasRequest.setPaciente(pacienteService.findById(pacienteId));
        citasRequest.setCitaId(citaId);


        if (citasRequest.getEspecialidad() != null &&
                citasRequest.getEspecialidad().getEspecialidadId() != null) {
            model.addAttribute("doctores",
                    doctorService.findDoctorByEspecialidad(
                            citasRequest.getEspecialidad().getEspecialidadId()));
        } else {
            model.addAttribute("doctores", List.of());
            model.addAttribute("warning", "Selecciona una especialidad");
            return "citas/modificar-cita";
        }

        if (citasRequest.getDoctor() == null ||
                citasRequest.getDoctor().getDoctorId() == null) {
            model.addAttribute("warning", "Selecciona un doctor");
            return "citas/modificar-cita";
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("warning","Corrige los errores del formulario");
            return "citas/modificar-cita";
        }


        citaService.update(citasRequest);
        return "redirect:/pacientes/detalle-paciente/" + pacienteId;
    }


    @GetMapping("eliminar-cita/paciente/{pacienteId}/cita/{citaId}")
    public String eliminarCita(
            @PathVariable("pacienteId") Integer pacienteId,
            @PathVariable("citaId") Integer citaId,
            Model model) {

        CitasResponse citasResponse = citaService.findByIdWithRelations(citaId);

        model.addAttribute("contenido", "Eliminar cita");
        model.addAttribute("citaRequest", citasResponse);


        return "citas/eliminar-cita";
    }


    @PostMapping("eliminar-cita/paciente/{pacienteId}/cita/{citaId}")
    public String eliminarCitaDePaciente(
            @PathVariable("pacienteId") Integer pacienteId,
            @PathVariable("citaId") Integer citaId,
            Model model) {

        CitasResponse citasResponse = citaService.findByIdWithRelations(citaId);

        model.addAttribute("contenido", "Eliminar cita");
        model.addAttribute("citaRequest", citasResponse);

        citaService.deleteById(citasResponse.getCitaId());

        return "redirect:/pacientes/detalle-paciente/" + pacienteId;
    }



}
