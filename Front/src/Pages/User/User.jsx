import NavSlide from "../../Components/NavSlide/NavSlide";
import "./User.css"
import { BiSolidXCircle, BiUserCircle } from "react-icons/bi";
import PetView from "../../Components/PetView/PetView";
import Edit from "../../images/edit.png"
import React, { useState, useRef, useEffect } from 'react';
import arrow from "../../images/Arrow.png"
import Input from "../../Components/Input/Input";
import Select from "../../Components/Select/Select";
import ButtonForms from "../../Components/ButtonForms/ButtonForms";
import UploadWidgetUser from "../../Components/UploadWidgedUser/UploadWidgetUser";
import { useSearchParams, useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import NotificationError from "../../Components/Notification/NotificationError"
import NotificationOkay from "../../Components/Notification/NotificationOkay"


import axios from "axios";
import Api from "../../Api/Api";
const User = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [notification, setNotification] = useState(false)
    const [notificationError, setNotificationError] = useState(false)
    const [errorText, setErrorText] = useState("")

    const {userIconUrl} = useSelector((state)=>state.userReducer)
    const {userID} = useSelector((state)=> state.userReducer)
    const {token} = useSelector((state)=> state.userReducer)
    console.log(token);
    const [deleteModal, setDeleteModal] = useState("none")
    const [searchParams] = useSearchParams()
    const [estado, setEstado] = useState()
    const [cidades, setCidades] = useState([])
    const [cidade, setCidade] = useState()
    const [display, setDisplay] = useState("none")
    const siglasEstadosBrasil = ["AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"];
    const id = searchParams.get("id")
    const [name, setName] = useState("")    
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")


    const [deletePassword, setDeletePassword] = useState("")

    const couroselfav = useRef(null)
    const courosel = useRef(null)
    const [user, setUser] = useState({ pet:[]})

        const getUser = async()=>{
        await axios(
            {
                method: "get",
                url:`${Api}/users/${id}`,
                headers:{
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            }
            ).then((res)=>{
            console.log(res.data);
            setEmail(res.data.email);
            setName(res.data.username)
            setUser(res.data)
        })
    }
    useEffect(
        ()=>{
            getUser()
        }
        
    ,[userID])
    const pet = {
        "id": 1,
    "characteristics": "asdas",
    "description": "dasdasdasf",
    "city": "Florianópolis",
    "state": "Santa Catarina",
    "url": "http://res.cloudinary.com/guiqsassi/image/upload/v1698510521/9a3c3fb5f73822af8514df07f6676392_lmu1p8.gif",
    "status": null,
    "locations": [],
    "images": [],
    "user": null,
    "comment": null,
    "lat": -27.543719484487116,
    "lng": -48.500416874885566
    }

    const onClickRightFav = (e)=>{
        e.preventDefault()
        couroselfav.current.scrollLeft += couroselfav.current.offsetWidth
        
    }
    const onClickLeftFav = (e)=>{
        e.preventDefault()
        couroselfav.current.scrollLeft -= couroselfav.current.offsetWidth
        
    }
    const onClickRight = (e)=>{
        e.preventDefault()
        courosel.current.scrollLeft += courosel.current.offsetWidth
        
    }
    const onClickLeft = (e)=>{
        e.preventDefault()
        courosel.current.scrollLeft -= courosel .current.offsetWidth
        
    }

    const handleStateSelected =async (e)=>{
        const estadoEscolhido =(e.target.value)
        setEstado(estadoEscolhido)
        setCidades([])
        await axios.get(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${estadoEscolhido}/municipios`).then((res)=>{
            const resposta = res.data

            resposta.map((municipio)=>{
                if(!cidades.includes(municipio.microrregiao.nome))(
                        setCidades((prev) =>  [...prev, municipio.nome ])

                )
                // setCidades([...cidades, municipio.microrregiao.nome])
            })
        })
    }
    

    
    const handleUpdateClick = async()=>{
        console.log(cidade)
        console.log(estado) 
        await axios.put(`${Api}/users`,{
            id: userID,
            username: name,
            email: email,
            city: cidade,
            state: estado,
            icon: userIconUrl,
            password: password
        }, {headers:{ 
            Authorization: `Bearer ${localStorage.getItem("token")}`

        }}).then((res)=>{
            setDisplay("none")
            setNotification(true)
            setTimeout(()=>{setNotification(false)}, 7000);  
            getUser()
        }).catch((err)=>{
            setErrorText("Infelizmente houve algum erro interno :(")
            setNotificationError(true)

            setTimeout(()=>{setNotificationError(false)}, 7000);  
        })
    }

    const handleDeleteClick = async()=>{
        deletePassword?
        await axios(
            {   
                method: "delete",
                url:`${Api}/users/${userID}`,
                params: {
                    password: deletePassword
                },
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
    }).then((res)=>{
            dispatch({
                type: "user/logOut"
            })
            navigate("/")
            console.log(res);
        }).catch((err)=>{
            setErrorText("Infelizmente houve algum erro interno :(")
            setNotificationError(true)

            setTimeout(()=>{setNotificationError(false)}, 7000);  
        }):
        setErrorText("Escreva sua senha ><")
            setNotificationError(true)

            setTimeout(()=>{setNotificationError(false)}, 7000);  
    }
    return (
        <>
        <NavSlide/>
        <NotificationError text={errorText} state={notificationError}></NotificationError>
        <NotificationOkay state={notification}></NotificationOkay>
        <section className="UserSection">
            <div className="userInformation">
                
                {user?
                user.icon?
                <img src={user.icon} className="userIcon"/>
            : <BiUserCircle size={120} color="#F98AAE"></BiUserCircle>
            : <BiUserCircle size={120} color="#F98AAE"></BiUserCircle>    
            }
                
                <div className="userEdit">
                 <h3>Olá {name}</h3> 
                 {user?
                    userID== user.id? 
                    <img src={Edit} className="editIcon" style={{cursor: "pointer"}} onClick={()=>{setDisplay("flex")}}/>
                    :null
                    :null
                }
                </div>
            </div>
            <div className="userPets">
                <div className="carrosels">

{                
                    user.pet[0]?
                    <div className="PetsCarrosel">
                    <div className="tittlePets">
                    <h3>Meus pets</h3>
                    <h4> Veja Mais</h4>
                    </div>
                    <div className="carouselArrows">
                    <img src={arrow} onClick={onClickLeft} className="arrow"/>

                    <div className="petCards" ref={courosel}>
                        {user?
                            user.pet.map((pet)=>{
                                return <PetView pet={pet} image={pet.images[0].url}></PetView>
                            }) 
                            :
                            null
                    }
                    </div>
                    <img src={arrow} onClick={onClickRight} style={{transform: "scaleX(-1)"}} className="arrow"/>

                    </div>
                    </div>
                    :null
                    }
                    <div className="PetsCarrosel">
                    <div className="tittlePets">
                    <h3>Pets Favoritos</h3>
                    <h4> Veja Mais</h4>

                    </div>
                    <div className="carouselArrows">
                    <img src={arrow} onClick={onClickLeftFav} className="arrow"/>

                    <div className="petCards" ref={couroselfav}>
                    <PetView image={"https://cdn.britannica.com/39/7139-050-A88818BB/Himalayan-chocolate-point.jpg"} pet={pet}></PetView>
                    <PetView image={pet.url} pet={pet}></PetView>
                    <PetView image={pet.url} pet={pet}></PetView>
                    <PetView image={pet.url} pet={pet}></PetView>
                    <PetView image={pet.url} pet={pet}></PetView>
                    <PetView image={pet.url} pet={pet}></PetView>
                    <PetView image={pet.url} pet={pet}></PetView>
                    <PetView image={pet.url} pet={pet}></PetView>
                    <PetView image={pet.url} pet={pet}></PetView>
                    </div>
                    <img src={arrow} onClick={onClickRightFav} style={{transform: "scaleX(-1)"}} className="arrow"/>

                    </div>
                    </div>

                    
                </div>
            </div>
           
        </section>
        <div className="modalUser" style={{display: display}}>
                <div className="modalContainerUser">
                    <div className="modalTittle">
                        <h3>Editar Cadastro</h3>
                        <BiSolidXCircle  color="F98AAE" size={35} style={{marginRight: "20px", cursor: "pointer"}} onClick={()=>{ setDisplay("none")}}></BiSolidXCircle>
                    </div>
                    <div className="userEditContainer">
                    <UploadWidgetUser></UploadWidgetUser>
                    <Input icon={"https://i.postimg.cc/8CV7NZSr/Vector.png"} placeholder="Nome" value={name} setValue={setName} name={"Nome"}></Input>
                <Input placeholder="Email" icon={"https://i.postimg.cc/VktJx338/email.png"} value={email} setValue={setEmail} name={"Email"}></Input>
                <Input password="password" placeholder="Senha" icon={"https://i.postimg.cc/MKNcrW35/cadeado.png"} value={password} setValue={setPassword} name={"Senha"}></Input>
                        <Select label={"Estado"} defaultValue={"Insira o seu estado"} onChange={handleStateSelected} options={ siglasEstadosBrasil}/>
                        <Select dataList={true} list="cidades" label={"Cidade"} defaultValue={"Insira o seu estado"} onChange={(e)=>{setCidade(e.target.value)}} options={ cidades}/>
                    <ButtonForms name="atualizar" onClick={handleUpdateClick}></ButtonForms>
                    <ButtonForms name="deletar" onClick={()=>{ setDeleteModal("flex")}}></ButtonForms>
                    </div> 
                </div>
            </div>

        <div className="modalDelete" style={{display: deleteModal}}>
            <div className="deleteModalContainer">
                <div className="modalDeleteTittle">
                    <h1>Está certo disso?</h1>
                </div>
                <div className="inputDelete" >
                    <Input name={"senha"} password={"password"} value={deletePassword} setValue={setDeletePassword}></Input>
                    <ButtonForms delete={true} onClick={handleDeleteClick} name="confirmar"></ButtonForms>
                    <ButtonForms name="cancelar" onClick={()=>{ setDeleteModal("none")}}></ButtonForms>
                </div>
            </div>
        </div>
        </>
      );
}
 
export default User;