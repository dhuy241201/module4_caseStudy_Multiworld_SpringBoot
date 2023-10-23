package com.CaseStudy.Multiworld.convert.itf;

import com.CaseStudy.Multiworld.convert.IConvertEtoD;
import com.CaseStudy.Multiworld.dto.response.UserForFindUserByIdResponseDTO;
import com.CaseStudy.Multiworld.entity.User.User;

public interface IFindUserForOtherUsersConvert extends IConvertEtoD<User, UserForFindUserByIdResponseDTO> {
}
