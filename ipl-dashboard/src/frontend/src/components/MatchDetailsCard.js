import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetailsCard = ({teamName,match})=>{
    if(!match) return null;
    const otherTeam = match.team1===teamName?match.team2:match.team1;
  return (
    <div className="MatchDetailsCard">
      <h3>Latest Matches</h3>
      <h1>vs <Link to={"/team/"+otherTeam}>{otherTeam}</Link></h1>
      <h3> on <b>{match.date}</b> <br/> at <b>{match.venue}</b></h3>
      <h4>{match.matchWinner} won by {match.resultMargin} {match.result}</h4>
      <br/>
    </div>
  );
}

