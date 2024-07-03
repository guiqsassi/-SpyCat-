import React, { useState } from 'react';
import { Marker, Popup, useMapEvents } from 'react-leaflet';
import "leaflet/dist/leaflet.css"
import axios from 'axios';
import { useDispatch } from "react-redux"
import { UseSelector,  } from 'react-redux/es/hooks/useSelector';

import markerIcon from './MarkerIcon';

const PlaceMarkerOnClick = () => {
    const dispatch = useDispatch()

    const [markerPosition, setMarkerPosition] = useState(null)
    const [city, setCity] = useState("")
    const [state, setState] = useState("")
    const [type, setType] = useState("")





    useMapEvents({
      click (e) {
        setMarkerPosition([e.latlng.lat, e.latlng.lng])
        axios.post(`https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/reverseGeocode?f=pjson&langCode=EN&location=${e.latlng.lng},${e.latlng.lat}`).then((res)=>{
            setCity(res.data.address.City)
            setState(res.data.address.Region)
            setType(res.data.address.Type);
            dispatch({
                type: "mapNewMarker",
                payload:{
                    city: res.data.address.City,
                    state: res.data.address.Region,
                    type: res.data.address.Type,
                    latitude: e.latlng.lat,
                    longitude: e.latlng.lng
                }
            })
        })

       
      },
    })     
    return markerPosition === null ? null : (
        <Marker icon={markerIcon} position={markerPosition}>
          <Popup className="popup">{city} <br></br> {state}</Popup>
        </Marker>
      )
}

export default PlaceMarkerOnClick