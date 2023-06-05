import { useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";

function Register() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    username: "",
    password: "",
    firstName: "",
    lastName: "",
    email: "",
  });

  function handleChange(event) {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value.trim(),
    }));
  }

  async function save() {
    if (formData.username !== "" && formData.password !== "" && formData.firstName !== "" && formData.lastName !== "" && formData.email !== "") {
      try {
        const response = await axios.post("http://localhost:8080/users/register", formData, {
          headers: {
            "Content-Type": "application/json",
          },
        });
        console.log("response.data (save function): ");
        console.log(response.data);
        console.log("new id (save function): ");
        console.log(response.data.id);
        if (response.status === 200) {
          createNewCart(response.data.id);
        }
      } catch (error) {
        console.error(error);
      }
    }
    setFormData({
      username: "",
      password: "",
      firstName: "",
      lastName: "",
      email: "",
    });
  }

  async function createNewCart(currentId) {
    try {
      const requestData = {
        userId: { id: currentId },
        status: 1,
      };

      console.log("requestData " + requestData);

      const response = await axios.post("http://localhost:8080/cart/create", requestData, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      console.log("response " + response);
    } catch (error) {
      console.error(error);
    }
  }

  function back() {
    navigate("/");
  }

  return (
    <div className="container">
      <h1>Register</h1>

      <label htmlFor="username">username:</label>
      <input type="text" name="username" value={formData.username} onChange={handleChange} />

      <label htmlFor="password">password:</label>
      <input type="password" name="password" value={formData.password} onChange={handleChange} />

      <label htmlFor="firstName">first name:</label>
      <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} />
      <label htmlFor="lastName">last name:</label>
      <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} />
      <label htmlFor="email">email:</label>
      <input type="email" name="email" value={formData.email} onChange={handleChange} />

      <div className="button-container-register">
        <button className="button logout" onClick={back}>
          back
        </button>

        <button className="button save" onClick={save}>
          save
        </button>
      </div>
    </div>
  );
}

export default Register;
