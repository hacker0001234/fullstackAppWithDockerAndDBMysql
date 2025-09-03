import {useEffect, useState} from "react";
import axios from "axios";

export default function TestApiToBD(){
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [response, setResponse] = useState("");
    const [disabled, setDisabled] = useState(true);

    const postData = () => {
        console.log(name,email,password);

        setEmail("");
        setPassword("");
        setName("");

        axios.post("/api/add",{name: name, email: email, password: password})
            .then(res => setResponse(res.data));

    }

    useEffect(() => {
        if(name.trim() === "" || email.trim() === "" || password.trim() === ""){
           setDisabled(true);
        }else {
            setDisabled(false);
        }
    }, [name,email,password]);
    return(
        <div>
            <h1>Test API to BD</h1>
            <p>set name</p>
            <input value={name} type={"text"} onChange={(event) => setName(event.target.value)}/>
            <p>set email</p>
            <input value={email} type={"text"} onChange={(event) => setEmail(event.target.value)}/>
            <p>set password</p>
            <input value={password} type={"text"} onChange={(event) => setPassword(event.target.value)}/>
            <button onClick={postData} disabled={disabled}>add</button>
            <p> response : {response}</p>
            <button onClick={(e) => window.location.href = "/get"}>next</button>
        </div>
    )
}