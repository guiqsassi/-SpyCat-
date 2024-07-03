import "./About.css"

import GatoCaixa from "../../../images/gatoCaixa.png"
import GatoCome from "../../../images/GatoCome.png"
import prancheta from "../../../images/prancheta.png"
const About = ()=>{
    return(
        <section className="About" id="about">
            <div className="Banner">
                <ul>
                    <li>
                        <img src={GatoCome} alt="A imagem representa um gato branco com pintas laranjas olhando uma rosquinha de cor rosada com granulados sendo segurada por uma linha" />
                        <h2>Cadastre Animais</h2>
                        <a target="blank" href="https://br.freepik.com/vetores-gratis/gato-bonito-comendo-rosquinha-dos-desenhos-animados-ilustracao-de-icone-de-vetor-de-comida-animal-conceito-de-icone-isolado-plano_24563995.htm#page=2&query=cat%20cartoon&position=21&from_view=search&track=ais">Imagem de catalyststuff no Freepik</a> 
                    </li>
                    <li>
                        <img src={GatoCaixa} alt="A imagem representa um gato branco com pintas laranjas dentro de uma caixa, o mesmo possui um emoji de coração ao lado" />
                        <h2>Resgate pets perdidos!</h2>
                        <a target="blank" href="https://br.freepik.com/vetores-gratis/gato-bonito-jogando-na-caixa-dos-desenhos-animados-ilustracao-de-icone-vetorial-natureza-animal-conceito-de-icone-isolado-plano_27313237.htm#page=2&query=cat%20cartoon&position=24&from_view=search&track=ais">Imagem de catalyststuff no Freepik</a>                     </li>
                    <li>    
                    <img src={prancheta} alt="a imagem representa um papel sendo segurado por uma prancheta" />
                        <h2>Ajude ONGs a encontrarem animais</h2>
                        <a target="blank" href="https://br.freepik.com/vetores-gratis/prancheta-papel-e-ilustracao-do-icone-dos-desenhos-animados-do-lapis-conceito-de-icone-de-objeto-de-educacao-isolado-estilo-flat-cartoon_10848390.htm#page=5&position=29&from_view=author">Imagem de catalyststuff no Freepik</a> 
                    </li>
                </ul>
            </div>
        </section>
    )

}
export default About;