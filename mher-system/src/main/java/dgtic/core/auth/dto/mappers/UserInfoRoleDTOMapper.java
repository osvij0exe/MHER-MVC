package dgtic.core.auth.dto.mappers;

import dgtic.core.auth.dto.UserInfoRoleDTO;
import dgtic.core.auth.model.UserInfoRole;

public class UserInfoRoleDTOMapper {

    public static UserInfoRoleDTO toDTO(UserInfoRole userInfo) {
        UserInfoRoleDTO dto = new UserInfoRoleDTO();
        dto.setUsrId(userInfo.getUsrId());
        dto.setUsrRoleName(userInfo.getUsrRoleName());
        dto.setUsrIdStatus(userInfo.getUsrIdStatus());
        dto.setUsrCreatedBy(userInfo.getUsrCreatedBy());
        dto.setUsrCreatedDate(userInfo.getUsrCreatedDate());
        dto.setUsrModifiedBy(userInfo.getUsrModifiedBy());
        dto.setUsrModifiedDate(userInfo.getUsrModifiedDate());
        return dto;
    }

    public static UserInfoRole toEntity(UserInfoRoleDTO userInfo) {
        UserInfoRole entity = new UserInfoRole();
        entity.setUsrId(userInfo.getUsrId());
        entity.setUsrRoleName(userInfo.getUsrRoleName());
        entity.setUsrIdStatus(userInfo.getUsrIdStatus());
        entity.setUsrCreatedBy(userInfo.getUsrCreatedBy());
        entity.setUsrCreatedDate(userInfo.getUsrCreatedDate());
        entity.setUsrModifiedBy(userInfo.getUsrModifiedBy());
        entity.setUsrModifiedDate(userInfo.getUsrModifiedDate());
        return entity;
    }
}
