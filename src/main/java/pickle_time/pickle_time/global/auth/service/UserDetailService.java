package pickle_time.pickle_time.global.auth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pickle_time.pickle_time.User.Repository.UserRepository;
import pickle_time.pickle_time.User.model.Users;
import pickle_time.pickle_time.global.auth.detail.PrincipalDetails;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user= userRepository.findByEmailAndSocialType(username, "GENERAL").orElseThrow(() -> new IllegalArgumentException("해당 계정이 존재하지 않습니다"));
        return new PrincipalDetails(user);
    }
}
