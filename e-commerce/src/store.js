import { combineReducers, compose, createStore } from "redux";
import { rootReducer } from "./redux/reducers/rootReducer";

const composedEnhancer = compose(window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

const store = createStore(
  combineReducers({
    loginReducer: rootReducer,
  }),
  composedEnhancer
);

export default store;
