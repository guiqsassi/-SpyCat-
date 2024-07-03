import React, { useState } from 'react';
import { MapContainer, TileLayer, useMap, Marker, Popup, useMapEvents } from 'react-leaflet';
import L from "leaflet"
import "leaflet/dist/leaflet.css"
import { useDispatch } from "react-redux"
import 'leaflet/dist/leaflet.css';
import PlaceMarkerOnClick from './PlaceMarkerOnClick';
import axios from 'axios';
import { Children } from 'react';
import markerIcon from './MarkerIcon';
import { useSelector } from 'react-redux';
const Map = (props) => {
    const dispatch = useDispatch()
    const [positionMap, setPosition] = useState([-27.548258, -48.498994])
    const {position} = useSelector(state=> state.mapReducer)


   function FindUser() {
    const map = useMapEvents({
    click() {
      map.locate()
    },
    locationfound(e) {
      setPosition(e.latlng)
      map.flyTo(e.latlng, map.getZoom())
          dispatch({
        type: "newPosition",
        payload:{
            position: e.latlng
        }
    })
    console.log(position)
    },
  }) 
return 
} 

    return ( 
        <MapContainer
        id="map"
        className="mapAdd"
        center={ position}
        zoom={20}
        scrollWheelZoom={true}
      >
        <TileLayer
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
       {props.findUser? 
        <FindUser></FindUser>:null   
    }
       {props.placeMarker? 
        <PlaceMarkerOnClick></PlaceMarkerOnClick>:null   
    }
      {Children.map(props.children,child =>
        <>
                {child}
        
        </>
      
      )}
      </MapContainer>
     );
}
 
export default Map;