import Button from "../Button/Button";
import "./ButtonForms.css"

type ButtonFormsProps = {
    name: string,
    onClick?: (e: React.MouseEvent<HTMLButtonElement>)=> void;
    delete?: boolean;
}

const ButtonForms = (props: ButtonFormsProps)=>{
    return(
        <>
        <button type={"submit"} style={props.delete? { backgroundColor: "#f34336"}: {}} value={"Send"} onClick={props.onClick} className="ButtonForms">{props.name}</button>
        </>
    )
}
export default ButtonForms