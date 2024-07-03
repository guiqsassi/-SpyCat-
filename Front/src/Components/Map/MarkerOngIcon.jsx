import L from "leaflet"


const markerIconOng = new L.Icon({
    iconUrl: require("../../images/locationOngMarker.png"),
    iconSize: [30, 30],
    iconAnchor: [17, 46], //[left/right, top/bottom]
    popupAnchor: [0, -46], //[left/right, top/bottom]
  });
  
  export default markerIconOng;