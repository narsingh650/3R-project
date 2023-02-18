package com.serviceworker.repository;

import com.serviceworker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    @Query(value = "select u from User u where u.email = :email and u.roleName =:roleName ")
    User findByEmailAndRoleName(String email, String roleName);
}
