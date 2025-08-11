package com.spartaschedulerdevelop.repository;

import com.spartaschedulerdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existByEmail(String email);

}
