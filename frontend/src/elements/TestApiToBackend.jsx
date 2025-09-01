import {useState} from "react";
import axios from "axios";

export default function TestApiToBackend()
{
    const [data, setData] = useState("");

    const testApiToBackend = () => {
        axios.get("http://localhost:8081/api/backend").then(response => {
            setData(response.data);
        })
    }

    return(
        <div>
            <h1>test /api/toBD</h1>
            <button onClick={testApiToBackend}>api</button>
            <p>{data}</p>
        </div>
    )
}