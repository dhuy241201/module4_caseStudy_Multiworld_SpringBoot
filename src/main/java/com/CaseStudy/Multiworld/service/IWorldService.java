package com.CaseStudy.Multiworld.service;

import com.CaseStudy.Multiworld.dto.request.CreateWorldRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateWorldRequestDTO;
import com.CaseStudy.Multiworld.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IWorldService {
    String createWorld(CreateWorldRequestDTO createWorldRequestDto, MultipartFile worldImage);

    Page<WorldForHomeResponseDTO> findWorldForHome(Pageable pageable);

    Page<WorldForShowWorldResponseDTO> findWorldForShowWorld(String username, String sort, String order, int page);

    ViewWorldForUserResponseDTO findWorldForViewWorldForUser(Long id);

    String updateWorld(UpdateWorldRequestDTO updateWorldRequestDTO, MultipartFile worldImage);

    String deleteWorld(long id);

    Page<SearchWorldResponseDTO> searchWorld(String keyword, int page);

    List<ShowWorldForOtherUserResponseDTO> findWorldForShowWorldForOtherUser(Long userId);
}
