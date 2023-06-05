import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useSelector } from "react-redux";
import TableRow from "./TableRow";

function Cart() {
  const navigate = useNavigate();
  const userInfo = useSelector((state) => state.loginReducer);
  const [cartItems, setCartItems] = useState([]);
  const [totalAmount, setTotalAmount] = useState(0);

  const cartId = userInfo.user.cartId;

  // Fetch cart items from the database using Axios
  async function fetchCartItems() {
    try {
      const response = await axios.get(`http://localhost:8080/cart-items/get-cart-items/${cartId}`, {
        headers: { Authorization: sessionStorage.getItem("token") },
      });
      setCartItems(response.data);
      calculateTotalAmount(response.data);
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  // Calculate the total amount based on cart items
  function calculateTotalAmount(items) {
    const total = items.reduce((sum, item) => sum + item.item.price * item.quantity, 0);
    setTotalAmount(total);
  }

  useEffect(() => {
    fetchCartItems();
  }, []);

  function handleBack() {
    console.clear();
    navigate("/main");
  }

  function handlePay() {
    // Handle the logic for payment here
    // You can navigate to a payment page or perform any other actions as needed
    console.log("Payment completed!");
  }

  return (
    <div className="container">
      <h1>Your Cart</h1>

      <table className="table">
        <thead>
          <tr>
            <th>#</th>
            <th>Item</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th>Remove</th>
          </tr>
        </thead>
        <tbody>
          {cartItems.map((item, index) => (
            <TableRow key={item.id} place={index + 1} item={item} />
          ))}
        </tbody>
      </table>

      <div className="button-container">
        <button className="button logout" onClick={handleBack}>
          Back
        </button>
        <button className="button pay" onClick={handlePay}>
          {`pay ${totalAmount}$`}
        </button>
      </div>
    </div>
  );
}

export default Cart;
