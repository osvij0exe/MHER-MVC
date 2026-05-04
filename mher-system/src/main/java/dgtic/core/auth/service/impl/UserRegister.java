package dgtic.core.auth.service.impl;

import dgtic.core.auth.dto.DoctorUserRegister;
import dgtic.core.auth.dto.mappers.UserInfoDTOMapper;
import dgtic.core.auth.exception.UserInfoNotFoundException;
import dgtic.core.auth.model.UserInfo;
import dgtic.core.auth.securityRepository.UserInfoRepository;
import dgtic.core.auth.service.IUserRegister;
import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.dto.Mappers.DoctorMapper;
import dgtic.core.model.dto.Response.DoctorUserResponse;
import dgtic.core.repository.doctores.IDoctorRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class UserRegister implements IUserRegister {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoRoleService userInfoRoleService;
    private final IDoctorRepository doctorRepository;

    @Autowired
    public UserRegister(UserInfoRepository userInfoRepository,
                           PasswordEncoder passwordEncoder,
                           UserInfoRoleService userInfoRoleService,
                            IDoctorRepository doctorRepository) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInfoRoleService = userInfoRoleService;
        this.doctorRepository  = doctorRepository;
    }



    @Override
    public DoctorUserResponse registerDoctor(DoctorUserRegister register) throws UserInfoNotFoundException {

        register.getUser().setUsePasswd(
                passwordEncoder.encode(
                        register.getUser().getUsePasswd()
                )
        );

        Doctor doctor =
                UserInfoDTOMapper.ToDoctorUserEntity(register);


        UserInfo savedUser =
                userInfoRepository.save(doctor.getUser());


        doctor.setUser(savedUser);


        Doctor savedDoctor =
                doctorRepository.save(doctor);

        return DoctorMapper.ToDoctorsUserDto(savedDoctor);
    }
}
