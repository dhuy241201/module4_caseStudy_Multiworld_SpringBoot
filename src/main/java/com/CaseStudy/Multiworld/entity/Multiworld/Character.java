package com.CaseStudy.Multiworld.entity.Multiworld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CHARACTERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_Deleted = 'F'")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private int age;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "ABILITY")
    private String ability;

    @Column(name = "STORY")
    private String story;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "IS_DELETED")
    private String isDeleted = "F";

    @ManyToOne
    @JoinColumn(name = "WORLD_ID")
    private World world;

}
