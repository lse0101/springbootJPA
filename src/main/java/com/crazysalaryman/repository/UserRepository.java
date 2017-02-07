package com.crazysalaryman.repository;

import com.crazysalaryman.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lse0101 on 2017-02-06.
 */
public interface UserRepository extends JpaRepository<User, String>{
}
