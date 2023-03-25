
import StatsForm from "./components/form/StatsForm";
import Results from "./components/results/Results";
import {useState} from "react";

function App() {
    const [response, setResponse] = useState({});
    const [status, setStatus] = useState(0);
    const [loading, setLoading] = useState(false);

    return (
        <>
            <StatsForm setResponse={setResponse} setStatus={setStatus} setLoading={setLoading}/>
            <Results response={response} status={status} loading={loading}/>
        </>
    );
}

export default App;
