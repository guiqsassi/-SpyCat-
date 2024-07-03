import "./Button.css"

type ButtonProps = {
    name: String;
    HandleClick?: () => void;
    
}
const Button = (props:ButtonProps) =>{
    return(
        <>
            <button> {props.name}</button>

        </>
    )
    
}
export default Button