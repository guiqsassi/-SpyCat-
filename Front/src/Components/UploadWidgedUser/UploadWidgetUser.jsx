import Axios from "axios";
import { useState } from 'react';
import { useDispatch } from "react-redux";
import add from "../../images/circle-addRose.png";
import "./UploadWidgetUser.css";
const UploadWidgetUser = () => {
    const [file, setFiles] = useState()
    const [resImg, setResImg] = useState()
    const dispatch = useDispatch()
    const uploadImage = (files)=>{
        if(files[0]){
            const formData = new FormData()
        formData.append("file", files[0])
        formData.append("upload_preset", "ml_default")

        Axios.post("https://api.Cloudinary.com/v1_1/guiqsassi/image/upload",formData).then((res)=>{
            const url =  res.data.url   
        dispatch({
                type: "user/newUrl",
                payload: {
                    url: url
                }

            })
            console.log(url);
            setResImg(res.data.url)
        })
        }
        
    };


    return ( 
        <>
 
        <form className="fileUser" style={resImg?  {backgroundImage: `url(${resImg}`} : null }>
        
        <input type="file"  className="upload" onChange={(e)=>{
            uploadImage(e.target.files)
        }}/>
        <div className="formCenter">
        <label className="labelUploadUser" style={resImg?  {color: "white"} : null }> Enviar imagem</label>
        <img src={add} className="addImgUser" />
        </div>
        </form>



       

        </>
     );
}
 
export default UploadWidgetUser;