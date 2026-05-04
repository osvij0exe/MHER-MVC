package dgtic.core.controller;

import dgtic.core.model.dto.Response.CitasResponse;
import dgtic.core.model.dto.Response.DoctorUserResponse;
import dgtic.core.service.doctorService.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("doctores")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("lista-pacientes-doctor")
    public  String listaPacientesDoctor(
            Model model,
            Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        DoctorUserResponse doctor = doctorService.findByDoctorUserEmail(userDetails.getUsername());

        List<CitasResponse> citas = doctorService.findDoctorsPatientsByDoctorId(doctor.getDoctorId(),doctor.getEmail());

        model.addAttribute("citas",citas);
        model.addAttribute("contenido","Lista de pacientes del doctor");

        return "doctores/lista-pacientes-doctor";
    }

}
