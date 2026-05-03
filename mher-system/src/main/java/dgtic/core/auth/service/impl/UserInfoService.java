package dgtic.core.auth.service.impl;

import dgtic.core.auth.dto.UserInfoDTO;
import dgtic.core.auth.dto.UserInfoRoleDTO;
import dgtic.core.auth.dto.mappers.UserInfoDTOMapper;
import dgtic.core.auth.exception.UserInfoNotFoundException;
import dgtic.core.auth.model.UserInfo;
import dgtic.core.auth.model.UserInfoRole;
import dgtic.core.auth.repository.UserInfoRepository;
import dgtic.core.auth.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserInfoService implements IUserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoRoleService userInfoRoleService; //inyectamos el servicio!!! (DIP)

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository,
                           PasswordEncoder passwordEncoder,
                           UserInfoRoleService userInfoRoleService) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInfoRoleService = userInfoRoleService;
    }

    @Override
    public List<UserInfoDTO> findAll() {
        log.info("Service - UserInfoServiceImpl.findAll");
        List<UserInfo> theList = userInfoRepository.findAllByOrderByUseIdAsc();
        return theList.stream().map(UserInfoDTOMapper::toDTO).toList();
    }

    @Override
    public UserInfoDTO findById(Long id) throws UserInfoNotFoundException {
        log.info("Service - UserAdmin.findById {}", id);
        UserInfo object = userInfoRepository.findById(id).orElseThrow(() ->
                new UserInfoNotFoundException(id));
        return UserInfoDTOMapper.toDTO(object);
    }

    @Override
    public UserInfoDTO save(UserInfoDTO userAdmin) throws UserInfoNotFoundException {
        log.info("Service - UserAdmin.save");
        log.info("Service - UserAdmin.save object {} ", userAdmin);
        if (existsByUseEmail(userAdmin.getUseEmail()))
            throw new UserInfoNotFoundException(userAdmin.getUseEmail());
        userAdmin.setUsePasswd(passwordEncoder.encode(userAdmin.getUsePasswd()));
        UserInfo finalStatus = UserInfoDTOMapper.toEntity(userAdmin);
        finalStatus = userInfoRepository.save(finalStatus);
        return UserInfoDTOMapper.toDTO(finalStatus);
    }

    @Override
    public UserInfoDTO findByUseEmail(String email) throws UserInfoNotFoundException {
        UserInfo object = userInfoRepository.findByUseEmail(email);
        if(object == null)
            throw new UserInfoNotFoundException(email);
        return UserInfoDTOMapper.toDTO(object);
    }

    private boolean existsByUseEmail(String email){
        return userInfoRepository.existsByUseEmail(email);
    }

    private UserInfoDTO convertEntityToDTO(UserInfo userInfo) {
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
            userInfoRoles.add(userInfoRoleService.convertEntityToDTO(role));
        }
        dto.setUseInfoRoles(userInfoRoles);
        /*dto.setUseInfoRoles(userInfo.getUseInfoRoles()
                .stream()
                .map(userInfoRoleService::convertEntityToDTO)
                .collect(Collectors.toSet()));*/
        return dto;
    }

    private UserInfo convertDTOtoEntity(UserInfoDTO userInfo) {
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
            userInfoRoles.add(userInfoRoleService.convertDTOtoEntity(role));
        }
        entity.setUseInfoRoles(userInfoRoles);
        /*entity.setUseInfoRoles(userInfo.getUseInfoRoles()
                .stream()
                .map(userInfoRoleService::convertDTOtoEntity)
                .collect(Collectors.toSet()));*/
        return entity;
    }
}
