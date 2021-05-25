"use strict"
const cors_url = 'https://cors-anywhere.herokuapp.com'
const yelp_search_url = 'https://api.yelp.com/v3/businesses/search'


function clientCallback() {
    console.log('IT WORKS')
}

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}


$.ajaxSetup({ cache: false });

var offsetRandomNumber = getRandomInt(0, 20)
var arrayRandomNumber = getRandomInt(0,50)

//<!---- TERM: TYPE OF BUSINESS BEING SEARCHED

//       LOCATION: SAN ANTONIO ZIPCODE

//       LIMIT: # OF RESULTS GENERATED IN ARRAY (MAX OF 50?)

//       OFFSET: IF MORE THAN 50 ADJUST OFFSET ------->


var requestObj= {
    'url': cors_url + '/' + yelp_search_url,
    'data': {term: 'restaurants', location: '78247', limit: 50, offset: offsetRandomNumber},
    headers: {
        'Authorization':'Bearer ' + YELP_TOKEN,
        'Cache-Control': 'no-cache, no-store, must-revalidate',
        'Pragma': 'no-cache',
        'Expires': '0'
    },


    error: function(jqXHR, textStatus, errorThrown) {
        console.log('AJAX error, jqXHR = ', jqXHR, ', textStatus = ', textStatus, ', errorThrown =', errorthrown)
    }
}


$("button").click(function(e) {
    e.preventDefault();
    // var randomRestaurantName = ""

    $('#results').empty()

$.ajax(requestObj)
    .done(function(data) {

        console.log('typeof data = ' + typeof data)
        console.log('data = ', data)

         // randomRestaurantName = data.businesses[arrayRandomNumber].name
        console.log(data.businesses[arrayRandomNumber].name);

        $('#results').append(data.businesses[arrayRandomNumber].name + "");

        // console.log(offsetRandomNumber)
        // console.log(arrayRandomNumber)
        // var aliasB = data.businesses.alias;
        // $('#results').append('<br>Business ID: ' + id + '');
        //<---- PULLING FIRST RESULT FROM ARRAY. USE # RANDOM # GENERATOR TO POPULATE RANDOM RESTAURANT
    })

});


