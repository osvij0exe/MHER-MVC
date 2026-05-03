package dgtic.core.controller;

import dgtic.core.model.dto.Response.CitasPacienteResponse;
import dgtic.core.model.dto.Response.CitasResponse;
import dgtic.core.service.doctorService.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("doctores")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("lista-pacientes-doctor/{doctorId}")
    public  String listaPacientesDoctor(
            @PathVariable Integer doctorId,
//            @PathVariable Long userId,
            Model model){

//        List<CitasResponse> citas = doctorService.findDoctorsPatientsByDoctorId(doctorId,userId);
        List<CitasResponse> citas = doctorService.findDoctorsPatientsByDoctorId(doctorId);

        model.addAttribute("citas",citas);
        model.addAttribute("contenido","Lista de pacientes del doctor");

        return "doctores/lista-pacientes-doctor";
    }

}
