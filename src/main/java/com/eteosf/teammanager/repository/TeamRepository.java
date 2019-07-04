package com.eteosf.teammanager.repository;

import com.eteosf.teammanager.model.entity.PlayerEntity;
import com.eteosf.teammanager.model.entity.TeamEntity;
import io.swagger.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<TeamEntity, Long> {

    @Override
    List<TeamEntity> findAll();


}
