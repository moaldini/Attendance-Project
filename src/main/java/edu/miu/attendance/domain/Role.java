package edu.miu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="role")
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
    @ManyToMany(mappedBy = "roleList", fetch = FetchType.EAGER)
    @ToStringExclude
    private Set<Person> personList = new HashSet<>();

    public Role(long id, String role) {
        this.id = id;
        this.role = role;
    }

    public void addPerson(Person person) {  personList.add(person); }
}
