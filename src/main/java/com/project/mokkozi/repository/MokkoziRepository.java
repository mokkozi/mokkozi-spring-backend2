package com.project.mokkozi.repository;

import com.project.mokkozi.model.Mokkozi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MokkoziRepository extends JpaRepository<Mokkozi, Long> {

}