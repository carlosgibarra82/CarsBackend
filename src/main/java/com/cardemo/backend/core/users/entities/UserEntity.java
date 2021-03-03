package com.cardemo.backend.core.users.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")

public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5751959429446537476L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name ="name", nullable = false, length = 50 )
    private String firstName;

    @Column(name ="last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name ="birth_date", nullable = false)
    private Date birthDate;

    @Column(name ="email", nullable = false, length = 100)
    private String email;

    @Column(name ="password", nullable = false, length = 100)
    private String password;

    @Transient
    public String getFullName() {
        return this.getFirstName()
                .concat(" ")
                .concat(this.getLastName());
    }

}
