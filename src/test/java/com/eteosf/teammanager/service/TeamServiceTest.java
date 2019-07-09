package com.eteosf.teammanager.service;

import com.eteosf.teammanager.model.dto.PlayerDto;
import com.eteosf.teammanager.model.dto.TeamDto;
import com.eteosf.teammanager.model.entity.PlayerEntity;
import com.eteosf.teammanager.model.entity.TeamEntity;
import com.eteosf.teammanager.repository.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
@ActiveProfiles("test")
public class TeamServiceTest {

    private TeamService teamService;
    private TeamRepository teamRepository;

    @Before
    public void init() {
        teamRepository = mock(TeamRepository.class);
        teamService = new DefaultTeamService(teamRepository);
    }

    @Test
    public void testFindTeamById() {
        //given
        final TeamDto expectedDto;
        final List<PlayerDto> playerDtoList = new ArrayList<>();
        final PlayerDto playerDto1 = new PlayerDto(1L, "Ronaldo", 15);
        playerDtoList.add(playerDto1);
        expectedDto = new TeamDto(1L, "Barca", playerDtoList);

        final TeamEntity teamEntity;
        final List<PlayerEntity> playerEntityList = new ArrayList<>();
        final PlayerEntity playerEntity = new PlayerEntity("Ronaldo", 15, null);
        playerEntity.setId(1L);
        playerEntityList.add(playerEntity);
        teamEntity = new TeamEntity("Barca", 1L, playerEntityList);

        Mockito.when(teamRepository.findById(1L)).thenReturn(Optional.of(teamEntity));

        //when
        TeamDto resultDto = teamService.getTeam(1L);

        //then
        assertThat("could not retrieve team", resultDto, is(expectedDto));
    }

    @Test
    public void testFindAllTeams() {
        //given
        final List<PlayerDto> playerDtoList = new ArrayList<>();
        final PlayerDto playerDto1 = new PlayerDto(1L, "Ronaldo", 15);
        playerDtoList.add(playerDto1);
        final List<TeamDto> expectedDtoList = new ArrayList<>();
        final TeamDto teamDto = new TeamDto(1L, "Barca", playerDtoList);
        expectedDtoList.add(teamDto);

        final List<PlayerEntity> playerEntityList = new ArrayList<>();
        final PlayerEntity playerEntity = new PlayerEntity("Ronaldo", 15, null);
        playerEntity.setId(1L);
        playerEntityList.add(playerEntity);
        final List<TeamEntity> teamEntityList = new ArrayList<>();
        final TeamEntity teamEntity = new TeamEntity("Barca", 1L, playerEntityList);
        teamEntityList.add(teamEntity);

        Mockito.when(teamRepository.findAll()).thenReturn(teamEntityList);

        //when
        final List<TeamDto> resultDtoList = teamService.getAllTeams();

        //then
        assertThat("could not retrieve teams", resultDtoList, is(expectedDtoList));
    }

    @Test
    public void testCreateOneTeam() {
        //when
        teamService.createTeam(any());

        //then
        verify(teamService, only()).createTeam(any());

    }
}
