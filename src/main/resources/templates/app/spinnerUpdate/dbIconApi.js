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
    'data': {term: 'restaurants', location: '78247', limit: arrayRandomNumber, offset: offsetRandomNumber},
    headers: {
        'Authorization':'Bearer ' + YELP_TOKEN,

    },
    error: function(jqXHR, textStatus, errorThrown) {
        console.log('AJAX error, jqXHR = ', jqXHR, ', textStatus = ', textStatus, ', errorThrown =', errorThrown)
    }
}
$("#searchAgain").click(function() {
    location.reload();
});

$(".icon").click(function() {
    // location.reload();
    $(".results").empty()

    var arr =[]
    let result = ""

    $.ajax(requestObj)
        .done(function (data) {
            console.log('typeof data = ' + typeof data)
            console.log('data = ', data)
            let itemHTML = '';
            $.each(data.businesses, function (i, item) {
                var restaurantName = item.name
                var restaurantId = item.id
                var restaurantRating = item.rating
                var restaurantPrice = item.price
                var restaurantImage = item.image_url
                if (i === 1) {
                    return false
                }

                console.log(restaurantName)
                console.log(restaurantId)
                itemHTML += "<h1 class='h1 ' >"+ restaurantName + "</h1>"


                itemHTML += "<button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#staticBackdrop'>" +
                    'View More Details' +
                    "</button>"



            })

            $(".results").append(itemHTML);










        });




});
