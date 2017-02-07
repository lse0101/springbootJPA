package com.crazysalaryman.service;

import com.crazysalaryman.domain.User;
import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by lse0101 on 2017-02-06.
 */
@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
   private final User user;

   public LoginUserDetails(User user) {
       super(user.getUsername(),
               user.getEncodedPassword(),
               AuthorityUtils.createAuthorityList("ROLE_USER"));

       this.user = user;
   }
}
