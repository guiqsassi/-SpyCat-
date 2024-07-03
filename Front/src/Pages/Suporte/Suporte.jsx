import "./Suporte.css"
import Input from "../../Components/Input/Input"
import ButtonForms from "../../Components/ButtonForms/ButtonForms"
import planeGuy from "../../images/AirplaneGuy.png"
import NavSlide from "../../Components/NavSlide/NavSlide"
import { useState, useRef } from "react"
import NotificationError from "../../Components/Notification/NotificationError"
import NotificationOkay from "../../Components/Notification/NotificationOkay"
import emailjs from '@emailjs/browser';

const Suporte = () => {
    const [notification, setNotification] = useState(false)
    const [notificationError, setNotificationError] = useState(false)
    const [errorText, setErrorText] = useState("")

    const [name, setName] = useState("")
    const [email, setEmail] = useState("")
    const [message, setMessage] = useState("")
    const form = useRef();

    const sendEmail = (e) => {
      e.preventDefault();
      if(name !== "" && email!== ""&& message !== ""){
      emailjs.sendForm('service_1ivi5yg', 'template_r3qrv95', form.current, 'Ho6N_yBd1QwlMUZ7K')
        .then((result) => {
            console.log(result.text);

            setNotification(true)
            setTimeout(()=>{setNotification(false)}, 7000);   
        }, (error) => {
            setErrorText("Um erro inesperado ocorreu")
            console.log(error.text);
            setNotificationError(true)
            setTimeout(()=>{setNotificationError(false)}, 7000);   
        });
    }
    else{
        setNotificationError(true)
        setErrorText("Insira todos os dados ccorretamente")

        setTimeout(()=>{setNotificationError(false)}, 7000);   

    }
};
    return (
        <>
                <NavSlide></NavSlide>

                <section className="Suporte">
                <NotificationError text={errorText} state={notificationError}></NotificationError>
            <NotificationOkay state={notification}></NotificationOkay>
        <div className="SupportContainer">
            <div className="imagePaper">
                <h2>Entre em contato</h2>
                <img src={planeGuy} alt="" />
            </div>
            <div className="Formulario">
                <form action="" ref={form}>
                    
                    <Input placeholder="Nome" formName="from_name" value={name} setValue={setName} name={"Nome"}></Input>
                    <Input placeholder="Email" formName="from_email" value={email} setValue={setEmail} name={"Email"}></Input>
                    <Input textArea={true} formName="message" placeholder="Mensagem" value={message} setValue={setMessage} name={"Mensagem"}></Input>
                    <ButtonForms onClick={sendEmail} name={"Enviar"}></ButtonForms>
                </form>
            </div>
        </div>
    </section>
        </>

     );
}
 
export default Suporte;