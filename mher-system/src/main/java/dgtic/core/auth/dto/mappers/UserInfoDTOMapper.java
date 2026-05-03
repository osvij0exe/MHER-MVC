package dgtic.core.auth.dto.mappers;

import dgtic.core.auth.dto.UserInfoDTO;
import dgtic.core.auth.dto.UserInfoRoleDTO;
import dgtic.core.auth.model.UserInfo;
import dgtic.core.auth.model.UserInfoRole;

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
        /*dto.setUseInfoRoles(userInfo.getUseInfoRoles()
                .stream()
                .map(userInfoRoleService::convertEntityToDTO)
                .collect(Collectors.toSet()));*/
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
        /*entity.setUseInfoRoles(userInfo.getUseInfoRoles()
                .stream()
                .map(userInfoRoleService::convertDTOtoEntity)
                .collect(Collectors.toSet()));*/
        return entity;
    }
}
