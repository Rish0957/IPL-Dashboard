package com.rishabh.ipldashboard.controller;

import com.rishabh.ipldashboard.model.Match;
import com.rishabh.ipldashboard.model.Team;
import com.rishabh.ipldashboard.respository.MatchRepository;
import com.rishabh.ipldashboard.respository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchForTeam(@PathVariable final String teamName, @RequestParam final int year){
        return matchRepository.findTeamMatchesByYear(teamName,year);
    }
}
