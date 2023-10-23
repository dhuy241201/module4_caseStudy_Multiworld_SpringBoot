package com.CaseStudy.Multiworld.controller.restcontroller;

import com.CaseStudy.Multiworld.dto.request.CreateCharacterRequestDTO;
import com.CaseStudy.Multiworld.dto.request.CreateWorldRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateCharacterRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateWorldRequestDTO;
import com.CaseStudy.Multiworld.dto.response.CharacterInfoForUpdateResponseDTO;
import com.CaseStudy.Multiworld.dto.response.SearchCharacterResponseDTO;
import com.CaseStudy.Multiworld.dto.response.ShowCharacterResponseDto;
import com.CaseStudy.Multiworld.dto.response.ViewCharacterResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import com.CaseStudy.Multiworld.service.ICharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/character")
@RequiredArgsConstructor
public class CharacterRestController {

    private final ICharacterService iCharacterService;

    @GetMapping("/show/{worldId}")
    public ResponseEntity<?> showAllCharacter(@PathVariable Long worldId, @RequestParam String sort, String order, int page){

        Page<ShowCharacterResponseDto> pageDTO = iCharacterService.findAllCharacterForShowCharacter(sort,order,page,worldId);
        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCharacter(@RequestPart("character") CreateCharacterRequestDTO createCharacterRequestDTO,
                                             @RequestPart(value = "characterImage", required = false) MultipartFile characterImage){
        String message = iCharacterService.createCharacter(createCharacterRequestDTO,characterImage);
        if (message.equalsIgnoreCase("SUCCESS")) {
            return new ResponseEntity<>("Created character successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid information", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/view/{characterId}")
    public ResponseEntity<?> viewCharacterInfo(@PathVariable Long characterId){
        ViewCharacterResponseDTO viewCharacterResponseDTO = iCharacterService.viewCharacter(characterId);
        return new ResponseEntity<>(viewCharacterResponseDTO,HttpStatus.OK);
    }

    @GetMapping("/update/{characterId}/current-info")
    public ResponseEntity<?> getCharacterInfoForUpdate(@PathVariable Long characterId){
        CharacterInfoForUpdateResponseDTO characterInfoForUpdateResponseDTO = iCharacterService.getChacterInfoForUpdate(characterId);
        return new ResponseEntity<>(characterInfoForUpdateResponseDTO,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCharacter(@RequestPart("character") UpdateCharacterRequestDTO updateCharacterRequestDTO,
                                             @RequestPart(value = "characterImage", required = false) MultipartFile characterImage){
        String message = iCharacterService.updateCharacter(updateCharacterRequestDTO,characterImage);
        if (message.equalsIgnoreCase("SUCCESS")) {
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Information", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{characterId}")
    public ResponseEntity<?> deleteCharacter(@PathVariable Long characterId){
        String message = iCharacterService.deleteCharacter(characterId);
        if (message.equalsIgnoreCase("SUCCESS")){
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Deleted Failed",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCharacter (@RequestParam String keyword, int page){
        Page<SearchCharacterResponseDTO> pageCharacter = iCharacterService.searchCharacter(keyword,page);
        return new ResponseEntity<>(pageCharacter,HttpStatus.OK);
    }
}
