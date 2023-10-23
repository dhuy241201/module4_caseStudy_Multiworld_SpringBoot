package com.CaseStudy.Multiworld.controller.restcontroller;

import com.CaseStudy.Multiworld.dto.request.CreateWorldRequestDTO;
import com.CaseStudy.Multiworld.dto.request.UpdateWorldRequestDTO;
import com.CaseStudy.Multiworld.dto.response.*;
import com.CaseStudy.Multiworld.service.IWorldService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/world")
@AllArgsConstructor
public class WorldRestController {
    IWorldService iWorldService;

    @PostMapping("/create")
    public ResponseEntity<?> createWorld(@RequestPart("world") CreateWorldRequestDTO createWorldRequestDto,
    @RequestPart(value = "worldImage", required = false) MultipartFile worldImage){
        String message = iWorldService.createWorld(createWorldRequestDto,worldImage);
        if(message.equals("WORLD_CREATED")){
            return new ResponseEntity<>("You have created this world",HttpStatus.BAD_REQUEST);
        }else if (message.equals("EMPTY_NAME")){
            return new ResponseEntity<>("Name must be filled out",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Created world successfully",HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> findAllWorldForHomePage(@RequestParam (defaultValue = "1") int page){
        Pageable requestedPage = PageRequest.of (page - 1, 10, Sort.by("dateCreated").descending());
        Page<WorldForHomeResponseDTO> worldForHomeResponseDtoPage = iWorldService.findWorldForHome(requestedPage);
        return new ResponseEntity<>(worldForHomeResponseDtoPage,HttpStatus.OK);
    }

    @GetMapping("/show/{username}")
    public ResponseEntity<?> showAllWorldForUser(@RequestParam String sort, String order, int page, @PathVariable String username){
        Page<WorldForShowWorldResponseDTO> worldForShowWorldDTOPage = iWorldService.findWorldForShowWorld(username, sort, order, page );
        return new ResponseEntity<>(worldForShowWorldDTOPage,HttpStatus.OK);
    };

    @GetMapping("/show/other/{userId}")
    public ResponseEntity<?> showAllWorldForOtherUser(@PathVariable Long userId) {
        List<ShowWorldForOtherUserResponseDTO> worldForShowWorldDTOPage = iWorldService.findWorldForShowWorldForOtherUser(userId);
        return new ResponseEntity<>(worldForShowWorldDTOPage, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewWorldForUser(@PathVariable Long id){
        ViewWorldForUserResponseDTO viewWorldForUserResponseDTO = iWorldService.findWorldForViewWorldForUser(id);
        return new ResponseEntity<>(viewWorldForUserResponseDTO,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateWorld(@RequestPart("world") UpdateWorldRequestDTO updateWorldRequestDTO,
                                         @RequestPart(value = "worldImage", required = false) MultipartFile worldImage){
        String message = iWorldService.updateWorld(updateWorldRequestDTO,worldImage);
        if(message.equals("WORLD_CREATED")){
            return new ResponseEntity<>("You have created this world",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWorld(@PathVariable long id){
        String message = iWorldService.deleteWorld(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    };

    @GetMapping("/search")
    public ResponseEntity<?> searchWorld(@RequestParam String keyword, int page){
        Page<SearchWorldResponseDTO> pageWorld = iWorldService.searchWorld(keyword,page);
        return new ResponseEntity<>(pageWorld,HttpStatus.OK);
    }
}
