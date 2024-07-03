
const initialState = {
    url:"",
    pet: {}
}

const petReducer =(state = initialState, action) =>{
    if(action.type === "url"){
        return {
            ...state,
             url: action.payload.url}
        
    }
    if(action.type === "petPage"){
        return{
            ...state,
            pet: action.payload.pet
        }
    }
    if(action.type == "clear"){
        return{
            ...state,
            url: ""
        }
    }
    return state
}

export default petReducer