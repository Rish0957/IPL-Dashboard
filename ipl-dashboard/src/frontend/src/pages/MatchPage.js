import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom'
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';


export const MatchPage = ()=>{

  const [matches, setTeam] = useState([]);
  const { teamName, year } = useParams();
//   const teamName="Delhi Capitals";
//   const year =2018;
  
  useEffect(
    ()=>{
      const fetchMatches = async () =>{
        const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
        const data = await response.json();
        setTeam(data);
      }
      fetchMatches();
    },[teamName]
  );

  if(!matches){
    return <h2>404 Not found</h2>
  }

  return (
    <div className="MatchPage">
      <h1 >{teamName}</h1>
      {matches.map(match=><MatchDetailsCard teamName={teamName} match={match}/>)}
    </div>
  );
}

