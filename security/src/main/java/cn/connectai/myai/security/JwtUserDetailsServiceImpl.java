package cn.connectai.myai.security;

import cn.connectai.myai.entity.ManagerRepository;
import cn.connectai.myai.entity.User;
import cn.connectai.myai.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by zy on 15/08/17.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public JwtUserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = repository.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}

