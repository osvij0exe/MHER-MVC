package dgtic.core.auth.service;



import dgtic.core.auth.dto.UserInfoDTO;
import dgtic.core.auth.exception.UserInfoNotFoundException;

import java.util.List;

public interface IUserInfoService {
    List<UserInfoDTO> findAll();
    UserInfoDTO findById(Long id) throws UserInfoNotFoundException;
    UserInfoDTO save(UserInfoDTO userAdmin) throws UserInfoNotFoundException;
    UserInfoDTO findByUseEmail(String email) throws UserInfoNotFoundException;
}
