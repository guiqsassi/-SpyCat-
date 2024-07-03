import "./ChangePassword.css"
import Input from "../../Components/Input/Input";
import React, { useState } from 'react';
import ButtonForms from "../../Components/ButtonForms/ButtonForms";
import axios from "axios";
import Api from "../../Api/Api";

import { useSearchParams, useNavigate } from "react-router-dom";

const ChangePassword = () => {
    const [searchParams] = useSearchParams()
    const [password,setPassword] = useState()
    const [passwordConfirm,setPasswordConfirm] = useState()
    const token = searchParams.get("token")
    const navigate = useNavigate()
    const handleClick = async()=>{
        console.log(token);
        if(password === passwordConfirm){

            await axios.post(`${Api}/savePassword`, {   
                password: password,
                token: token
            }).then((res)=>{
                navigate("/login")
                console.log(res);
            })
        }
    }

    return ( 
        <section className="forgetPassword">
            <div className="forgetPasswordContainer">
                <div className="forgetPasswordTittle">
                    <h1> Informe a nova senha para a sua conta</h1>
                </div>
                <div className="inputChangePassword">
                    <Input name={"Nova senha"} setValue={setPassword} value={password} placeholder="Senha" password="password"></Input>
                    <Input name={"Confimar senha"} setValue={setPasswordConfirm} value={passwordConfirm} placeholder="Confirmar Senha" password="password"></Input>
                    <ButtonForms onClick={handleClick} name="enviar"></ButtonForms>
                </div>
                
            </div>
        </section>
     );
}
 
export default ChangePassword
;