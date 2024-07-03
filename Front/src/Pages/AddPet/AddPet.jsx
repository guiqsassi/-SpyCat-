import "./AddPet.css";
import NavSlide from "../../Components/NavSlide/NavSlide";
import Input from "../../Components/Input/Input";
import "leaflet/dist/leaflet.css";
import { useState, useEffect} from "react";
import UploadWidget from "../../Components/UploadWidged/UploadWidget";
import { BiSolidXCircle } from "react-icons/bi";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import ButtonForms from "../../Components/ButtonForms/ButtonForms";
import { useSelector, useDispatch } from "react-redux";
import Select from "../../Components/Select/Select";
import Api from "../../Api/Api";
import Map from "../../Components/Map/Map";
import { image } from "@cloudinary/url-gen/qualifiers/source";
import NotificationError from "../../Components/Notification/NotificationError";
const AddPet = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [markerPosition, setMarkerPosition] = useState(null);
  const [description, setDescription] = useState("");
  const [especie, setEspecie] = useState("");
  const [Cor, setCor] = useState("");
  const [email, setEmail] = useState("");
  const [telefone, setTelefone] = useState("");
  const [status, setStatus] = useState("");
  const [view, setView] = useState("");
  const [notificationError, setNotificationError] = useState(false);
  const [errorText, setErrorText] = useState("");

  const [urlArray, setUrlArray] = useState([]);

  const { city, state, type, latitude, longitude } = useSelector(
    (state) => state.mapReducer
  );
  const { userID } = useSelector(
    (state) => state.userReducer
  );
  
  const {url}  = useSelector(
    (state) => state.petReducer
  );
    // console.log(url);


  useEffect(
    ()=>{
      if(url != "" && !urlArray.includes(url)){
        setUrlArray([...urlArray, url])
        console.log(url);
      }
    }
  ,[url])



  const date = new Date().toJSON();

  const handleClick = async () => {
    const url ="https://vivoverde.com.br/wp-content/uploads/2010/02/sphynx_cats_1a_thumb.jpg";
    const location = {
      id: 0,
      latitude: latitude,
      longitude: longitude,
      date: date,
    };
    console.log(Cor, especie, city, state, location, status, url);
    if (
      type.toLocaleLowerCase() !== "ocean" &&
      latitude &&
      url !== "" &&
      description !== "" &&
      Cor !== "" &&
      especie !== "" &&
      status == "Abandonado"
    ) 
    {
      console.log(urlArray)

      await axios(
        {
        method: "post",
        url: `${Api}/pets`, 
        data: {
          id: 0,
          description: description,
          color: Cor,
          specie: especie,
          city: city,
          state: state,
          location: location,
          images: 
            urlArray.map((url)=>{
              return ({
                id: 0,
                url: url,
                date: date
              })
            })
          ,
          status: status.toUpperCase(),
          user:{ id: userID}
        },       
        headers:{
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      }
        )
        .then((res) => {
          console.log(res.data);
          navigate("/home");
          dispatch({
            type: "clear"
          })
        }).catch((err)=>{
          console.log(err.text);
          setNotificationError(true)
          setTimeout(()=>{setNotificationError(false)}, 7000);  
          setErrorText("Houve algum erro com seu envio") 
        });
    }
    
    else if(
      type.toLocaleLowerCase() !== "ocean" &&
      latitude &&
      url !== "" &&
      description !== "" &&
      Cor !== "" &&
      especie !== "" &&
      status == "Perdido" &&
      email !== "" &&
      telefone !== ""
    ){
      await axios
      (
        {
          method: "post",
          url: `${Api}/lostPets`, 
        data: {
        id: 0,
        description: description,
        color: Cor,
        specie: especie,
        city: city,
        state: state,
        location: location,
        images: 
        urlArray.map((url)=>{
          return ({
            id: 0,
            url: url,
            date: date
          })
        }),
        status: status.toUpperCase(),
        user:{ id: userID},
        phone: telefone,
        email: email
      },
      headers:{
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    }
      )
      .then((res) => {
        console.log(res.data);
        navigate("/home");
        dispatch({
          type: "clear"
        })
      }).catch((err)=>{
        console.log(err.text);
        setNotificationError(true)
        setTimeout(()=>{setNotificationError(false)}, 7000);  
        setErrorText("Houve algum erro com seu envio") 
      });
    }
    else{
      setNotificationError(true)
      setTimeout(()=>{setNotificationError(false)}, 7000);  
      setErrorText("Insira todas as informações") 

    }
  
  };
  const handleClickDeletePetImage = (Image)=>{
    console.log(Image);
    const index = urlArray.filter((url)=>
      url !== Image
    )
    setUrlArray(index)
  }

  return (
    <section className="addPet">
      <NotificationError text={errorText} state={notificationError}></NotificationError>
      <NavSlide></NavSlide>
      <div className="petCreateContainer">
        <div className="banner">
          <div className="bannerTittle">
            <h1>Novo Pet</h1>
          </div>
          <div className="bannerContent">
            <div className="BannerInputs">
              <div className="cadType">
                <div className="TypeTittle">
                  <h3>Tipo de cadastro</h3>
                </div>
                <div className="checkboxes">
                  <Select
                    options={["Abandonado", "Perdido"]}
                    defaultValue="Faça sua escolha"
                    onChange={(e) => {
                      if (e.target.value == "Perdido") {
                        setView(true);
                      } else {
                        setView(false);
                      }
                      setStatus(e.target.value);
                    }}
                  ></Select>
                </div>
              </div>
              <Input
                name={"Espécie"}
                value={especie}
                setValue={setEspecie}
              ></Input>
              <Input name={"Cor"} value={Cor} setValue={setCor}></Input>
              <Input
                name={"Descrição"}
                value={description}
                setValue={setDescription}
                textArea={true}
              ></Input>
              {view ? (
                <>
                  <Input
                    name={"Email de Contato"}
                    setValue={setEmail}
                    value={email}
                  ></Input>
                  <Input
                    name={"Telefone"}
                    setValue={setTelefone}
                    value={telefone}
                  ></Input>
                </>
              ) : null}
            </div>
            <div className="imagesMaps">
              <UploadWidget></UploadWidget>
              <div className="imagesSelection">
                
              {urlArray[0]?
              urlArray.map((image)=>{
                return(
                  <>
                  <div className="uploadedImageView">
                  <BiSolidXCircle className="deleteImage" color="F98AAE" onClick={()=>{
                    handleClickDeletePetImage(image)
                  }} size={25} style={{cursor: "pointer"}} ></BiSolidXCircle>
                  <img src={image} alt="" />
                  </div>
                  </>
                )
              })
              :null
              
            }
              </div>
              <Map findUser={true} placeMarker={true}></Map>
            </div>
          </div>
          <div className="confirmButton">
            <ButtonForms name="Cadastrar" onClick={handleClick}></ButtonForms>
          </div>
        </div>
      </div>
    </section>
  );
};

export default AddPet;
