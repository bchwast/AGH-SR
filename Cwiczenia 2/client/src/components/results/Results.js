import React from 'react';
import Result from "./Result";

const Results = ({status, response, loading}) => {
    console.log(response)

    if (status === 0 && !loading) {
        return (
            <></>
        )
    }

    if (status === 0 && loading) {
        return (
            <h1>Loading...</h1>
        )
    }

    if (status !== 200) {
        return (
            <>
                <h3>{response.type}</h3>
                <h3>{response.status}</h3>
                <h3>{response.message}</h3>
                <h3>{response.timestamp}</h3>
            </>
        )
    }

    return (
        <>
            <h1>Driver: {response.firstName} {response.lastName}</h1>
            <h2>Number of wins in this period: {response.numberOfWins}</h2>
            <h2>Number of poles in this period: {response.numberOfPoles}</h2>
            <h2>Number of podiums in this period: {response.numberOfPodiums}</h2>
            {response && response.results.map((result) => (
                <Result key={result.raceDate} result={result} />
            ))}
        </>
    )
}

export default Results;