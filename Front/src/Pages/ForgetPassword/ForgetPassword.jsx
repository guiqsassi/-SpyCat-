import "./ForgetPassword.css"
import Input from "../../Components/Input/Input";
import React, { useState } from 'react';
import ButtonForms from "../../Components/ButtonForms/ButtonForms";
import axios from "axios";
import Api from "../../Api/Api";

const ForgetPassword = () => {

    const [email,setEmail] = useState()

    const handleClick = async()=>{
        await axios.post(`${Api}/forgot-password`, {   
        }, { params: { email: email}}).then((res)=>{
            console.log(res);
        })
    }

    return ( 
        <section className="forgetPassword">
            <div className="forgetPasswordContainer">
                <div className="forgetPasswordTittle">
                    <h1> Informe o email da sua conta</h1>
                    <p> Iremos enviar um email para confirmarmos sua identidade</p>
                </div>
                <div className="inputForgetPassword">
                    <Input name={"email"} setValue={setEmail} value={email} placeholder="email"></Input>
                    <ButtonForms onClick={handleClick} name="enviar"></ButtonForms>
                </div>
                
            </div>
        </section>
     );
}
 
export default ForgetPassword;