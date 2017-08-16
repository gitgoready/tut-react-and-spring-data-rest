package cn.connectai.myai.auth;

import cn.connectai.myai.entity.User;

/**
 * Created by zy on 15/08/17.
 */
public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}