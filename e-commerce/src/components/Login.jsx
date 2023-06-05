import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useDispatch } from "react-redux";
import axios from "axios";
import { userData } from "../redux/actions/rootAction";

function Login() {
  //redux:
  const dispatch = useDispatch();

  //router:
  const navigate = useNavigate();

  //state:
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  async function handleLogin() {
    if (username !== "" && password !== "") {
      try {
        let response = await axios.post("http://localhost:8080/users/login", {
          username: username,
          password: password,
        });

        if (response.status === 200) {
          sessionStorage.setItem("token", response.data.token);
          navigate("/main");
          console.log(response.data);
          dispatch(userData(response.data));
          // getCartId(response.data.id);
        } else {
          navigate("/");
        }
      } catch (error) {
        console.error(error);
      }
    }
    setUsername("");
    setPassword("");
  }

  async function handleRegister() {
    navigate("/register");
  }

  return (
    <div className="container">
      <h1>E-commerce HOME PAGE</h1>
      <label htmlFor="username">username:</label>
      <input
        type="text"
        name="username"
        value={username}
        onChange={(e) => {
          setUsername(e.target.value.trim());
        }}
      />
      <br />
      <label htmlFor="password">password:</label>
      <input
        type="password"
        name="password"
        value={password}
        onChange={(e) => {
          setPassword(e.target.value.trim());
        }}
      />
      <div className="button-container-login">
        <button className="button login" onClick={handleLogin}>
          Login
        </button>

        <button className="button register" onClick={handleRegister}>
          Register
        </button>
      </div>
    </div>
  );
}

export default Login;
