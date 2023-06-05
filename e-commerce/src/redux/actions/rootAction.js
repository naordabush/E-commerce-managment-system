import { USER_DATA } from "../actionTypes/actionTypes";

const userData = (user) => ({
  type: USER_DATA,
  payload: { user },
});

export { userData };
