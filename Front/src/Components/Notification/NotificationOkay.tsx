import "./NotificationOkay.css"
import {useState } from 'react';

type NotificationProps = {
    state: Boolean
}

 function NotificationOkay(props:NotificationProps) {
    let notification

    if(props.state == true){
        notification = "0px"
    }
    else{
        notification = "300px"
    }
    return (
        <div className="notificationContainer" style={{transform: `translateX(${notification})`}}>
            <h2>Ação realizada com sucesso 😸 </h2>
        </div>
    )
  
}
export default NotificationOkay