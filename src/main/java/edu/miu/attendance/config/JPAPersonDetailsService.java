package edu.miu.attendance.config;

import edu.miu.attendance.domain.Person;
import edu.miu.attendance.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Qualifier("JPAPersonDetailsService")
public class JPAPersonDetailsService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException());
        return new JPAPersonDetails(user);
    }
}