package edu.miu.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.attendance.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String userName);
}
