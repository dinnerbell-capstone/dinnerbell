"use strict"

// const client = filestack.init(fileStackApiKey);
const cors_url = 'https://cors-anywhere.herokuapp.com'
const yelp_search_url = 'https://api.yelp.com/v3/businesses/search'

function clientCallback() {
    console.log('IT WORKS')
}

//<----- RANDOM # GENERATOR ----->

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

//<----- Offset used to generate results > 50
//       Array # used to generate a random restaurant from
//       the total # of random results ------>

var offsetRandomNumber = getRandomInt(0, 20)
var arrayRandomNumber = getRandomInt(0,50)

//<!---- TERM: Type of business being searched. Defaults to restaurants
//       LOCATION: San Antonio Zip Code
//       LIMIT: # of results generated in array (MAX OF 50?)
//       OFFSET: If more than 50 results offset is needed  ------->

var requestObj= {
    'url': cors_url + '/' + yelp_search_url,
    'data': {term: 'restaurants', location: '78247', limit: 50, offset: offsetRandomNumber},
    headers: {
        'Authorization':'Bearer ' + YELP_TOKEN,
    },
    error: function(jqXHR, textStatus, errorThrown) {
        console.log('AJAX error, jqXHR = ', jqXHR, ', textStatus = ', textStatus, ', errorThrown =', errorThrown)
    }
}


$("#submit").click(function() {
    location.reload();
    $('#results').empty()
});

var arr =[]
let result = ""

    $.ajax(requestObj)
        .done(function (data) {
            console.log('typeof data = ' + typeof data)
            console.log('data = ', data)
            let itemHTML = '';
            $.each(data.businesses, function (index, value) {
            var businessName = data.businesses[arrayRandomNumber].name
            // var businessRating = data.businesses[arrayRandomNumber].rating
            // var businessPrice = data.businesses[arrayRandomNumber].price
            // var businessImage = data.businesses[arrayRandomNumber].image_url
                if (index === 4) {
                    return false
                }
            itemHTML += "<span class='card text-white bg-dark ' >"
            itemHTML += "<div class='card-body'>"
            itemHTML += "<b class='text-white'>" + businessName + "</b>"
            // itemHTML += "<h2 class='text-white'>" + businessPrice + "</h2>"
            // itemHTML += "<h2 class='text-white'>" + 'Rating: '  + businessRating + "</h2>"
            itemHTML += "</span>"
            itemHTML += "</div>"




                })

$(".box1").append(itemHTML);


        });

$.ajax(requestObj)
    .done(function (data) {
        console.log('typeof data = ' + typeof data)
        console.log('data = ', data)
        let itemHTML = '';
        $.each(data.businesses, function (index, value) {
            var businessName = data.businesses[arrayRandomNumber].name
            // var businessRating = data.businesses[arrayRandomNumber].rating
            // var businessPrice = data.businesses[arrayRandomNumber].price
            // var businessImage = data.businesses[arrayRandomNumber].image_url
            if (index === 4) {
                return false
            }
            itemHTML += "<span class='card text-white bg-dark ' >"
            itemHTML += "<div class='card-body'>"
            itemHTML += "<b class='text-white'>" + businessName + "</b>"
            // itemHTML += "<h2 class='text-white'>" + businessPrice + "</h2>"
            // itemHTML += "<h2 class='text-white'>" + 'Rating: '  + businessRating + "</h2>"
            itemHTML += "</span>"
            itemHTML += "</div>"




        })

        $(".box2").append(itemHTML);


    });




