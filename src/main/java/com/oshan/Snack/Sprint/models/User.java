package com.oshan.Snack.Sprint.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oshan.Snack.Sprint.dto.RestourentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private USER_ROLE ROLE;
    @JsonIgnore
    @OneToMany
    private List<Order> orders=new ArrayList<Order>();
    @ElementCollection
    private List<RestourentDto> favorites=new ArrayList();
}
