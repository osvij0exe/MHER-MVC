package dgtic.core.controller;

import dgtic.core.auth.dto.DoctorUserRegister;
import dgtic.core.auth.dto.UserInfoDTO;
import dgtic.core.auth.dto.UserInfoRoleDTO;
import dgtic.core.auth.exception.UserInfoNotFoundException;
import dgtic.core.auth.service.IUserInfoService;
import dgtic.core.auth.service.IUserRegister;
import dgtic.core.model.dto.Response.EspecialidadResponse;
import dgtic.core.service.doctorService.IDoctorService;
import dgtic.core.service.especialidadService.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("security")
public class securityController {

    @Autowired
    IUserInfoService userInfoService;

    @Autowired
    IUserRegister userRegister;

    @Autowired
    IEspecialidadService especialidadService;


    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        System.out.println("Logging user login success...");
        return "redirect:/principal";
    }

    @PostMapping("/login_failure_handler")
    public String loginFailureHandler() {
        System.out.println("Login failure handler....");
        return "login";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        List<EspecialidadResponse> especialidades = especialidadService.findAll();
        model.addAttribute("especialidades",especialidades);
        model.addAttribute("user", new DoctorUserRegister());
        return "security/signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(DoctorUserRegister doctorUser) throws UserInfoNotFoundException {
        doctorUser.getUser().setUseIdStatus(1);
        Set<UserInfoRoleDTO> roles = new HashSet<>();
        roles.add(UserInfoRoleDTO.builder().usrId(2L).build());
        doctorUser.getUser().setUseInfoRoles(roles);
        doctorUser.getUser().setUseCreatedBy(1L);
        doctorUser.getUser().setUseModifiedBy(1L);


        userRegister.registerDoctor(doctorUser);
        return "redirect:/principal";
    }



}
