package com.example.sbbmission.user.service;


import com.example.sbbmission.common.dto.RsData;
import com.example.sbbmission.common.exception.DataNotFoundException;
import com.example.sbbmission.user.entity.SiteUser;
import com.example.sbbmission.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<SiteUser> create(String username, String email, String password) {
        SiteUser user = SiteUser.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        this.userRepository.save(user);
        return RsData.of("200", "%s님 가입을 환영합니다".formatted(user), user);
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }


}
