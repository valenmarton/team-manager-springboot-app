package com.eteosf.teammanager.service;

import com.eteosf.teammanager.exception.TeamNotFoundException;
import com.eteosf.teammanager.repository.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
@ActiveProfiles("test")
public class testTeamNotFoundExceptionHandler {
    
    private TeamService teamService;

    @Before
    public void init() {
        final TeamRepository teamRepository;
        teamRepository = mock(TeamRepository.class);
        teamService = new DefaultTeamService(teamRepository);
    }

    @Test(expected = TeamNotFoundException.class)
    public void testTeamNotFoundExceptionThrowingWithExceptionHandler() {
        teamService.getTeam(1L);
    }
}

