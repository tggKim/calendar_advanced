package org.example.calendar_advanced.user.repository;

import org.example.calendar_advanced.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.password FROM User u WHERE u.userId = :userId")
    Optional<String> findPasswordByUserId(@Param("userId") Long userId);

}
