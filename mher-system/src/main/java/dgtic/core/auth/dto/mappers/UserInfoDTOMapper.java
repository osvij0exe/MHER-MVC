package dgtic.core.auth.dto.mappers;

import dgtic.core.auth.dto.DoctorUserRegister;
import dgtic.core.auth.dto.UserInfoDTO;
import dgtic.core.auth.dto.UserInfoRoleDTO;
import dgtic.core.auth.model.UserInfo;
import dgtic.core.auth.model.UserInfoRole;
import dgtic.core.model.Entities.Doctor;
import dgtic.core.model.Entities.Especialidad;
import dgtic.core.model.dto.Mappers.DoctorMapper;
import dgtic.core.model.dto.Mappers.EspecialidadMapper;
import dgtic.core.model.dto.Response.EspecialidadResponse;

import java.util.HashSet;
import java.util.Set;

public class UserInfoDTOMapper {

    public static UserInfoDTO toDTO(UserInfo userInfo) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setUseId(userInfo.getUseId());
        dto.setUseFirstName(userInfo.getUseFirstName());
        dto.setUseLastName(userInfo.getUseLastName());
        dto.setUseEmail(userInfo.getUseEmail());
        dto.setUsePasswd(userInfo.getUsePasswd());
        dto.setUseIdStatus(userInfo.getUseIdStatus());
        dto.setUseCreatedBy(userInfo.getUseCreatedBy());
        dto.setUseCreatedDate(userInfo.getUseCreatedDate());
        dto.setUseModifiedBy(userInfo.getUseModifiedBy());
        dto.setUseModifiedDate(userInfo.getUseModifiedDate());
        Set<UserInfoRoleDTO> userInfoRoles = new HashSet<>();
        for (UserInfoRole role : userInfo.getUseInfoRoles()) {
            userInfoRoles.add(UserInfoRoleDTOMapper.toDTO(role));
        }
        dto.setUseInfoRoles(userInfoRoles);
        return dto;
    }

    public static UserInfo toEntity(UserInfoDTO userInfo) {
        UserInfo entity = new UserInfo();
        entity.setUseId(userInfo.getUseId());
        entity.setUseFirstName(userInfo.getUseFirstName());
        entity.setUseLastName(userInfo.getUseLastName());
        entity.setUseEmail(userInfo.getUseEmail());
        entity.setUsePasswd(userInfo.getUsePasswd());
        entity.setUseIdStatus(userInfo.getUseIdStatus());
        entity.setUseCreatedBy(userInfo.getUseCreatedBy());
        entity.setUseCreatedDate(userInfo.getUseCreatedDate());
        entity.setUseModifiedBy(userInfo.getUseModifiedBy());
        entity.setUseModifiedDate(userInfo.getUseModifiedDate());
        Set<UserInfoRole> userInfoRoles = new HashSet<>();
        for (UserInfoRoleDTO role : userInfo.getUseInfoRoles()) {
            userInfoRoles.add(UserInfoRoleDTOMapper.toEntity(role));
        }
        entity.setUseInfoRoles(userInfoRoles);
        return entity;
    }

    public static Doctor ToDoctorUserEntity(DoctorUserRegister register)
    {
        Doctor doctor = new Doctor();

        doctor.setNombre(register.getDoctor().getNombre());
        doctor.setApellidos(register.getDoctor().getApellido());
        doctor.setGenero(register.getDoctor().getGenero());
        doctor.setCedulaProfesional(register.getDoctor().getCedulaProfesional());
        doctor.setActive(true);

        EspecialidadResponse especialidadResponse = new EspecialidadResponse();
        especialidadResponse.setEspecialidadId(
                register.getDoctor().getEspecialidadId()
        );

        Especialidad especialidad =
                EspecialidadMapper.ToEntity(especialidadResponse);

        doctor.setEspecialidad(especialidad);

        register.getUser().setUseFirstName(doctor.getNombre());
        register.getUser().setUseLastName(doctor.getApellidos());


        UserInfo user =
                UserInfoDTOMapper.toEntity(register.getUser());

        doctor.setUser(user);

        return doctor;
    }
}
