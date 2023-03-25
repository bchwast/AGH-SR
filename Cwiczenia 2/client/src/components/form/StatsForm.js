import React, {useState} from 'react';
import {driverIds} from "../../driver_ids";

const URL = "http://localhost:8080/results/";
const ACCESS_TOKEN = "F1_ENJOYER";

const StatsForm = ({setResponse, setStatus, setLoading}) => {
    const [driver, setDriver] = useState('max_verstappen');
    const [fromYear, setFromYear] = useState('2021');
    const [toYear, setToYear] = useState('2022');
    const onSubmit = (e) => {
        e.preventDefault();
        console.log(driver, fromYear, toYear);
        if (driver.trim().length > 0 && fromYear.trim().length > 0 && toYear.trim().length > 0 ) {
            setLoading(true);
            setStatus(0);
            fetchData({driver, fromYear, toYear});
        }
    }

    const fetchData = async (parameters) => {
        const url = URL + parameters.driver + "/" + parameters.fromYear + "/" + parameters.toYear;

        const response = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": ACCESS_TOKEN
            }
        });
        const payload = await response.json();
        console.log(payload);
        setResponse(payload);
        setStatus(response.status);
        setLoading(false);
      }

    return (
        <form onSubmit={onSubmit}>
            <label>Driver: </label>
            <input placeholder='Driver' list='drivers' onChange={(e) => setDriver(e.target.value)}/>
            <datalist id='drivers'>
                {driverIds.map((driver) => (
                    <option key={driver} value={driver}/>
                    ))}
            </datalist>
            <label>From year: </label>
            <input type="number" min='1950' onChange={(e) => setFromYear(e.target.value)}/>
            <label>To year: </label>
            <input type="number" max='2023' onChange={(e) => setToYear(e.target.value)} />

            <input type="submit" value={"Get stats"}/>
        </form>
    );
}

export default StatsForm;