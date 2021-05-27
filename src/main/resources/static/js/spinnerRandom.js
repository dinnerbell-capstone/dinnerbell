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
        // 'Cache-Control': 'no-cache, no-store, must-revalidate',
        // 'Pragma': 'no-cache',
        // 'Expires': '0'

    },


    error: function(jqXHR, textStatus, errorThrown) {
        console.log('AJAX error, jqXHR = ', jqXHR, ', textStatus = ', textStatus, ', errorThrown =', errorthrown)
    }
}


$("button").click(function() {
    location.reload();
    $('#results').empty()
});

var arr =[]
let result = ""

//
// for (var i = 0 ; i < 6; i++) {
//     arr[i] =
    $.ajax(requestObj)
        .done(function (data) {
            console.log('typeof data = ' + typeof data)
            console.log('data = ', data)
            let itemHTML = '';
            $.each(data.businesses, function (index, value) {
            var businessName = data.businesses[arrayRandomNumber].name
            var businessRating = data.businesses[arrayRandomNumber].rating
            var businessLocation = data.businesses[arrayRandomNumber].location
            var businessImage = data.businesses[arrayRandomNumber].image_url
                if (index === 6) {
                    return false
                }
            itemHTML += "<div class='card text-white bg-dark'>"
            itemHTML += "<div class='card-body'>"
            itemHTML += "<h2 class='text-white'>" + businessName + "</h2>"
            itemHTML += "<h2 class='text-white'>" + businessName + "</h2>"
            itemHTML += "<h2 class='text-white'>" + businessName + "</h2>"
                    </div>
                    </div>






                // var image = document.getElementById("image");
            // image.src = data.businesses[arrayRandomNumber].image_url;
            // console.log(businessName)
            // console.log(businessRating)
            // console.log(businessLocation)

            // for( var i = 0; i < 6;++i) {
            //     result = `
            //
            //     <div class="card text-white bg-dark">
            //     <div class="card-body">
            //     <img style="float:left" class="img-thumbnail" width="200" height="200"
            //     src="${data.businesses[arrayRandomNumber].image_url}"/>
            //     <h2 class="text-white">${data.businesses[arrayRandomNumber].name}</h2>
            //     <h3 class="text-white">${data.businesses[arrayRandomNumber].location}</h3>
            //     <h3 class="text-white">${data.businesses[arrayRandomNumber].rating}</h3>
            //     </div>
            //     </div>
            //     `;

            });
            $("#results").append(itemHTML);


            // $('#results').append(businessName + "" + ('<div class="card"> hello  </div>')
            //     // + businessLocation + ""
            //     // + businessRating + ""
            //     // + businessImage + ""
            // );

            // console.log(offsetRandomNumber)
            // console.log(arrayRandomNumber)


            //<---- PULLING FIRST RESULT FROM ARRAY. USE # RANDOM # GENERATOR TO POPULATE RANDOM RESTAURANT
        })








