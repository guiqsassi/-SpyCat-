import axios from "axios"
import { MouseEvent, useState } from "react"
import { useDispatch } from "react-redux"
import { Link, useNavigate } from "react-router-dom"
import Api from "../../Api/Api"
import ButtonForms from "../../Components/ButtonForms/ButtonForms"
import Input from "../../Components/Input/Input"
import NotificationError from "../../Components/Notification/NotificationError";

import "./Login.css"
const Login = () =>{
    const [error, setError] = useState(false)
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [notificationError, setNotificationError] = useState(false)
    const [errorText, setErrorText] = useState("")
    
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const handleClick = (e: MouseEvent<HTMLButtonElement>)=>{
        e.preventDefault()
        if(email !== "" && password !== ""){
        axios.post(`${Api}/login`, {
            email: email,
            password: password

        }).then((res)=>{
            dispatch({
                type: "user/login",
                payload: {
                    logged: true,
                    id: res.data.id,
                    icon: res.data.icon,
                    token: res.data.token
                }
            })
            localStorage.setItem("token", res.data.token)
            navigate("/home")
            console.log(res.data);
            
        }).catch((err)=>{
            setErrorText("Informações inseridas erradas")
            setNotificationError(true)
            setTimeout(()=>{setNotificationError(false)}, 7000);  
            setError(true)
        })}
        else{
            setErrorText("Insira todos os campos")
            setNotificationError(true)
            setTimeout(()=>{setNotificationError(false)}, 7000);  
            setError(true)
        }

        
    }

    return(
        <section className="Login">
                        <NotificationError state={notificationError} text={errorText}></NotificationError>

        <div className="imageBg">

        </div>
        <div className="LoginForm">
        <h2>Entre na sua conta!!</h2>
        <form action="">
            <Input error={error} icon={"https://i.postimg.cc/8CV7NZSr/Vector.png"} placeholder="Email" value={email} setValue={setEmail} name={"Email"}></Input>
            <Input error={error} password="password" placeholder="Senha" icon={"https://i.postimg.cc/MKNcrW35/cadeado.png"} value={password} setValue={setPassword} name={"Senha"}></Input>
                <p>Esqueceu sua senha? <Link to={"/forget-password"}>Clique aqui</Link></p>
            <div className="button">
                <ButtonForms name="Logar" onClick={handleClick}/>
                <p>Não possui um conta? <Link to={"/Cadastro"}>Clique aqui</Link></p>
            </div>

        </form>
       
        </div>
    </section>
    )
}
export default Login