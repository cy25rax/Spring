package ru.gb.springdemo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final ReaderRepository readerRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Reader reader = readerRepository.findByName(username)
      .orElseThrow(() -> new UsernameNotFoundException(username));

    User user = new User(reader.getName(),
            reader.getPassword(),
            reader.getRoles().stream()
                    .map(it -> new SimpleGrantedAuthority(it.getName()))
                    .collect(Collectors.toList()));
    return user;
  }

}
