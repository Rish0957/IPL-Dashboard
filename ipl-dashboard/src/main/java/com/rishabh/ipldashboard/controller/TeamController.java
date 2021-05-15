package com.rishabh.ipldashboard.controller;

import com.rishabh.ipldashboard.model.Team;
import com.rishabh.ipldashboard.respository.MatchRepository;
import com.rishabh.ipldashboard.respository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private final TeamRepository  teamRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }


    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable final String teamName){
        Team team= teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName,4));

        return team;
    }
}
