'use strict';

//---------------- MAPBOX ----------------------//>

// mapboxgl.accessToken = '<bd3ec46a553181ff8da235a51eb233fd>';
var map = new mapboxgl.Map({
    container: 'map', // container ID
    style: 'mapbox://styles/mapbox/streets-v11', // style URL
    center: [-97.7431, 30.2672], // starting position [lng, lat]
    zoom: 5 // starting zoom

});
