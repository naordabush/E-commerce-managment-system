const initialState = {
  userData: {
    id: null,
    cartId: null,
    username: "",
    email: "",
    firstName: "",
    lastName: "",
    token: "",
  },
};

export const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case "USER_DATA":
      return {
        ...state.userData,
        ...action.payload,
      };
    default:
      return state;
  }
};
