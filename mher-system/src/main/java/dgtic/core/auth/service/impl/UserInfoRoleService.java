package dgtic.core.auth.service.impl;

import dgtic.core.auth.dto.UserInfoRoleDTO;
import dgtic.core.auth.dto.mappers.UserInfoRoleDTOMapper;
import dgtic.core.auth.exception.UserInfoRoleNotFoundException;
import dgtic.core.auth.model.UserInfoRole;
import dgtic.core.auth.securityRepository.UserInfoRoleRepository;
import dgtic.core.auth.service.IUserInfoRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserInfoRoleService implements IUserInfoRoleService {
    private final UserInfoRoleRepository userInfoRoleRepository;

    @Autowired
    public UserInfoRoleService(UserInfoRoleRepository userInfoRoleRepository) {
        this.userInfoRoleRepository = userInfoRoleRepository;
    }

    @Override
    public List<UserInfoRoleDTO> findAll() {
        log.info("Service - UserInfoRoleServiceImpl.findAll");
        List<UserInfoRole> theList = userInfoRoleRepository.findAllByOrderByUsrIdAsc();
        return theList.stream().map(UserInfoRoleDTOMapper::toDTO).toList();
    }

    @Override
    public List<UserInfoRoleDTO> findAllOrderByUsrRoleName() {
        log.info("Service - UserInfoRoleServiceImpl.findAll");
        List<UserInfoRole> theList = userInfoRoleRepository.findAllByOrderByUsrRoleNameAsc();
        return theList.stream().map(UserInfoRoleDTOMapper::toDTO).toList();
    }

    @Override
    public UserInfoRoleDTO findById(Long id) throws UserInfoRoleNotFoundException {
        log.info("Service - UserInfoRoleServiceImpl.findById {}", id);
        UserInfoRole object = userInfoRoleRepository.findById(id).orElseThrow(() ->
                new UserInfoRoleNotFoundException(id));
        return UserInfoRoleDTOMapper.toDTO(object);
    }

    @Override
    public UserInfoRoleDTO save(UserInfoRoleDTO role) {
        log.info("Service - UserInfoRoleServiceImpl.save object {} ", role);
        UserInfoRole finalStatus = UserInfoRoleDTOMapper.toEntity(role);
        finalStatus = userInfoRoleRepository.save(finalStatus);
        return convertEntityToDTO(finalStatus);
    }

    public UserInfoRoleDTO convertEntityToDTO(UserInfoRole userInfo) {
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

    public UserInfoRole convertDTOtoEntity(UserInfoRoleDTO userInfo) {
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
