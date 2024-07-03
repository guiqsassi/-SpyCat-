import "./Comment.css"
import userIcon from "../../images/Vector.png"
import { BiUserCircle } from "react-icons/bi"
type CommentProps = {
    url?: string,
    username: string,
    UserID: BigInt,
    text: string
}

const Comment = (props: CommentProps ) => {
    return ( 
        <div className="CommentContainer">
            <div className="UserCommentInfo">
                {props.url?
                    <img src={props.url}/>
                    :
                    <BiUserCircle size={40} color="#F98AAE"></BiUserCircle>

                }
                <h4>{props.username}</h4>
            </div>
            <div className="TextCommentContainer">
                <p>{props.text}</p>
            </div>
        </div>

     );
}
 
export default Comment;