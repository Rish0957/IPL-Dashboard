package com.rishabh.ipldashboard.respository;

import com.rishabh.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

//    List<Match> getByTeam1OrTeam2AndDateBetweenOrderByDateDesc(String teamName1, String teamName2,LocalDate date1, LocalDate date2);
    @Query("select m from Match m where (m.team1=:teamName or m.team2=:teamName)" +
            " and m.date between :startDate and :endDate order by date desc ")
    List<Match> getMatchesByTeamBetweenDates(@Param("teamName") String teamName, @Param("startDate") LocalDate date1,@Param("endDate") LocalDate date2);

    default List<Match> findLatestMatchesByTeam(String teamName,int count){
        Pageable pageable =  PageRequest.of(0,count);
        return getByTeam1OrTeam2OrderByDateDesc(teamName,teamName,pageable);
    }

    default List<Match> findTeamMatchesByYear(String teamName,int year){
        LocalDate startDate = LocalDate.of(year,1,1);
        LocalDate endDate = LocalDate.of(year+1,1,1);
        return getMatchesByTeamBetweenDates(teamName,startDate,endDate);
    }

}
