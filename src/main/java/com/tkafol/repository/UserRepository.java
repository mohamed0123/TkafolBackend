package com.tkafol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tkafol.model.User;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "select * from users where role_id = :roleId", nativeQuery = true)
	public List<User> getUsersByRules(@Param("roleId") Long roleId );
}
