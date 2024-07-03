import "./NotificationError.css"
import {useState } from 'react';

type NotificationProps = {
    state: boolean,
    text: String
}

 function NotificationError(props:NotificationProps) {
    let notification

    if(props.state){
        notification = "0px"
    }
    else{
        notification = "300px"
    }
    return (
        <div className="notificationErrorContainer" style={{transform: `translateX(${notification})`}}>
            <h2>{props.text} </h2>
        </div>
    )
  
}
export default NotificationError