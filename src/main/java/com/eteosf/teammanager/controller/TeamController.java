package com.eteosf.teammanager.controller;

import com.eteosf.teammanager.mapper.PlayerEntityMapper;
import com.eteosf.teammanager.mapper.TeamEntityMapper;
import com.eteosf.teammanager.model.dto.PlayerDto;
import com.eteosf.teammanager.model.dto.TeamDto;
import com.eteosf.teammanager.service.TeamService;
import io.swagger.api.TeamsApi;
import io.swagger.model.Player;
import io.swagger.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TeamController implements TeamsApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public ResponseEntity<Void> addTeam(@Valid Team body) {
        teamService.createTeam(TeamEntityMapper.teamObjToTeamServiceDto(body));
        LOGGER.info("addTeam() controller called.");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> updateTeam(@Valid Team body, Integer teamId) {
        teamService.editTeam(teamId.longValue(), TeamEntityMapper.teamObjToTeamServiceDto(body));
        LOGGER.info("editTeam() controller called.");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addPlayer(@Valid Player body, Integer teamId) {
        teamService.createPlayerToATeam(teamId.longValue(), PlayerEntityMapper.playerObjToPlayerServiceDto(body));
        LOGGER.info("addPlayer() controller called.");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Team> deletePlayerById(Integer teamId, Integer playerId) {
        teamService.deletePlayerFromATeam(teamId.longValue(), playerId.longValue());
        LOGGER.info("deletePlayedById() controller called.");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Team> deleteTeamById(Integer teamId) {
        teamService.deleteTeam(teamId.longValue());
        LOGGER.info("deleteTeamById() controller called.");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Player> findPlayerById(Integer playerId, Integer teamId) {
        PlayerDto playerDto = teamService.getPlayerFromATeam(teamId.longValue(), playerId.longValue());
        Player result = PlayerEntityMapper.playerServiceDtoToPlayerObj(playerDto);
        LOGGER.info("findPlayerById() controller called.");
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Team> findTeamById(Integer teamId) {
        TeamDto teamDto = teamService.getTeam(teamId.longValue());
        Team result = TeamEntityMapper.teamServiceDtoTopTeamObj(teamDto);
        LOGGER.info("findTeamById() controller called.");
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Player>> getAllPlayers(Integer teamId) {
        List<PlayerDto> playerDtoList = teamService.getAllPlayersFromATeam(teamId.longValue());
        List<Player> result = PlayerEntityMapper.playerDtoListToPlayerObjList(playerDtoList);
        LOGGER.info("getAllPlayers() controller called.");
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Team>> getAllTeams() {
        List<TeamDto> teamDtoList = teamService.getAllTeams();
        List<Team> result = TeamEntityMapper.teamDtoListToTeamObjList(teamDtoList);
        LOGGER.info("getAllTeams() controller called.");
        return ResponseEntity.ok(result);
    }
}
