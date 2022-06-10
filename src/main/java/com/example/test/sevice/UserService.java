package com.example.test.sevice;

import com.example.test.entity.MyUser;
import com.example.test.repo.MyUserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final MyUserRepo userRepo;

    public UserService(MyUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser user = userRepo.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("user == null");
        }

       /* List<SimpleGrantedAuthority> collect = user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());*/

        return new User(user.getUsername(), user.getPassword(), user.getRoles());

    }
}
