package com.CaseStudy.Multiworld.repository;

import com.CaseStudy.Multiworld.entity.Multiworld.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICharacterRepository extends JpaRepository<Character,Long> {
    Page<Character> findByWorldId(Pageable requestPage, long worldId);

    Optional<List<Character>> findByWorldId(long worldId);

    Page<Character> findByNameIgnoreCaseContaining(String name, Pageable pageable);
}
