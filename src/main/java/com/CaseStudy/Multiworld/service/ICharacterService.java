package com.CaseStudy.Multiworld.service;

import com.CaseStudy.Multiworld.dto.request.CreateCharacterRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateCharacterRequestDTO;
import com.CaseStudy.Multiworld.dto.response.CharacterInfoForUpdateResponseDTO;
import com.CaseStudy.Multiworld.dto.response.SearchCharacterResponseDTO;
import com.CaseStudy.Multiworld.dto.response.ShowCharacterResponseDto;
import com.CaseStudy.Multiworld.dto.response.ViewCharacterResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ICharacterService {

    Page<ShowCharacterResponseDto> findAllCharacterForShowCharacter(String sort, String order, int page, long worldId);

    String createCharacter(CreateCharacterRequestDTO createCharacterRequestDTO, MultipartFile characterImage);

    ViewCharacterResponseDTO viewCharacter(Long characterId);

    CharacterInfoForUpdateResponseDTO getChacterInfoForUpdate(Long characterId);

    String updateCharacter(UpdateCharacterRequestDTO updateCharacterRequestDTO, MultipartFile characterImage);

    String deleteCharacter(Long characterId);

    Page<SearchCharacterResponseDTO> searchCharacter(String keyword, int page);
}
