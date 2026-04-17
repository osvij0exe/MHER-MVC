package dgtic.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class inicioController {

    @Value("${mensaje.application}")
    private String valor;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String inicio(){
        return "inicio";
    }

    @RequestMapping(value = "modelo",method = RequestMethod.GET)
    public String modelo(Model modelo){
        modelo.addAttribute("mensaje","Diplomado de Java con Spring");
        return "inicio";
    }

    @RequestMapping(value = "propiedad",method = RequestMethod.GET)
    public String propiedad(Model modelo){
        modelo.addAttribute("mensaje","Diplomado de Java con Spring");
        modelo.addAttribute("propiedad",valor);
        return "inicio";
    }

    @RequestMapping(value = "principal",method = RequestMethod.GET)
    public String salto(Model modelo){
        modelo.addAttribute("contenido","Principal");
        return "principal/principal";
    }
}

