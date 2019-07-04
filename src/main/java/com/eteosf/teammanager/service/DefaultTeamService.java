package com.eteosf.teammanager.service;

import com.eteosf.teammanager.exception.PlayerNotFoundException;
import com.eteosf.teammanager.exception.TeamNotFoundException;
import com.eteosf.teammanager.mapper.PlayerEntityMapper;
import com.eteosf.teammanager.mapper.TeamEntityMapper;
import com.eteosf.teammanager.model.dto.PlayerDto;
import com.eteosf.teammanager.model.dto.TeamDto;
import com.eteosf.teammanager.model.entity.PlayerEntity;
import com.eteosf.teammanager.model.entity.TeamEntity;
import com.eteosf.teammanager.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DefaultTeamService implements TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTeamService.class);
    private TeamRepository teamRepository;

    @Autowired
    public DefaultTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public List<TeamDto> getAllTeams() {
        List<TeamDto> teamDtoList = TeamEntityMapper.teamEntityListToTeamDtoList(teamRepository.findAll());
        LOGGER.info("getAllTeams() service is called. Teams are retrieved.");
        return teamDtoList;
    }

    @Override
    public TeamDto getTeam(Long id) {
        if(!(teamRepository.findById(id).isPresent())) {
            LOGGER.info("getTeam() service exception. Bad ID: "+id);
            throw new TeamNotFoundException("Team not found.");

        }
        LOGGER.info("getTeam() service is called. Team is retrieved with ID: " +id);
        TeamEntity fetchedTeam = teamRepository.findById(id).get();
        return TeamEntityMapper.teamEntityToTeamServiceDto(fetchedTeam);
    }

    @Override
    public void createTeam(TeamDto teamDto) {
        final TeamEntity teamEntity = TeamEntityMapper.teamServiceDtoToTeamEntity(teamDto);
        LOGGER.info("createTeam() service is called. Team is created with ID: " +teamDto.getId());
        teamRepository.save(teamEntity);
    }

    @Override
    public void deleteTeam(Long id) {
        if(!(teamRepository.findById(id).isPresent())) {
            LOGGER.info("deleteTeam() service is called. Could not delete Team with ID: " +id);
            throw new TeamNotFoundException("Team not found.");
        }
        LOGGER.info("deleteTeam() service is called. Team is deleted with ID: " +id);
        teamRepository.deleteById(id);
    }

    @Override
    public void editTeam(Long id, TeamDto teamDto) {
        final Optional<TeamEntity> optional = teamRepository.findById(id);
        if(!(optional.isPresent())) {
            LOGGER.info("editTeam() service is called. Could not edit Team with ID: " +id);
            throw new TeamNotFoundException("Team not found.");
        }
        final TeamEntity teamEntity = optional.get();
        teamEntity.setId(teamDto.getId());
        teamEntity.setName(teamDto.getName());
        teamEntity.setPlayerEntities(PlayerEntityMapper.playerDtoListToEntityList(teamDto.getPlayerList()));
        LOGGER.info("editTeam() service is called. Team is edited with ID: " +id);
        teamRepository.save(teamEntity);
    }

    @Override
    public List<PlayerDto> getAllPlayersFromATeam(Long teamId) {
        if (!(teamRepository.findById(teamId).isPresent())) {
            LOGGER.info("getAllPlayersFromATeam() service is called. Could not retrieve players from team with ID: " + teamId);
            throw new TeamNotFoundException("Team not found.");
        }
        final TeamEntity teamEntity = teamRepository.findById(teamId).get();
        final List<PlayerEntity> playerEntityList = teamEntity.getPlayerEntities();
        LOGGER.info("getAllPlayersFromATeam() service is called. Players retrieved from team with ID: " + teamId);
        return PlayerEntityMapper.playerEntityListToDtoList(playerEntityList);
    }

    @Override
    public PlayerDto getPlayerFromATeam(Long teamId, Long playerId) {
        if (!(teamRepository.findById(teamId).isPresent())) {
            LOGGER.info("getPlayerFromATeam() service is called. Could not retrieve player from team with ID: " + playerId);
            throw new TeamNotFoundException("Team not found.");
        }
        TeamEntity teamEntity = teamRepository.findById(teamId).get();
        List<PlayerEntity> entityList = teamEntity.getPlayerEntities();
        for (PlayerEntity entity : entityList) {
            if (entity.getId() == playerId) {
                return PlayerEntityMapper.playerEntityToPlayerServiceDto(entity);
            }
        }
        LOGGER.info("getPlayerFromATeam() service is called.  Player retrieved from team with ID: " + playerId);
        throw new PlayerNotFoundException("Player not found.");
    }

    @Override
    public void createPlayerToATeam(Long teamId, PlayerDto playerDto) {
        if (!(teamRepository.findById(teamId).isPresent())) {
            LOGGER.info("createPlayerToATeam() service is called. Could not create player to team: " + teamId);
            throw new PlayerNotFoundException("Player not found.");
        }
        final TeamEntity teamEntity = teamRepository.findById(teamId).get();
        final PlayerEntity playerEntity = PlayerEntityMapper.playerServiceDtoToPlayerEntity(playerDto);
        final List<PlayerEntity> playerEntityList = teamEntity.getPlayerEntities();
        playerEntityList.add(playerEntity);

        LOGGER.info("createPlayerToATeam() service is called. Player created to team: " + playerDto);
        teamRepository.save(teamEntity);

    }

    @Override
    public void deletePlayerFromATeam(Long teamId, Long playerId) {
        if (!(teamRepository.findById(teamId).isPresent())) {
            LOGGER.info("deletePlayerFromATeam() service is called. Could not delete player from team with teamID: " + teamId);
            throw new PlayerNotFoundException("Team not found.");
        }
        TeamEntity teamEntity = teamRepository.findById(teamId).get();
        List<PlayerEntity> entityList = teamEntity.getPlayerEntities();
        for (PlayerEntity entity : entityList) {
            if (entity.getId().equals(playerId)) {
                LOGGER.info("deletePlayerFromATeam() service is called. Player deleted from team with ID: " + playerId);
                teamRepository.deleteById(playerId);
            }
        }
        throw new PlayerNotFoundException("Player not found.");
    }
}
