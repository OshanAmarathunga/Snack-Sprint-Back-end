package com.oshan.snack.sprint.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oshan.snack.sprint.dto.RestourentDto;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private USER_ROLE role=USER_ROLE.ROLE_CUSTOMER;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Order> orders=new ArrayList<Order>();
    @ElementCollection
    private List<RestourentDto> favorites=new ArrayList();
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true) //when one user delete, all the related  address will delete
    private List<Address> addresses =new ArrayList<>();
}
