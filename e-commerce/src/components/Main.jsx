import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";

function Main() {
  const navigate = useNavigate();
  const userInfo = useSelector((state) => state.loginReducer);

  const [items, setItems] = useState([]);
  const [selectedQuantities, setSelectedQuantities] = useState({});

  useEffect(() => {
    // Fetch items from the database using Axios
    async function fetchItems() {
      try {
        const response = await axios.get("http://localhost:8080/items/get-all", { headers: { Authorization: sessionStorage.getItem("token") } });
        setItems(response.data);

        // console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    }

    fetchItems();
  }, []);

  function handleLogout() {
    sessionStorage.removeItem("token");
    navigate("/");
    console.clear();
  }

  function handleCart() {
    navigate("/cart");
    console.clear();
  }

  function handleQuantityChange(itemId, quantity) {
    setSelectedQuantities((prevQuantities) => ({
      ...prevQuantities,
      [itemId]: quantity,
    }));
  }

  async function handleAddToCart(itemId) {
    //console.log("cart id from redux:");
    // console.log(userInfo);
    try {
      const quantity = selectedQuantities[itemId] || 1;

      const requestData = {
        cart: { id: userInfo.user.cartId },
        item: { id: itemId },
        quantity: quantity,
      };

      // console.log("requestData:");
      // console.log(requestData);

      await axios.post("http://localhost:8080/cart-items/create", requestData, {
        headers: {
          "Content-Type": "application/json",
          Authorization: sessionStorage.getItem("token"),
        },
        body: JSON.stringify(requestData),
      });

      //  console.log("response:");
      //  console.log(response);
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <div className="container">
      <h1>E-Commerce</h1>

      <div className="button-container">
        <button className="button logout" onClick={handleLogout}>
          Logout
        </button>
        <button className="button logout" onClick={handleCart}>
          cart
        </button>
      </div>

      <div className="row">
        {items.map((item) => (
          <div className="col-md-3" key={item.id}>
            <div className="card">
              <img src={item.image} className="card-img-top item-img" alt={item.title} />
              <div className="card-body">
                <h5 className="card-title">{item.name}</h5>
                <p className="card-text">{item.category}</p>
                <p className="card-text">{item.price}</p>
                <div className="form-group">
                  <label htmlFor={`quantity-${item.id}`}>Quantity:</label>

                  <select className="form-control" id={`quantity-${item.id}`} value={selectedQuantities[item.id] || 1} onChange={(e) => handleQuantityChange(item.id, parseInt(e.target.value))}>
                    {[...Array(item.quantity).keys()].map((quantity) => (
                      <option key={quantity + 1} value={quantity + 1}>
                        {quantity + 1}
                      </option>
                    ))}
                  </select>
                </div>
                <button className="btn btn-primary" onClick={() => handleAddToCart(item.id)}>
                  Add to Cart
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Main;
