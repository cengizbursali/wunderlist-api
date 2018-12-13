package com.cengiz.bursali.wunderlist.api.persistence.repository;

import com.cengiz.bursali.wunderlist.api.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {
}
