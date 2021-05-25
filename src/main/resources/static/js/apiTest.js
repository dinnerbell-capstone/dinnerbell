"use strict"
const cors_url = 'https://cors-anywhere.herokuapp.com'
const yelp_search_url = 'https://api.yelp.com/v3/businesses/search'


function clientCallback() {
    console.log('IT WORKS')
}


//<!---- TERM: TYPE OF BUSINESS BEING SEARCHED

//       LOCATION: SAN ANTONIO ZIPCODE

//       LIMIT: # OF RESULTS GENERATED IN ARRAY (MAX OF 50?)

//       OFFSET: IF MORE THAN 50 ADJUST OFFSET ------->


var requestObj= {
    'url': cors_url + '/' + yelp_search_url,
    'data': {term: 'restaurants', location: '78247', limit: 5},
    headers: {
        'Authorization':'Bearer ' + YELP_TOKEN},

    error: function(jqXHR, textStatus, errorThrown) {
        console.log('AJAX error, jqXHR = ', jqXHR, ', textStatus = ', textStatus, ', errorThrown =', errorthrown)
    }
}

$.ajax(requestObj)
    .done(function(data) {
        console.log('typeof data = ' + typeof data)
        console.log('data = ', data)
        console.log(data.businesses[0])   //<---- PULLING FIRST RESULT FROM ARRAY. USE # RANDOM # GENERATOR TO POPULATE RANDOM RESTAURANT
    })