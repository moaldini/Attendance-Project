package edu.miu.attendance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Table(name="person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name",nullable = false, length = 255)
    private String firstName;
    @Column(name = "last_name",nullable = false, length = 255)
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;//student ID
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @ToStringExclude
    @JsonIgnore
    private Set<Role> roleList = new HashSet<>();

    public Person(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public void addRole(Role role) {  roleList.add(role); }
}
