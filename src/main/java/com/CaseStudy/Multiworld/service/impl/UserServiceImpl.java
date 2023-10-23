package com.CaseStudy.Multiworld.service.impl;

import com.CaseStudy.Multiworld.convert.impl.*;
import com.CaseStudy.Multiworld.convert.itf.IFindUserForOtherUsersConvert;
import com.CaseStudy.Multiworld.dto.request.LoginRequestDTO;
import com.CaseStudy.Multiworld.dto.response.ProfileResponseDTO;
import com.CaseStudy.Multiworld.dto.request.RegisterRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateUserRequestDTO;
import com.CaseStudy.Multiworld.dto.response.SearchUserResponseDTO;
import com.CaseStudy.Multiworld.dto.response.UserForFindUserByIdResponseDTO;
import com.CaseStudy.Multiworld.entity.User.User;
import com.CaseStudy.Multiworld.repository.IUserRepository;
import com.CaseStudy.Multiworld.service.IUserService;
import com.CaseStudy.Multiworld.util.HideUserInformation;
import com.CaseStudy.Multiworld.util.ValidateUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository iUserRepository;
    private final RegisterConvert registerConvert;
    private final LoginConvert loginConvert;
    private final ProfileConvert profileConvert;
    private final UpdateUserConvert updateUserConvert;
    private final SearchUserConvert searchUserConvert;
    private final IFindUserForOtherUsersConvert iFindUserForOtherUsersConvert;

    @Override
    public String register(RegisterRequestDTO registerRequestDto) {
        User user = registerConvert.convertDTOtoEntity(registerRequestDto);
        User entity = iUserRepository.findByUsername(user.getUsername()).orElse(null);
        if (entity != null){
            return "USER_EXIST";
        }

        User userHaveName = iUserRepository.findByName(user.getName()).orElse(null);
        if (userHaveName != null){
            return "NAME_EXISTED";
        }

        User userHaveEmail = iUserRepository.findByEmail(user.getEmail()).orElse(null);
        if (userHaveEmail != null){
            return "EMAIL_EXISTED";
        }


        boolean isUserValid = ValidateUser.validate(user);
        if(!isUserValid){
            return "INVALID_INFORMATION";
        }else {
            iUserRepository.save(user);
        }
        return "SUCCESS";
    }

    @Override
    public String login(LoginRequestDTO loginRequestDto) {
        User user = loginConvert.convertDTOtoEntity(loginRequestDto);
        User entity = iUserRepository.findByUsername(user.getUsername()).orElse(null);

        if (entity == null){
            return "NOT_EXIST";
        }

        boolean isPasswordValid = user.getPassword().equals(entity.getPassword());

        if (!isPasswordValid){
            return "WRONG_PASSWORD";
        }

        return user.getUsername();
    }

    @Override
    public ProfileResponseDTO findUserForProfile(String username) {
        User user = iUserRepository.findByUsername(username).orElse(null);
        if (user != null) {
            user.setPassword(HideUserInformation.hidePassword(user.getPassword()));
            user.setEmail(HideUserInformation.hideEmail(user.getEmail()));
        }
        return profileConvert.convertEntityToDTO(user);
    }

    @Override
    public String updateUser(UpdateUserRequestDTO updateUserRequestDto, MultipartFile avatar){

        String currentUsername = updateUserRequestDto.getUsername();
        User entity = iUserRepository.findByUsername(currentUsername).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );

        String newAvatarName;
        if (avatar == null){
            newAvatarName = entity.getAvatar();
        }else {
            final Path CURRENT_FOLDER = Paths.get("D:\\JOB\\CODE - IJ\\Module 4\\Case_Study\\Multiworld\\src\\main\\resources");
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("avatar");
            newAvatarName = avatar.getOriginalFilename();
            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(newAvatarName);
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(avatar.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String currentPassword = updateUserRequestDto.getCurrentPassword();
        String newPassword = updateUserRequestDto.getNewPassword();
        String newName = updateUserRequestDto.getNewName();
        String newEmail = updateUserRequestDto.getNewEmail();

        User userHaveName = iUserRepository.findByName(newName).orElse(null);
        if (userHaveName != null){
            return "NAME_EXISTED";
        }

        User userHaveEmail = iUserRepository.findByEmail(newEmail).orElse(null);
        if (userHaveEmail != null){
            return "EMAIL_EXISTED";
        }


        String entityPassword = entity.getPassword();

        if (currentPassword.equals("")){
            currentPassword = entity.getPassword();
            newPassword = entity.getPassword();
        }
        if(newName.equals("")){
            newName = entity.getName();
        }
        if (newEmail.equals("")){
            newEmail = entity.getEmail();
        }

        if (!entityPassword.equals(currentPassword)){
            return "WRONG_CURRENT_PASSWORD";
        }else {
            updateUserRequestDto.setId(entity.getId());
            updateUserRequestDto.setNewPassword(newPassword);
            updateUserRequestDto.setNewName(newName);
            updateUserRequestDto.setNewEmail(newEmail);
            updateUserRequestDto.setNewAvatar(newAvatarName);
            User user = updateUserConvert.convertDTOtoEntity(updateUserRequestDto);
            boolean isUserValid = ValidateUser.validate(user);
            if (isUserValid) {
                iUserRepository.save(user);
            }else {
                return "INVALID_INFORMATION";
            }
        }
        return "SUCCEED";
    }

    @Override
    public Page<SearchUserResponseDTO> searchUser(String keyword, int page) {
        Pageable pageable = PageRequest.of(page,10, Sort.by("name").ascending());
        Page<User> characterPage = iUserRepository.findByNameIgnoreCaseContaining(keyword,pageable);
        Page<SearchUserResponseDTO> pageDTO = searchUserConvert.convertEntityToDTO(characterPage);
        return pageDTO;

    }

    @Override
    public UserForFindUserByIdResponseDTO findUserById(Long userId) {
        User user = iUserRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        UserForFindUserByIdResponseDTO dto = iFindUserForOtherUsersConvert.convertEntityToDTO(user);
        return dto;
    }
}
