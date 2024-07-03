
const initialState = {
    longitude: 0,
    latitude: 0,
    city: "",
    state: "",
    type: "",
    position: [-27.548258, -48.498994]

}

const mapReducer = (state = initialState, action) =>{
    if(action.type === "mapNewMarker"){
        return {
            ...state,
            longitude: action.payload.longitude,
            latitude: action.payload.latitude,
            city: action.payload.city,
            state: action.payload.state,
            type: action.payload.type,            
            }
        
    }
    if(action.type === "newPosition"){
        return{
            ...state,
            position: action.payload.position
        }
    }
    return state
}

export default mapReducer