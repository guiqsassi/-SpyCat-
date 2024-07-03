import "./Input.css"
import React, {SetStateAction, Dispatch, useState, useEffect } from 'react';
import passwordOpen from "../../images/password.png"
import passwordClosed from "../../images/passwordClosed.png"

type InputProps = {
    value: string,
    name: String,
    placeholder: string,
    setValue: Dispatch<SetStateAction<string>>;
    icon?: string;
    textArea?: Boolean;
    error?: Boolean;
    password?: string;
    formName?: string
}

const Input = (props:InputProps )=>{

    const[view, setView] = useState(false)
    useEffect(
    ()=>{
        if(props.password){
            setView(true)
        }
        else{
            setView(false)
        }
    }  
    ,[])

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>)=>{
        props.setValue(e.target.value)
    }
    const handleChangeTextArea = (e: React.ChangeEvent<HTMLTextAreaElement>)=>{
        props.setValue(e.target.value)
    }

    return(
        <div className="campoInput">
            <label style={props.error? { color: "red"}: {}}>{props.name}</label>
        {props.textArea? <div className="TextContaier">
        <textarea style={props.error? { borderColor: "red"}: {}} name={props.formName} value={props.value} onChange={handleChangeTextArea} placeholder={props.placeholder}/>   
        </div>:
          <div className="inputContainer"  style={props.error? { borderColor: "red"}: {}}>
         {props.icon? <img className="iconInput" src={props.icon}/>:null}
         <input name={props.formName} type={view? "password" : "text"} value={props.value} onChange={handleChange} placeholder={props.placeholder}/>   
         {props.password? <img className="passwordInput" onClick={()=>{ view? setView(false): setView(true)}} src={view? passwordClosed: passwordOpen}/> : null}

        </div>
        
    }
          
    
        </div>
    )
}

export default Input;