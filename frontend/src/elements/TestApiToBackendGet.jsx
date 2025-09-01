import {useEffect, useState} from "react";
import axios from "axios";

export default function TestApiToBackendGet(){
    const [users, setUsers] = useState([]);

    const getUsers = ()=>{
        axios.get("http://localhost:8081/api/get").then(res => setUsers(res.data));
    }
    useEffect(() => {
        console.log(users);
    }, [users]);

    return(
        <div>
            <h1>get all users</h1>
            <button onClick={getUsers}>get</button>
            <div>
                {users.map(user => (
                    <p>user name : {user.name}, user email : {user.email}, user password : {user.password}</p>
                ))}
            </div>
        </div>
    )
}