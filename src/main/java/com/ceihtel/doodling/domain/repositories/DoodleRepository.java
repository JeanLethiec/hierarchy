package com.ceihtel.doodling.domain.repositories;

import com.ceihtel.doodling.domain.entities.Doodle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoodleRepository extends JpaRepository<Doodle, Long> {
}
