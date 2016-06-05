package org.kszidocs.repository;

import org.kszidocs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String username);

    User findByEmail(String email);
}
