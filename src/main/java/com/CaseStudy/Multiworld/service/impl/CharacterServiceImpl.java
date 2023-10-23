package com.CaseStudy.Multiworld.service.impl;

import com.CaseStudy.Multiworld.convert.impl.*;
import com.CaseStudy.Multiworld.dto.request.CreateCharacterRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateCharacterRequestDTO;
import com.CaseStudy.Multiworld.dto.response.CharacterInfoForUpdateResponseDTO;
import com.CaseStudy.Multiworld.dto.response.SearchCharacterResponseDTO;
import com.CaseStudy.Multiworld.dto.response.ShowCharacterResponseDto;
import com.CaseStudy.Multiworld.dto.response.ViewCharacterResponseDTO;
import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import com.CaseStudy.Multiworld.entity.Multiworld.World;
import com.CaseStudy.Multiworld.repository.ICharacterRepository;
import com.CaseStudy.Multiworld.repository.IWorldRepository;
import com.CaseStudy.Multiworld.service.ICharacterService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CharacterServiceImpl implements ICharacterService {

    private final IWorldRepository iWorldRepository;
    private final ICharacterRepository iCharacterRepository;
    private final ShowCharacterConvert showCharacterConvert;
    private final CreateCharacterConvert createCharacterConvert;
    private final ViewCharacterConvert viewCharacterConvert;
    private final GetCharacterInfoForUpdateConvert getCharacterInfoForUpdateConvert;
    private final SearchCharacterConvert searchCharacterConvert;

    @Override
    public String createCharacter(CreateCharacterRequestDTO createCharacterRequestDTO, MultipartFile characterImage) {
        String name = createCharacterRequestDTO.getName();
        int age = createCharacterRequestDTO.getAge();

        if (name.equals("") || String.valueOf(age).equals("")){
            return "INVALID_INFO";
        }


        //Add image
        String characterImageName;
        if (characterImage == null){
            characterImageName = "character_image.png";
        }else {
            final Path CURRENT_FOLDER = Paths.get("D:\\JOB\\CODE - IJ\\Module 4\\Case_Study\\Multiworld\\src\\main\\resources");
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("character_image");
            characterImageName = characterImage.getOriginalFilename();
            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(characterImageName);
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(characterImage.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //FindWorld
        World worldOfCharacter = iWorldRepository.findById(createCharacterRequestDTO.getWorldId()).orElseThrow(
                () -> new IllegalArgumentException("World not found")
        );

        Character newCharacter = createCharacterConvert.convertDTOtoEntity(createCharacterRequestDTO);
        newCharacter.setImage(characterImageName);
        newCharacter.setWorld(worldOfCharacter);
        iCharacterRepository.save(newCharacter);
        return "SUCCESS";
    }

    @Override
    public ViewCharacterResponseDTO viewCharacter(Long characterId) {
        Character character = iCharacterRepository.findById(characterId).orElseThrow(
                () -> new IllegalArgumentException("Character Not Found")
        );

        String role = character.getRole();
        String ability = character.getAbility();
        String story = character.getStory();

        if (role.equals("")){
            character.setRole("No Information");
        }
        if (ability.equals("")){
            character.setAbility("No Information");
        }
        if (story.equals("")){
            character.setStory("No Information");
        }
        System.out.println(story);

        ViewCharacterResponseDTO DTO = viewCharacterConvert.convertEntityToDTO(character);
        return DTO;
    }

    @Override
    public CharacterInfoForUpdateResponseDTO getChacterInfoForUpdate(Long characterId) {
        Character character = iCharacterRepository.findById(characterId).orElseThrow(
                () -> new IllegalArgumentException("Character not found")
        );
        CharacterInfoForUpdateResponseDTO DTO = getCharacterInfoForUpdateConvert.convertEntityToDTO(character);
        return DTO;
    }

    @Override
    public String updateCharacter(UpdateCharacterRequestDTO updateCharacterRequestDTO, MultipartFile characterImage) {
        String name = updateCharacterRequestDTO.getName();
        int age = updateCharacterRequestDTO.getAge();

        if (name.equals("") || String.valueOf(age).equals("")){
            return "INVALID_INFO";
        }


        // Lấy hình ảnh mới hoặc lưu hình ảnh cũ
        String characterImageName;
        if (characterImage == null){
            characterImageName = updateCharacterRequestDTO.getImage();
        }else {
            final Path CURRENT_FOLDER = Paths.get("D:\\JOB\\CODE - IJ\\Module 4\\Case_Study\\Multiworld\\src\\main\\resources");
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("character_image");
            characterImageName = characterImage.getOriginalFilename();
            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(characterImageName);
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(characterImage.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Long id = updateCharacterRequestDTO.getId();
        Character character = iCharacterRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Character not found")
        );

        character.setName(updateCharacterRequestDTO.getName());
        character.setAge(updateCharacterRequestDTO.getAge());
        character.setGender(updateCharacterRequestDTO.getGender());
        character.setRole(updateCharacterRequestDTO.getRole());
        character.setAbility(updateCharacterRequestDTO.getAbility());
        character.setStory(updateCharacterRequestDTO.getStory());
        character.setImage(characterImageName);
        iCharacterRepository.save(character);
        return "SUCCESS";
    }

    @Override
    public String deleteCharacter(Long characterId) {
        Character character = iCharacterRepository.findById(characterId).orElseThrow(
                () -> new IllegalArgumentException("Character not found")
        );
        character.setIsDeleted("T");
        iCharacterRepository.save(character);
        return "SUCCESS";
    }

    @Override
    public Page<ShowCharacterResponseDto> findAllCharacterForShowCharacter(String sort, String order, int page, long worldId) {
        Pageable requestPage;
        if (order.equalsIgnoreCase("asc")) {
            requestPage = PageRequest.of(page, 5, Sort.by(sort).ascending());
        }else {
            requestPage = PageRequest.of(page, 5, Sort.by(sort).descending());
        }
        Page<Character> characterList = iCharacterRepository.findByWorldId(requestPage,worldId);
        Page<ShowCharacterResponseDto> showCharacterResponseDtos = showCharacterConvert.convertEtoD(characterList);
        return showCharacterResponseDtos;
    }

    @Override
    public Page<SearchCharacterResponseDTO> searchCharacter(String keyword, int page) {
        Pageable pageable = PageRequest.of(page,10,Sort.by("name").ascending());
        Page<Character> characterPage = iCharacterRepository.findByNameIgnoreCaseContaining(keyword,pageable);
        Page<SearchCharacterResponseDTO> pageDTO = searchCharacterConvert.convertEntityToDTO(characterPage);
        return pageDTO;
    }

}
