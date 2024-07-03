import NavSlide from "../../Components/NavSlide/NavSlide"
import ButtonForms from "../../Components/ButtonForms/ButtonForms"
import Select from "../../Components/Select/Select"
import Button from "../../Components/Button/Button"
import filter from "../../images/filter.png"
import PetView from "../../Components/PetView/PetView"
import "./Home.css"
import {useEffect, useState} from 'react';
import axios from "axios"
import { useNavigate } from "react-router-dom"
import Api from "../../Api/Api"
import { useSelector } from "react-redux"
const Home = ()=>{
    const {logged} = useSelector((state)=>state.userReducer)
    console.log(logged);
    const [pets, setPets] = useState([{}])
    const [page, setPages]= useState(0)
    const navigate = useNavigate()
    const getPets = async()=>{
        axios.get(Api+ "/pets", {
            params:{
                page: page,
                size: 10,
                sort: "color"
            }
        }).then((res)=> {
            setPets( [...pets, ...res.data] )
             console.log(res.data);
            })
    }
    
    useEffect(
        ()=>{
            getPets()
        }
    ,[page])
    useEffect(
        ()=>{
            window.addEventListener("scroll", handleScroll)
            
            return()=> window.removeEventListener("scroll", handleScroll)
        }
        ,
    )
    function handleScroll(){
        if(window.innerHeight + document.documentElement.scrollTop < document.documentElement.offsetHeight){
            return console.log("deu por hj");;
        }
        setPages(page + 1)

    }
    return(
        <section className="Home">
            <NavSlide></NavSlide>
            <div className="top">
                <div className="tittle">
                    <h1>Encontre Pets</h1>
                </div>
                <div className="infoZone">
                    <div className="filterByData">
                    <Select label="Ordenar por data" options={["op1","op2"]} defaultValue="mais recentes"></Select>
                    </div>
                    <div className="newPetButton">
                        <div className="petsCounter">
                            <p>{pets.length} animais</p>
                        </div>
                        <div className="cadNewPet">
                           {logged?
                            <button onClick={()=>{
                                navigate("/addPet")
                            }}> Cadastrar novo pet</button>:null}

                        </div>
                    </div>
                </div>
            </div>
            <main className="content">
                <div className="filter">
                    <div className="filterTittle">
                    <img src={filter} alt="" />
                    <h3>Filtros</h3>
                    </div>
                    <div className="filterBanner">
                    <div className="filters">
                    <h5>Tipo de cadastro</h5>
                    <ul>
                        <li>
                            <input type="checkbox" />
                            <p>Abandonado</p>
                        </li>
                        <li>
                            <input type="checkbox" />
                            <p>Perdido</p>
                        </li>
                    </ul>

                    <h5>Espécie</h5>
                    <ul>
                        <li>
                            <input type="checkbox" />
                            <p>Gato</p>
                        </li>
                        <li>
                            <input type="checkbox" />
                            <p>Cachorro</p>
                        </li>
                        <li>
                            <input type="checkbox" />
                            <p>Outro</p>
                        </li>
                    </ul>
                    <h5>Cor</h5>
                    <ul>
                        <li>
                            <input type="checkbox" />
                            <p>Vira-lata</p>
                        </li>
                        <li>
                            <input type="checkbox" />
                            <p>Outro</p>
                        </li>
                    </ul>
                    </div>
                
                    </div>
                </div>
                <div className="pets">
                <div className="petsWrap">
                    {pets? pets.map((pet)=>{
                        
                        if(pet.images){

                            return <PetView image={pet.images[0].url} pet={pet}></PetView>
                        }
                        
                        
                        
                    })
                    : null}
                </div>
                </div>
            </main>
        </section>
    )
}
export default Home