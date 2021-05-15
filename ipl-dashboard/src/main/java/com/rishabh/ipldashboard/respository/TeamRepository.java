package com.rishabh.ipldashboard.respository;

import com.rishabh.ipldashboard.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Long> {

    Team findByTeamName(String teamName);

}
