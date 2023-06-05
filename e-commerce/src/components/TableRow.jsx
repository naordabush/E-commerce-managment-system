import React from "react";
import axios from "axios";

function TableRow(props) {
  const { place, item } = props;
  const itemID = item.id;

  async function handleDelete() {
    try {
      const response = await axios.delete(`http://localhost:8080/cart-items/remove/${itemID}`, {
        headers: { Authorization: sessionStorage.getItem("token") },
      });
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <tr>
      <td>{place}</td>
      <td>{item.item.name}</td>
      <td>{item.item.price}</td>
      <td>{item.quantity}</td>
      <td>{item.item.price * item.quantity}</td>
      <td>
        <button className="button dlt" onClick={handleDelete}>
          delete
        </button>
      </td>
    </tr>
  );
}

export default TableRow;
