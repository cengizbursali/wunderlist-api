package com.cengiz.bursali.wunderlist.api.persistence.repository;

import com.cengiz.bursali.wunderlist.api.persistence.entity.WunderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WunderRespository extends JpaRepository<WunderEntity, String> {
}
