package dgtic.core.controller;

import dgtic.core.model.dto.Request.PacienteRequest;
import dgtic.core.model.dto.Response.PacienteCitasResponse;
import dgtic.core.model.dto.Response.PacienteHistoriasClinicasResponse;
import dgtic.core.model.dto.Response.PacienteRecetasResponse;
import dgtic.core.model.dto.Response.PacienteResponse;
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

@Controller
@RequestMapping("pacientes")
public class pacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("lista-pacientes")
    public String listaPaciente(
            @RequestParam(name="page",defaultValue = "0")int page,
            Model model,
            @RequestParam(required = false)String search){
        Pageable pageable= PageRequest.of(page,5);
        Page<PacienteResponse> pacienteResposne =pacienteService.findByPage(pageable,search);
        RenderPagina<PacienteResponse> renderPagina=
                new RenderPagina<>("lista-pacientes",pacienteResposne);
        model.addAttribute("pacientes",pacienteResposne);
        model.addAttribute("page",renderPagina);
        model.addAttribute("search",search);
        model.addAttribute("contenido","Lista de pacientes");

        return "pacientes/lista-pacientes";
    }

    @GetMapping("detalle-paciente/{pacienteId}")
    public String DetallePaciente(@PathVariable("pacienteId") Integer pacienteId,Model modelo){
        PacienteCitasResponse pacienteResponse = pacienteService.findPacienteCitasById(pacienteId);
        modelo.addAttribute("paciente",pacienteResponse);
        modelo.addAttribute("contenido","Detalles del paciente");
        return "pacientes/detalle-paciente";
    }

    @GetMapping("detalle-historia-clinica-paciente/{pacienteId}")
    public String DetalleHistoriaClinicaPaciente(@PathVariable("pacienteId")Integer pacienteId,Model model)
    {
        PacienteHistoriasClinicasResponse response = pacienteService.findPacienteHistoriasClinicasById(pacienteId);
        model.addAttribute("paciente",response);
        model.addAttribute("contenido","Historias clinicas del paciente");
        return "pacientes/detalle-historia-clinica-paciente";
    }

    @GetMapping("detalle-recetas-paciente/{pacienteId}")
    public String DetalleRecetasPaciente(@PathVariable("pacienteId")Integer pacienteId,Model model)
    {
        PacienteRecetasResponse response = pacienteService.findPacienteRecetasById(pacienteId);
        model.addAttribute("paciente",response);
        model.addAttribute("contenido","Recetas del paciente");
        return  "pacientes/detalle-recetas-paciente";

    }


    @GetMapping("agregar-paciente")
    public String verAlta(Model model){
        model.addAttribute("contenido","Agregar Paciente");
        model.addAttribute("datos",new PacienteRequest());
        return "pacientes/agregar-paciente";
    }

    @GetMapping("modificar-paciente/{pacienteId}")
    public String modificarPaciente(@PathVariable("pacienteId") Integer pacienteId,Model modelo){
        PacienteResponse pacienteResponse =pacienteService.findById(pacienteId);
        modelo.addAttribute("paciente",pacienteResponse);
        modelo.addAttribute("contenido","Modificar paciente");
        return "pacientes/modificar-paciente";
    }


    @PostMapping(value = "salvar-paciente")
    public String guardarPaciente(@RequestParam(required = false)Integer pacienteId,
                                  @Valid @ModelAttribute("datos") PacienteRequest pacienteRequest,
                                  BindingResult bindingResult,
                                  Model model) {

        if(bindingResult.hasErrors())
        {
            model.addAttribute("contenido", "Agregar Paciente");
            model.addAttribute("datos", pacienteRequest);
            return "pacientes/agregar-paciente";
        }


        if(pacienteId != null)
        {
            pacienteService.save(pacienteRequest);
            model.addAttribute("datos",null);
            return "redirect:/pacientes/lista-pacientes";
        }


        model.addAttribute("alerts", "Se almaceno con éxito");
        model.addAttribute("contenido", "Alta Paciente");

            pacienteService.save(pacienteRequest);
            model.addAttribute("datos", new PacienteRequest());
            return "redirect:/pacientes/lista-pacientes";

    }







}
