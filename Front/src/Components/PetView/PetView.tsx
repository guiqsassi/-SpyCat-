import { type } from "os"
import seta from "../../images/seta.png"
import "./PetView.css"
import { useDispatch } from "react-redux"
import { useNavigate } from "react-router-dom"
type propsPetView={
    image: string
    pet: {
        id: BigInt
    }
}
const PetView = (props:propsPetView)=>{
    const navigate = useNavigate()
    const dispatch = useDispatch()
    const handleClick = async()=>{
        dispatch({
            type: "petPage",
            payload:{
                pet: props.pet
            }
        })
        navigate(`/pets?id=${props.pet.id}`)
    }
    
    return(
        <div className="petContainer" onClick={handleClick} >
            <div style={{backgroundImage: `url(${props.image})`}} className="petImage">
            </div>
            <div className="viewMore">
                <p>Me ajude</p>
                <img src={seta} alt="" />
            </div>
        </div>
    )
}

export default PetView