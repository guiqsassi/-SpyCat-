import L from "leaflet"


const markerIcon = new L.Icon({
    iconUrl: require("../../images/locator.png"),
    iconSize: [30, 30],
    iconAnchor: [17, 46], //[left/right, top/bottom]
    popupAnchor: [0, -46], //[left/right, top/bottom]
  });
  
  export default markerIcon;