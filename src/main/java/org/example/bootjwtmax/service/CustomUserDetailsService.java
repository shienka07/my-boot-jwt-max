package org.example.bootjwtmax.service;

import lombok.RequiredArgsConstructor;
import org.example.bootjwtmax.model.entity.UserAccount;
import org.example.bootjwtmax.model.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount account = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("기수 404에서 1기수본부 유저를 찾을 수 없습니다. %s".formatted(username)));
        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .build();
    }
}
