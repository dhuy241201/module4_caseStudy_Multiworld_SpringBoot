package com.CaseStudy.Multiworld.repository;

import com.CaseStudy.Multiworld.entity.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

    Page<User> findByNameIgnoreCaseContaining(String keyword, Pageable pageable);
}
