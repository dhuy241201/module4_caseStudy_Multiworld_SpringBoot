package com.CaseStudy.Multiworld.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDTO {
    private long id;
    private String username;
    private String currentPassword;
    private String newPassword;
    private String newName;
    private String newEmail;
    private String newAvatar;
}
