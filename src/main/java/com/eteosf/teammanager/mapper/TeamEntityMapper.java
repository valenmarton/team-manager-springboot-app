package com.eteosf.teammanager.mapper;

import com.eteosf.teammanager.model.dto.PlayerDto;
import com.eteosf.teammanager.model.dto.TeamDto;
import com.eteosf.teammanager.model.entity.PlayerEntity;
import com.eteosf.teammanager.model.entity.TeamEntity;
import io.swagger.model.Player;
import io.swagger.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamEntityMapper {
    public static TeamDto teamObjToTeamServiceDto(Team team) {
        final List<Player> playerList = team.getPlayers();
        final List<PlayerDto> players = new ArrayList<>(playerList.size());
        final TeamDto teamDto;

        for (Player player : playerList) {
            final PlayerDto playerDto = new PlayerDto(player.getId() ,player.getName(), player.getAge());
            players.add(playerDto);
        }
        teamDto = new TeamDto(team.getId(), team.getName(), players);
        return teamDto;
    }

    public static Team teamServiceDtoTopTeamObj (TeamDto teamDto){
        final List<PlayerDto> playerDtoList = teamDto.getPlayerList();
        final List<Player> playerList = new ArrayList<>(playerDtoList.size());
        final Team team = new Team();

        for(PlayerDto playerDto : playerDtoList) {
            final Player player = new Player();
            player.setId(playerDto.getId());
            player.setName(playerDto.getName());
            player.setAge(playerDto.getAge());
            playerList.add(player);
        }
        team.setId(teamDto.getId());
        team.setName(teamDto.getName());
        team.setPlayers(playerList);

        return team;
    }

    public static TeamDto teamEntityToTeamServiceDto(TeamEntity teamEntity) {
        final TeamDto teamDto = new TeamDto();
        teamDto.setId(teamEntity.getId());
        teamDto.setName(teamEntity.getName());
        teamDto.setPlayerList(PlayerEntityMapper.playerEntityListToDtoList(teamEntity.getPlayerEntities()));
        return teamDto;
    }

    public static TeamEntity teamServiceDtoToTeamEntity(TeamDto teamDto) {
        final TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamDto.getId());
        teamEntity.setName(teamDto.getName());
        final List<PlayerEntity> playerEntities = new ArrayList<>(teamDto.getPlayerList().size());

        for(PlayerDto playerDto : teamDto.getPlayerList()) {
            final PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setId(playerDto.getId());;
            playerEntity.setName(playerDto.getName());
            playerEntity.setAge(playerDto.getAge());
            playerEntities.add(playerEntity);
        }
        teamEntity.setPlayerEntities(playerEntities);
        return teamEntity;
    }

    public static List<TeamEntity> teamDtoListToTeamEntityList(List<TeamDto> dtoList) {
        final List<TeamEntity> teamEntityList = new ArrayList<>();

        for(TeamDto dto : dtoList) {
            final TeamEntity teamEntity = TeamEntityMapper.teamServiceDtoToTeamEntity(dto);
            teamEntityList.add(teamEntity);
        }
    return teamEntityList;
    }

    public static List<TeamDto> teamEntityListToTeamDtoList(List<TeamEntity> entityList) {
        final List<TeamDto> teamDtoList = new ArrayList<>();

        for (TeamEntity teamEntity : entityList) {
            final TeamDto teamDto = TeamEntityMapper.teamEntityToTeamServiceDto(teamEntity);
            teamDtoList.add(teamDto);
        }
        return teamDtoList;
    }

    public  static List<Team> teamDtoListToTeamObjList (List<TeamDto> teamDtoList) {
        final List<Team> teamList = new ArrayList<>();
        for (TeamDto teamDto : teamDtoList) {
            final Team team = TeamEntityMapper.teamServiceDtoTopTeamObj(teamDto);
            teamList.add(team);
        }
        return teamList;
    }
}
