import { useState } from "react"
import "./NavSlide.css"
import { BiMenu, BiUserCircle, BiMap} from "react-icons/bi";
import Logo from "../../images/logo.png"
import home from "../../images/home.png"
import fav from "../../images/fav.png"
import suporte from "../../images/suporte.png"
import { NavLink } from "react-router-dom";
import logOut from "../../images/logOut.png"
import config from "../../images/config.png"
import add from "../../images/add.png"
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../Redux/rootReducer";


type NavProps = {
    image?: string,
    local?: string
}

const NavSlide = (props:NavProps)=>{
    const dispatch = useDispatch()
    const navigate = useNavigate()
    const [slideView, setSlideView] = useState("-50vh")

    const handleClick = ()=>{
        slideView == "-50vh"?
        setSlideView("0vh"):
        setSlideView("-50vh")
    }
    const handleClickLogo = ()=>{
        navigate("/home")
}

    const userID = useSelector((state: RootState) => state.userReducer.userID);
    
    const logged = useSelector((state: RootState) => state.userReducer.logged);
    const userIconUrl = useSelector((state: RootState) => state.userReducer.userIconUrl)
    
    return (
        <div className="navContainer">
        <header className="navInside">
        <div className="navOptions">
        <BiMenu className="burguer" size={40} color="#3D1723" onClick={handleClick}></BiMenu>
        <img src={Logo} className="logoImg" onClick={handleClickLogo}/>
        </div>
        <div className="userOptions">
        <div className="localization">
        <BiMap size={35} color="#F98AAE"></BiMap>
        <p>Florianópolis - Sc</p>
        </div>
    
{logged?
userIconUrl? 
<img onClick={()=>{ navigate(`/User?id=${userID}`)}} src={userIconUrl} style={{cursor: "pointer"}} className="userIcon" alt="" />    
:
<BiUserCircle onClick={()=>{ navigate(`/User?id=${userID}`)}} size={40} color="#F98AAE" style={{cursor: "pointer"}} ></BiUserCircle>
:null
    

    }
        </div>
        </header>
        <div className="slide" style={{transform:`translateX(${slideView})` }}>
            <nav>
                <ul>
                    <li>
                        <NavLink className={"navSlide"} to={"/home"}>
                         <img src={home}/>
                        <p>Home</p>   
                        </NavLink>
                        
                    </li>
                    {logged?
                    
                    <li>
                    <NavLink className={"navSlide"} to={"/addPet"}>
            
                        <img src={add}/>
                        <p>Adicionar Pet</p>
                        </NavLink>

                    </li>: null
                
                
                }
                    <li>
                    <NavLink className={"navSlide"} to={"/suporte"}>
                        <img src={suporte}/>
                        <p>Suporte</p>
                        </NavLink>
                    </li>
                </ul>
            </nav>
            <div className="configure">
                <hr />
                <ul>
                    <li>
                    <NavLink className={"navSlide"} to={`/User?id=${userID}`}>

                    <img src={config}/>
                    <p>Perfil</p>
                    </NavLink>

                    </li>
                    <li>
                    <NavLink onClick={()=>{
                        dispatch({
                            type: "user/logOut"
                        })
                    }} className={"navSlide"} to={"/"}>

                    <img src={logOut}/>
                    <p>LogOut</p>
                    </NavLink>

                    </li>
                </ul>
            </div>
        </div>
        </div>
    
        
    )
}

export default NavSlide