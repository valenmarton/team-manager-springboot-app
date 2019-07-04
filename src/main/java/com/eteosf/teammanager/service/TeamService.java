package com.eteosf.teammanager.service;

import com.eteosf.teammanager.model.dto.PlayerDto;
import com.eteosf.teammanager.model.dto.TeamDto;
import java.util.List;

public interface TeamService {
    List<TeamDto> getAllTeams();
    TeamDto getTeam(final Long id);
    void createTeam(final TeamDto team);
    void deleteTeam(final Long id);
    void editTeam(Long id, final TeamDto team);

    List<PlayerDto> getAllPlayersFromATeam(final Long teamId);
    PlayerDto getPlayerFromATeam(final Long teamId, final Long playerId);
    void createPlayerToATeam(final Long teamId, PlayerDto playerDto);
    void deletePlayerFromATeam(final Long  teamId ,final Long playerId);
}
