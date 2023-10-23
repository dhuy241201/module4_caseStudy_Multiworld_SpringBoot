package com.CaseStudy.Multiworld.repository;

import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import com.CaseStudy.Multiworld.entity.Multiworld.World;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Repository
public interface IWorldRepository extends JpaRepository<World,Long> {
    World findByUserIdAndName(Long user_id, String name);

    Page<World> findByUserId(Pageable requestedPage, Long userId);
    List<World> findByUserId(Long userId);

    Page<World> findByNameIgnoreCaseContaining(String worldName, Pageable pageable);

}
