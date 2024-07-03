import "./Cadastro.css"
import { useState } from "react"
import { Link } from "react-router-dom"
import Input from "../../Components/Input/Input"
import ButtonForms from "../../Components/ButtonForms/ButtonForms"
import email from "../../images/email.png"
import user from "../../images/user.png"
import cadeado from "../../images/cadeado.png"
import { useNavigate } from "react-router-dom"
import axios from "axios"
import NotificationError from "../../Components/Notification/NotificationError";
import Api from "../../Api/Api"
const Cadastro = ()=>{
    const [name, setName] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const navigate = useNavigate()


    const [errorInput, setErrorInput] = useState(false)
    const [notificationError, setNotificationError] = useState(false)
    const [errorText, setErrorText] = useState("")

    const handleClickRegister = async()=>{
      if(name !== "" && email !== "" && password !==""){
      await axios.post(`${Api}/users`,{
      username: name,
      email: email,
      password: password
    }).then((res)=>{
         console.log(res)
    navigate("/login")
        }).catch((err)=>{
            setErrorInput(true)
        setErrorText("Houve algum erro em seu cadastro")
        setNotificationError(true)
        setTimeout(()=>{setNotificationError(false)}, 7000);  
    });
  
}
else{
    setErrorInput(true)
    setErrorText("Preencha todos os campos antes de enviar")
    setNotificationError(true)
    setTimeout(()=>{setNotificationError(false)}, 7000);  
}
    }
    return(
        <section className="Cadastro">
            <NotificationError state={notificationError} text={errorText}></NotificationError>
            <div className="imageBg">

            </div>
            <div className="CadForm">
            <h2>Cadastre-se!!</h2>
                <Input error={errorInput} icon={"https://i.postimg.cc/8CV7NZSr/Vector.png"} placeholder="Nome" value={name} setValue={setName} name={"Nome"}></Input>
                <Input error={errorInput} placeholder="Email" icon={"https://i.postimg.cc/VktJx338/email.png"} value={email} setValue={setEmail} name={"Email"}></Input>
                <Input error={errorInput} password="password" placeholder="Senha" icon={"https://i.postimg.cc/MKNcrW35/cadeado.png"} value={password} setValue={setPassword} name={"Senha"}></Input>
                <Input error={errorInput} placeholder="Senha" password="password" icon={"https://i.postimg.cc/MKNcrW35/cadeado.png"} value={password} setValue={setPassword} name={"Senha"}></Input>
                <div className="button">
                    <ButtonForms name="Cadastrar" onClick={handleClickRegister}/>
                    <p>já possui um conta? <Link to={"/Login"}>Clique aqui</Link></p>
                </div>

           
            </div>
        </section>
    )

}
export default Cadastro