package dgtic.core.auth.service;

import dgtic.core.auth.dto.UserInfoRoleDTO;
import dgtic.core.auth.exception.UserInfoNotFoundException;
import dgtic.core.auth.exception.UserInfoRoleNotFoundException;
import dgtic.core.auth.model.UserInfoRole;
import java.util.List;

public interface IUserInfoRoleService {
    List<UserInfoRoleDTO> findAll();
    List<UserInfoRoleDTO> findAllOrderByUsrRoleName();
    UserInfoRoleDTO findById(Long id) throws UserInfoRoleNotFoundException;
    UserInfoRoleDTO save(UserInfoRoleDTO role);
    UserInfoRoleDTO convertEntityToDTO(UserInfoRole userInfo);
    UserInfoRole convertDTOtoEntity(UserInfoRoleDTO userInfo);
}
