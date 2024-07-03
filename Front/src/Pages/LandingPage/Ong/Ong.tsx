import "./Ong.css"
import Button from "../../../Components/Button/Button"
import OngImage from "../../../images/OngImage.png"
import { Link } from "react-router-dom"


const Ong = ()=>{
    return(
        <section className="Ong">
            <div className="container">
                <div className="OngImage">
                    <img src={OngImage} alt="" />
                </div>
                <div className="TextContainer">
                <div className="OngText">
                <h1>Cadastre sua ONG!!</h1>
                <p>Entre em contato conosco e cadastre sua ONG para aparecer nos mapas de nossos serviços!!</p>
                    <div className="buttons">
                        <button><Link to={"/ongCad"}>Saiba mais</Link></button>
                    </div>
                </div>
                </div>
            </div>
            <div className="background">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1920 460" fill="none">
                <path d="M-5 465V185.433L1920 0V465H-5Z" fill="#FFD8E5" fill-opacity="1"/>
            </svg>
            </div>
        </section>
    )
}

export default Ong;