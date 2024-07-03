import { createStore } from "redux";
import { useDispatch } from "react-redux";
import rootReducer from "./rootReducer";
import {persistStore, persistReducer} from "redux-persist"
import storage from "redux-persist/lib/storage"

const persistConfig ={
    key: "spyCatRoot",
    storage
}

const persitedReducer = persistReducer(persistConfig, rootReducer)
const store = createStore(persitedReducer)
const persistor = persistStore(store)

export {store, persistor}
export type RootState = ReturnType<typeof rootReducer>
export type AppDispatch = typeof store.dispatch
export const useAppDispatch: () => AppDispatch = useDispatch    
