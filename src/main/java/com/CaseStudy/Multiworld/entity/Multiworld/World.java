package com.CaseStudy.Multiworld.entity.Multiworld;

import com.CaseStudy.Multiworld.entity.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "WORLD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_Deleted = 'F'")
public class World {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FANTASY")
    private String fantasy;

    @Column(name = "DATE_CREATED")
    private Date dateCreated = new Date();

    @Column(name = "IMAGE")
    private String image;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "IS_DELETED")
    private String isDeleted = "F";

    @OneToMany(mappedBy = "world", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Character> characters;

}
