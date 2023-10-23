package com.CaseStudy.Multiworld.controller.restcontroller;

import com.CaseStudy.Multiworld.dto.request.LoginRequestDTO;
import com.CaseStudy.Multiworld.dto.request.RegisterRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateUserRequestDTO;
import com.CaseStudy.Multiworld.dto.response.LoginResponseDTO;
import com.CaseStudy.Multiworld.dto.response.ProfileResponseDTO;
import com.CaseStudy.Multiworld.dto.response.SearchUserResponseDTO;
import com.CaseStudy.Multiworld.dto.response.UserForFindUserByIdResponseDTO;
import com.CaseStudy.Multiworld.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    IUserService iUserService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDto){
        String message = iUserService.register(registerRequestDto);
        switch (message){
            case "USER_EXIST":
                return new ResponseEntity<>("User existed",HttpStatus.BAD_REQUEST);
            case "EMAIL_EXISTED":
                return new ResponseEntity<>("Email existed",HttpStatus.BAD_REQUEST);
            case "NAME_EXISTED":
                return new ResponseEntity<>("Name existed",HttpStatus.BAD_REQUEST);
            case "INVALID_INFORMATION":
                return new ResponseEntity<>("Invalid information",HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>("Registered successfully. Let's login",HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDto){
        String username = iUserService.login(loginRequestDto);
        switch (username){
            case "NOT_EXIST":
                return new ResponseEntity<>("User doesn't exist",HttpStatus.NOT_FOUND);

            case "WRONG_PASSWORD":
                return new ResponseEntity<>("Username or password is incorrect",HttpStatus.BAD_REQUEST);
            default:
                LoginResponseDTO loginResponseDto = new LoginResponseDTO();
                loginResponseDto.setUsername(username);
                return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
        }
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getUserInformation(@PathVariable("username") String username){
        ProfileResponseDTO currentUser = iUserService.findUserForProfile(username);
        return new ResponseEntity<>(currentUser,HttpStatus.OK);
    }

    @GetMapping("/profile/other/{userId}")
    public ResponseEntity<?> getUserInformationById(@PathVariable Long userId){
        UserForFindUserByIdResponseDTO dto = iUserService.findUserById(userId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    };


    @PutMapping(value = "/update", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> update(@RequestPart("user") UpdateUserRequestDTO updateUserRequestDto,
                                    @RequestPart(value = "avatar", required = false) MultipartFile avatar
                                    ){
        String isUpdateSucceed = iUserService.updateUser(updateUserRequestDto, avatar);

        switch (isUpdateSucceed){
            case "NAME_EXISTED":
                return new ResponseEntity<>("Name existed",HttpStatus.BAD_REQUEST);
            case "EMAIL_EXISTED":
                return new ResponseEntity<>("Email existed",HttpStatus.BAD_REQUEST);
            case "WRONG_CURRENT_PASSWORD":
                return new ResponseEntity<>("Wrong current password",HttpStatus.BAD_REQUEST);
            case "INVALID_INFORMATION":
                return new ResponseEntity<>("Information doesn't valid. Try again",HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam String keyword, int page){
        Page<SearchUserResponseDTO> pageUser = iUserService.searchUser(keyword,page);
        return new ResponseEntity<>(pageUser,HttpStatus.OK);
    }
}

