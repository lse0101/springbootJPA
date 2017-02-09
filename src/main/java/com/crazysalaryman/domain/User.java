package com.crazysalaryman.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"encodedPassword", "customers"})
public class User {
    @Id
    private String username;

    private String encodedPassword;

    @OneToMany(cascade = CascadeType.ALL, fetch =FetchType.LAZY, mappedBy = "user")
    private List<Customer> customers;
}
