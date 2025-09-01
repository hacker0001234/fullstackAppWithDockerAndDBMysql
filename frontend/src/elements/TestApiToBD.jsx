import {useState} from "react";
import axios from "axios";

export default function TestApiToBD(){
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [response, setResponse] = useState("");

    const postData = () => {
        console.log(name,email,password);

        setEmail("");
        setPassword("");
        setName("");

        axios.post("http://localhost:8081/api/add",{name: name, email: email, password: password})
            .then(res => setResponse(res.data));

    }

    return(
        <div>
            <h1>Test API to BD</h1>
            <p>set name</p>
            <input value={name} type={"text"} onChange={(event) => setName(event.target.value)}/>
            <p>set email</p>
            <input value={email} type={"text"} onChange={(event) => setEmail(event.target.value)}/>
            <p>set password</p>
            <input value={password} type={"text"} onChange={(event) => setPassword(event.target.value)}/>
            <button onClick={postData}>add</button>
            <p> response : {response}</p>
        </div>
    )
}