package dgtic.core.auth.service;

import dgtic.core.auth.dto.DoctorUserRegister;
import dgtic.core.auth.exception.UserInfoNotFoundException;
import dgtic.core.model.dto.Response.DoctorUserResponse;

public interface IUserRegister {
    DoctorUserResponse registerDoctor(DoctorUserRegister doctorUserResponse) throws UserInfoNotFoundException;
}
