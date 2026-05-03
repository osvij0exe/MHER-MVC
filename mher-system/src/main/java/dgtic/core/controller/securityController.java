package dgtic.core.controller;

import dgtic.core.auth.dto.UserInfoDTO;
import dgtic.core.auth.dto.UserInfoRoleDTO;
import dgtic.core.auth.exception.UserInfoNotFoundException;
import dgtic.core.auth.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("security")
public class securityController {

    @Autowired
    IUserInfoService userInfoService;

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
        model.addAttribute("user", new UserInfoDTO());
        return "security/signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(UserInfoDTO user) throws UserInfoNotFoundException {
        user.setUseIdStatus(1);
        Set<UserInfoRoleDTO> roles = new HashSet<>();
        roles.add(UserInfoRoleDTO.builder().usrId(1L).build());
        user.setUseInfoRoles(roles);
        user.setUseCreatedBy(1L);
        user.setUseModifiedBy(1L);
        userInfoService.save(user);
        return "redirect:/principal";
    }



}
