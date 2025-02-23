package com.ablez.admin.first_db.security.repository;

import com.ablez.admin.first_db.security.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
