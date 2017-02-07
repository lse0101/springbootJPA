package com.crazysalaryman.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lse0101 on 2017-02-06.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@ToString(exclude = "customers")
public class User {
    @Id
    private String username;

    @JsonIgnore
    private String encodedPassword;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch =FetchType.LAZY, mappedBy = "user")
    private List<Customer> customers;
}
