import axios from "axios";
import "leaflet/dist/leaflet.css";
import { useEffect, useRef, useState } from 'react';
import { BiSolidXCircle } from "react-icons/bi";
import { Marker, Popup } from 'react-leaflet';
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useSearchParams } from "react-router-dom";
import Api from "../../Api/Api";
import ButtonForms from "../../Components/ButtonForms/ButtonForms";
import Comment from "../../Components/Comment/Comment";
import Input from "../../Components/Input/Input";
import Map from "../../Components/Map/Map";
import markerIcon from "../../Components/Map/MarkerIcon";
import markerIconOng from "../../Components/Map/MarkerOngIcon";
import NavSlide from "../../Components/NavSlide/NavSlide";
import Select from "../../Components/Select/Select";
import UploadWidget from "../../Components/UploadWidged/UploadWidget";
import fav from "../../images/fav.png";
import favMarkedIMG from "../../images/favMarked.png";
import seta from "../../images/seta.png";
import "./Pets.css";
import NotificationError from "../../Components/Notification/NotificationError"
import NotificationOkay from "../../Components/Notification/NotificationOkay"

const PetsPage = ()=>{
    const [notification, setNotification] = useState(false)
    const [notificationError, setNotificationError] = useState(false)
    const navigate = useNavigate()
    const dispatch = useDispatch()
    const imageCollunm = useRef()
    const [bigImage, setBigImage] = useState()
    const [arrayImageCat, setArrayImageCat] = useState("")
    const [searchParams] = useSearchParams()
    const id = searchParams.get("id")
    const [pet, setPet] = useState()
    const [marker, setMarker] = useState()
    const [position, setPosition] = useState([])
    const [markerPosition, setMarkerPosition] = useState([12,12])
    const [viewRescueModal, setViewRescueModal] = useState("none")
    const [viewEncounterModal, setViewEncounterModal] = useState("none")
    const [favMarked, setFavMarked] = useState(false)
    const [errorText, setErrorText] = useState("")
    const [ongs, setOngs] = useState()
    const [data, setData] = useState()

    const [commentText, setCommentText] = useState("")
    const [comments, setComments] = useState("")
    const date = new Date().toJSON();

    const {userID} = useSelector(state => state.userReducer)
    const {logged} = useSelector(state => state.userReducer)
    const {url} = useSelector(state => state.petReducer)
    const { city, state, type, latitude, longitude } = useSelector(
        (state) => state.mapReducer
      );
    console.log(userID);


    console.log(position);
    const handleFavMarked = ()=>{
        favMarked? 
        setFavMarked(false):
        setFavMarked(true)
    }
    const handleModalRescueClick =()=>{
        if(viewRescueModal === "none"){
            setViewRescueModal("flex")
        }else{
            setViewRescueModal("none")
        }
    }
    const handleViewEncounterModal =()=>{
        if(viewEncounterModal === "none"){
            setViewEncounterModal("flex")
        }else{
            setViewEncounterModal("none")
        }
    }
        const getPets = async()=>{
                await axios.get(`${Api}/pets/${id}`).then((res)=>{
                    setPet(res.data)
                    setMarker(res.data.locations)
                    
                    setPosition([res.data.locations[0].latitude, res.data.locations[0].longitude])
                    setBigImage(res.data.images[0].url)
                    setComments(res.data.comments)
                    dispatch({
                        type: "newPosition",
                        payload:{
                            position: [res.data.locations[0].latitude, res.data.locations[0].longitude]
                        }
                    })
                    console.log(res.data);
                    const indexOfLastElement = res.data.locations.length-1;
                    console.log();
                    console.log(res.data.locations[indexOfLastElement].date);
                    const dataCerta = new Date(res.data.locations[indexOfLastElement].date) 
                    setData(dataCerta.getDate() + "/" + dataCerta.getMonth() +"/" + dataCerta.getFullYear())
                    
                    setArrayImageCat(res.data.images.map(url=>url.url))

                })
        }
        const getOngs = async ()=>{
            await axios.get(`${Api}/ongs`).then((res)=>{
                setOngs(res.data)
                
                console.log(res.data);

            })
        }
      useEffect(
        ()=>{
            getOngs()
            getPets()
        }
      ,[])
        const handleClickUp = ()=>{
            imageCollunm.current.scrollTop -= imageCollunm.current.offsetHeight
        }
        const handleClickBottom = ()=>{
            imageCollunm.current.scrollTop += imageCollunm.current.offsetHeight
        }

        const handleClickCommentCreate= async()=>{
            commentText?
            await axios.post(`${Api}/comments`, {
                user:{ id: userID
                },
                pet: {
                    id: pet.id
                },
                text: commentText,
                date: date 
            },
            {headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`

            }}
            
            ).then((res)=>{
                setNotification(true)
                setTimeout(()=>{setNotification(false)}, 7000);  
                setViewEncounterModal("none")
                console.log(res.data);
                setCommentText("")
                getPets()
            }).catch((err)=>{
                setNotificationError(true)
                setErrorText("Houve um erro com seu comentário")
                setTimeout(()=>{setNotificationError(false)}, 7000);   
            }):
            setNotificationError(true)
            setTimeout(()=>{setNotificationError(false)}, 7000);   
            setErrorText("Insira um texto antes de comentário")

        }
        const handleClickRescue = async()=>{
            await axios.post(`${Api}/pets/rescue`, {
                id: pet.id,
                status: "RESGATADO"
            },{
                headers:{
                    Authorization: `Bearer ${localStorage.getItem("token")}`

                }
            }
            
            ).then((res)=>{
                navigate("/home")
            }).catch((err)=>{
                setNotificationError(true)
                setErrorText("Houve um erro com seu resgate")
                setTimeout(()=>{setNotificationError(false)}, 7000);  
            } 
            )
        }
        const location = {
            id: 0,
            latitude: latitude,
            longitude: longitude,
            date: date,
          };
        const handleClickNewSighting = async()=>{
            console.log(location)
            console.log(url);
            await axios.post(`${Api}/pets/sighting`, {
                id: pet.id,
                image:{
                    id: 0,
                    url: url,
                    date: date,
                    pet: {
                        id: pet.id
                    },
                   

                },
                location: location
            },
            {headers:{
                Authorization: `Bearer ${localStorage.getItem("token")}`

            }}).then((res)=>{
                getPets()
                setNotification(true)
                setTimeout(()=>{setNotification(false)}, 7000);  
                setViewEncounterModal("none")
                dispatch({
                    type: "clear"
                  })
            }).catch((err)=>{
                setNotificationError(true)
                setErrorText("Houve um erro com seu envio de Econtro")
                setTimeout(()=>{setNotificationError(false)}, 7000);  
            })
        }
            
    return(
        <section className="petsSection">
        
            <NavSlide></NavSlide>
            <NotificationError text={errorText} state={notificationError}></NotificationError>
            <NotificationOkay state={notification}></NotificationOkay>
            {pet?
                    <div className="petInfo">
                <div className="imageSection">
                    <div className="images">
                    <div className="bigImage">
                        <img src={bigImage} alt="" />
                    </div>
                    <div className="Collunm" >
                        {arrayImageCat[3]?
                            <img src={seta} style={{maxWidth: "24px", transform: "scaleY(-1)", cursor: "pointer", marginBottom: "5px"}} onClick={handleClickUp} alt="" />
                        :null
                    }
                    <div className="imagesCollums" ref={imageCollunm}>
                    {arrayImageCat? arrayImageCat.map((imag)=>{
                        return(
                            <>
                            
                            <img src={imag} className="imgCollunmSon" onClick={()=>{setBigImage(imag)}} alt="" />
                            </>
                        )
                    }): null
                    }
                    </div>
                    {arrayImageCat[3]?
                        <img src={seta} style={{maxWidth: "24px", cursor: "pointer",  marginTop: "5px"}} onClick={handleClickBottom}  alt="" />
                        :null
                    }
                    </div>
                    
                    
                   </div>
                   
                   <div className="petData">
                        <div className="cadBy">
                            <p>Cadastrado por: {pet.user.username}</p>
                        </div>
                        <div className="favoritar" onClick={handleFavMarked}>
                            {favMarked?
                            <img src={favMarkedIMG} />
                            :
                            <img src={fav}/>
                        }
                        </div>
                        
                    </div>
                    <div className="map">
                 <Map position={position}>
                    {markerPosition?
                    pet.locations.map((markerP)=> {
                        return <Marker icon={markerIcon} position={[markerP.latitude, markerP.longitude]}>
                                <Popup>{markerP.date}</Popup>
                             </Marker>
                        
                    }): null}
                    {ongs? 
                    ongs.map((ong)=>{
                        return <Marker icon={markerIconOng} position={[ong.location.latitude, ong.location.longitude]}>
                        <Popup>{ong.tradingName}</Popup>
                     </Marker>
                    }):null
                }
                 </Map>
                    </div>
                </div>
                <div className="petAbout">
                    <div className="infoBanner">
                        <div className="bannerCotent">
                        <label>Descrição</label>
                        <div className="campoTextoDescricao"> {pet.description} </div>
                        <label>Espécie</label>
                        <div className="campoTexto">{pet.specie}</div>    
                        <label>Cor</label>
                        <div className="campoTexto">{pet.color}</div>    
                        <label>Data de encontro</label>
                        <div className="campoTexto">{data? data: null}</div>    
                        </div>
                    </div>
                       {
                       logged?
                       <>
                       
                       <ButtonForms onClick={handleModalRescueClick} name="Resgatar Pet"></ButtonForms>
                        <ButtonForms onClick={handleViewEncounterModal}  name="Atualizar Encontro"></ButtonForms> 
                       </>
                        : null}
                </div>
            </div>:
            null
        }
    
            <div className="comentarios">
                <div className="tittleComentarios">
                    <h3>Comentários</h3>
                </div>

       {logged?
        <div className="input">
                <label>Compartilhe suas descobertas!!</label>
                <Input textArea={true} value={commentText} setValue={setCommentText} placeholder="sua mensagem"></Input>
                <ButtonForms name="Enviar" onClick={handleClickCommentCreate}></ButtonForms>
                </div>
               : null }
                <div className="Comments">
                {comments?
                comments.map((comment)=>{
                    return <Comment url={comment.user.icon} username={comment.user.username} text={comment.text}></Comment>
                })
                :
                null
                }
                </div>
            </div>


        <div className="NewEnconterModal" style={{display: viewEncounterModal}}>
            <div className="NewEnconterModalContainer">
            <BiSolidXCircle size={30} onClick={handleViewEncounterModal} color="F98AAE" className="close"></BiSolidXCircle>
            <h2>Avistamento de Pet</h2>
            <div className="petInputs">
            <UploadWidget></UploadWidget>
            <Map placeMarker={true} findUser={true}></Map>

            </div>
                <ButtonForms onClick={handleClickNewSighting} name="Enviar"></ButtonForms>
            </div>
        </div>

        <div className="rescueModal" style={{display: viewRescueModal}}>
            <div className="rescueModalContainer">
            <BiSolidXCircle size={30}  onClick={handleModalRescueClick} color="F98AAE" className="close"></BiSolidXCircle>
            <div className="RescueModalTittle">
            <h2>Resgate de Pet</h2>
            </div>
            <div className="rescueModalActions">
            <Select options={["Encontrado", "Levado até Ong"]}></Select>
                <ButtonForms name="Enviar" onClick={handleClickRescue}></ButtonForms>

            </div>
            </div>
        </div>

        </section>
    )
}
export default PetsPage