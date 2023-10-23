package com.CaseStudy.Multiworld.service;


import com.CaseStudy.Multiworld.dto.request.LoginRequestDTO;
import com.CaseStudy.Multiworld.dto.response.ProfileResponseDTO;
import com.CaseStudy.Multiworld.dto.request.RegisterRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateUserRequestDTO;
import com.CaseStudy.Multiworld.dto.response.SearchUserResponseDTO;
import com.CaseStudy.Multiworld.dto.response.UserForFindUserByIdResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    String register(RegisterRequestDTO registerRequestDto);

    String login(LoginRequestDTO loginRequestDto);

    ProfileResponseDTO findUserForProfile(String username);

    String updateUser(UpdateUserRequestDTO updateUserRequestDto, MultipartFile avatar);

    Page<SearchUserResponseDTO> searchUser(String keyword, int page);

    UserForFindUserByIdResponseDTO findUserById(Long userId);
}
