import {combineReducers} from "redux"
import userReducer from "./User/userReducer"
import petReducer from "./Pet/PetReducer"
import mapReducer from "./Map/MapReducer"

const rootReducer = combineReducers({userReducer, petReducer, mapReducer})

export default rootReducer
export type RootState = ReturnType<typeof rootReducer>

