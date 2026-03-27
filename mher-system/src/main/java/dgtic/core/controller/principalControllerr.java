package dgtic.core.controller;

import dgtic.core.model.Entities.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "views")
public class principalControllerr {
    @GetMapping("pacientes")
    public String getPacientes(Model model)
    {
        model.addAttribute("contenido","Lista de pacientes");
        return "views/pacientes";

    }

    @GetMapping("doctores")
    public String getDoctores(Model model)
    {
        model.addAttribute("contenido","Lista de doctores");
        return "views/doctores";
    }

    @GetMapping("citas")
    public String getCitas(Model model)
    {
        model.addAttribute("contenido","Lista de citas");
        return "views/citas";
    }

    @GetMapping("especialidades")
    public String getEspecialidades(Model model)
    {
        model.addAttribute("contenido","Lista de especialidades");
        return "views/especialidades";
    }

}
