package com.CaseStudy.Multiworld.service.impl;

import com.CaseStudy.Multiworld.convert.impl.*;
import com.CaseStudy.Multiworld.dto.request.CreateWorldRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateWorldRequestDTO;
import com.CaseStudy.Multiworld.dto.response.*;
import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import com.CaseStudy.Multiworld.entity.Multiworld.World;
import com.CaseStudy.Multiworld.entity.User.User;
import com.CaseStudy.Multiworld.repository.ICharacterRepository;
import com.CaseStudy.Multiworld.repository.IUserRepository;
import com.CaseStudy.Multiworld.repository.IWorldRepository;
import com.CaseStudy.Multiworld.service.IWorldService;
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
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorldServiceImpl implements IWorldService {
    IUserRepository iUserRepository;
    IWorldRepository iWorldRepository;
    ICharacterRepository iCharacterRepository;
    PageWorldConvert pageWorldConvert;
    ShowWorldConvert showWorldConvert;
    ViewWorldConvert viewWorldConvert;
    SearchWorldConvert searchWorldConvert;
    ShowWorldForOtherUserConvert showWorldForOtherUserConvert;
    @Override
    public String createWorld(CreateWorldRequestDTO createWorldRequestDto, MultipartFile worldImage) {

        //Get user id and world's name
        String username = createWorldRequestDto.getUsername();
        User userEntity = iUserRepository.findByUsername(username).orElse(null);
        String name = createWorldRequestDto.getName();
        if(name.equalsIgnoreCase("")){
            return "EMPTY_NAME";
        }

        //Check world created
        World worldInDB = iWorldRepository.findByUserIdAndName(userEntity.getId(),name);
        if (worldInDB != null && worldInDB.getName().equalsIgnoreCase(name)){
            return "WORLD_CREATED";
        }

        String fantasy = createWorldRequestDto.getFantasy();


        //Add image
        String worldImageName;
        if (worldImage == null){
            worldImageName = "world_image.png";
        }else {
            final Path CURRENT_FOLDER = Paths.get("D:\\JOB\\CODE - IJ\\Module 4\\Case_Study\\Multiworld\\src\\main\\resources");
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("world_image");
            worldImageName = worldImage.getOriginalFilename();
            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(worldImageName);
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(worldImage.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Create new world
        World newWorld = new World();
        newWorld.setName(name);
        newWorld.setFantasy(fantasy);
        newWorld.setImage(worldImageName);
        newWorld.setUser(userEntity);

        iWorldRepository.save(newWorld);
        return "SUCCEED";
    }

    @Override
    public Page<WorldForHomeResponseDTO> findWorldForHome(Pageable pageable) {
        Page<World> worldPage = iWorldRepository.findAll(pageable);
        Page<WorldForHomeResponseDTO> worldDtoPage = pageWorldConvert.apply(worldPage);
        return worldDtoPage;
    }

    @Override
    public Page<WorldForShowWorldResponseDTO> findWorldForShowWorld(String username, String sort, String order, int page) {
        Pageable requestedPage;
        if (order.equalsIgnoreCase("asc")) {
            requestedPage = PageRequest.of(page, 10, Sort.by(sort).ascending());
        }else {
            requestedPage = PageRequest.of(page, 10, Sort.by(sort).descending());
        }


        User userEntity = iUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("User Not Found")
        );
        Long userId = userEntity.getId();
        Page<World> worldPage = iWorldRepository.findByUserId(requestedPage, userId);
        Page<WorldForShowWorldResponseDTO> worldForShowPage = showWorldConvert.convertEtoD(worldPage);
        return worldForShowPage;
    }

    @Override
    public ViewWorldForUserResponseDTO findWorldForViewWorldForUser(Long id) {
        World worldEntity = iWorldRepository.findById(id).orElse(null);
        ViewWorldForUserResponseDTO viewWorldForUserResponseDTO = viewWorldConvert.convertEntityToDTO(worldEntity);
        return viewWorldForUserResponseDTO;
    }

    @Override
    public String updateWorld(UpdateWorldRequestDTO updateWorldRequestDTO, MultipartFile worldImage) {
        //Get user id and world's name
        String newName = updateWorldRequestDTO.getName();
        long idOfUpdateWorld = updateWorldRequestDTO.getId();

        World worldEntity = iWorldRepository.findById(idOfUpdateWorld).orElseThrow(
                () -> new IllegalArgumentException("World Not Found")
        );
        long userIdOfWorldEntity = worldEntity.getUser().getId();
        String nameOfWorldEnity = worldEntity.getName();

        //Check world created
        if (!newName.equalsIgnoreCase(nameOfWorldEnity)) {//Kiểm tra tên mới có giống với tên world có id đã truyền không.Nếu có thì bỏ qua
            World worldInDB = iWorldRepository.findByUserIdAndName(userIdOfWorldEntity, newName);//Nếu khác thì kiểm tra xem người dùng đã tạo world đó chưa
            if (worldInDB != null) {
                return "WORLD_CREATED";
            }
        }

        //Add image
        String worldImageName;
        if (worldImage == null){
            worldImageName = updateWorldRequestDTO.getImage();
        }else {
            final Path CURRENT_FOLDER = Paths.get("D:\\JOB\\CODE - IJ\\Module 4\\Case_Study\\Multiworld\\src\\main\\resources");
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("world_image");
            worldImageName = worldImage.getOriginalFilename();
            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(worldImageName);
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(worldImage.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String fantasy = updateWorldRequestDTO.getFantasy();
        long worldUpdateId = updateWorldRequestDTO.getId();
        User user = worldEntity.getUser();


        //Create new world
        World newWorld = new World();
        newWorld.setId(worldUpdateId);
        newWorld.setName(newName);
        newWorld.setFantasy(fantasy);
        newWorld.setDateCreated(worldEntity.getDateCreated());
        newWorld.setImage(worldImageName);
        newWorld.setUser(user);

        iWorldRepository.save(newWorld);
        return "SUCCEED";
    }

    @Override
    public String deleteWorld(long id) {
        World worldEntity = iWorldRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("World not found")
        );
        worldEntity.setIsDeleted("T");

        List<Character> characterList = iCharacterRepository.findByWorldId(id).orElse(
                new ArrayList<>()
        );
        if (characterList.size()>0){
            for (Character character : characterList){
                character.setIsDeleted("T");
            }
        }
        worldEntity.setCharacters(characterList);
        iWorldRepository.save(worldEntity);
        return "SUCCEED";
    }

    @Override
    public Page<SearchWorldResponseDTO> searchWorld(String keyword, int page) {
        Pageable pageable = PageRequest.of(page,10,Sort.by("name").ascending());
        Page<World> worldPage = iWorldRepository.findByNameIgnoreCaseContaining(keyword,pageable);
        Page<SearchWorldResponseDTO> pageDTO = searchWorldConvert.convertEntityToDTO(worldPage);
        return pageDTO;
    }

    @Override
    public List<ShowWorldForOtherUserResponseDTO> findWorldForShowWorldForOtherUser(Long userId) {
        List<World> listEntity = iWorldRepository.findByUserId(userId);
        List<ShowWorldForOtherUserResponseDTO> listDTO = showWorldForOtherUserConvert.convertEntityToDTO(listEntity);
        return listDTO;
    }
}
