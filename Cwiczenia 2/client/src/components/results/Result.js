import React from "react";
import '../../App.css'

const Result = ({result}) => {

    return (
        <div className={"result"}>
            <div>
                    <b>Season:</b> {result.season}
            </div>
            <div>
                    <b>Round:</b> {result.raceNumber}
            </div>
            <div>
                    <b>Race date:</b> {result.raceDate}
            </div>
            <div>
                    <b>Race name:</b> {result.raceName}
            </div>
            <div>
                    <b>Track name:</b> {result.trackName}
            </div>
            <div>
                    <b>Qualifying time:</b> {result.qualifyingTime}
            </div>
            <div>
                    <b>Grid position:</b> {result.gridPosition}
            </div>
            <div>
                    <b>Finishing position:</b> {result.finishingPosition}
            </div>
            <div>
                    <b>Positions gained:</b> {result.positionsGained}
            </div>
        </div>
    )
}

export default Result;