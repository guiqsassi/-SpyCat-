import "./UploadWidget.css"
import { useDispatch } from "react-redux";
import React, { useState, useEffect, useRef } from 'react';
import Axios from "axios";
import add from "../../images/circle-addRose.png"
import ReactLoading from 'react-loading';


const UploadWidget = () => {
    const [file, setFiles] = useState()
    const [loading, setLoading] = useState(true)
    const [resImg, setResImg] = useState()
    const dispatch = useDispatch()
    const uploadImage = async (files)=>{
        const formData = new FormData()
        formData.append("file", files[0])
        formData.append("upload_preset", "wtegldxha")
        setLoading(false)

        await Axios.post("https://api.Cloudinary.com/v1_1/guiqsassi/image/upload",formData).then((res)=>{
            const url =  res.data.url   
        dispatch({
                type: "url",
                payload: {
                    url: url
                }

            })
            setResImg(res.data.url)
            console.log(res.data);
            setLoading(true)
        })

    };


    return ( 
        <>
        <form className="file" >
        {loading?
        <>
        
        <input type="file"  className="upload" onChange={(e)=>{
            uploadImage(e.target.files)
        }}/>
        <div className="formCenter">
        <label className="labelUpload"> Enviar imagem</label>
        <img src={add} className="addImg" />
        </div>
        </>
        
    :
        <>
              <div className="formCenter">
              <ReactLoading className="loading" type={"spin"} color={"pink"} height={50} width={50} />

        </div>
        </>
}
        </form>

       

        </>
     );
}
 
export default UploadWidget;