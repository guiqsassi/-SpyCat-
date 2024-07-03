import "leaflet/dist/leaflet.css";
import { useState } from "react";
import Input from "../../Components/Input/Input";
import UploadWidget from "../../Components/UploadWidged/UploadWidget";
import "./OngCad.css";
import Nav from "../LandingPage/Nav/Nav";

import { useSelector } from "react-redux";
import ButtonForms from "../../Components/ButtonForms/ButtonForms";
import Map from "../../Components/Map/Map";
import Select from "../../Components/Select/Select";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Api from "../../Api/Api";

const OngCad = () => {
  const navigate = useNavigate()
  const [markerPosition, setMarkerPosition] = useState(null);
  const [nomeFantasia, setNomeFantasia] = useState("");
  const [cnpj, setCnpj] = useState("");
  const [Cor, setCor] = useState("");
  const [email, setEmail] = useState("");
  const [telefone, setTelefone] = useState("");
  const [status, setStatus] = useState("");
  

  const { city, state, type, latitude, longitude } = useSelector(
    (state) => state.mapReducer
  );


  const date = new Date().toJSON();

    const handleClickRegister = ()=>{
        const location = {
            latitude: latitude,
            longitude: longitude,
            date: date,
          };
        if(cnpj != "" && telefone != "" && email != "" && nomeFantasia != "" && location.latitude != 0)
        axios(
        
{   method: "post",  
    url: `${Api}/ongs`, 
      data: {
            tradingName: nomeFantasia,
            cnpj: cnpj,
            email: email,
            phone: telefone,
            location: location
        }
      }
        ).then((res)=>{ 
            navigate("/")
            console.log(res);
        }
        
        ).catch((err)=>{
            if(err.code == "ERR_BAD_RESPONSE"){
                console.log("Ong Já Cadastrada");
            }
            console.log(err.code);
        })
        
    }

    return ( 
        <section className="OngCad">
      <Nav></Nav>
      <div className="OngCreateContainer">
        <div className="banner">
          <div className="bannerTittle">
            <h1>Novo Pet</h1>
          </div>
          <div className="bannerContent">
            <div className="OngBannerInputs">
              <Input name={"Nome Fantasia"}  placeholder={"Nome Fantasia"} value={nomeFantasia} setValue={setNomeFantasia}></Input>
              <Input name={"Cnpj"} placeholder={"Cnpj"} value={cnpj} setValue={setCnpj}></Input>
              <Input name={"Telefone"} placeholder={"Telefone"} value={telefone} setValue={setTelefone}></Input>
              <Input name={"Email"} placeholder={"Email"} value={email} setValue={setEmail}></Input>

             
            </div>
            <div className="OngMaps">
              <Map placeMarker={true}></Map>
            </div>
          </div>
          <div className="confirmButton">
            <ButtonForms name="Cadastrar" onClick={handleClickRegister}></ButtonForms>
          </div>
        </div>
      </div>
    </section>

     );
}
 
export default OngCad;