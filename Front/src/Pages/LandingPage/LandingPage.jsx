import Nav from "./Nav/Nav"
import "./LandingPage.css"
import About from "./About/About"
import Ong from "./Ong/Ong"
import Support from "./Support/Support"
import Initial from "./Initial/Initial"
const LandingPage = ()=>{
    return(
        <>
                    <Nav></Nav>
                    <Initial></Initial>
                    <About></About>
                    <Ong></Ong>
                    <Support></Support>
        </>
    )
}
export default LandingPage