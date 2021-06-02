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
                console.log(restaurantImage)
                console.log(restaurantName)
                console.log(restaurantId)
                itemHTML += "<h1 class='h1 ' >"+ restaurantName + "</h1>"


                itemHTML += "<button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#staticBackdrop'>" +
                'View More Details' +
                "</button>"




            })

            $(".results").append(itemHTML);
           

        });

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
                var restaurantLocation= item.display_address
                var restaurantDescription = item.categories[0].title
                var restaurantDescription2 = item.categories[1]
                var restaurantPhone = item.display_phone
                var restaurantUrl = item.url
                var address = item.location.address1;
                var city = item.location.city;
                var state = item.location.state;
                var zipcode = item.location.zip_code;


                if (i === 1) {
                    return false
                }
                console.log(restaurantDescription)
                // console.log(restaurantDescription2)
                console.log(restaurantName)
                console.log(restaurantId)
                console.log(city)
                console.log(address)
                console.log(state)
                console.log(zipcode)
                itemHTML += "<form action='' method='post'>"
                itemHTML += "<main>"
                itemHTML += "<h1>" + restaurantName + "</h1>"
                itemHTML += "<img src='" + restaurantImage + "' style='width:500px height:500px'>"
                itemHTML += "<br>"
                itemHTML += " <div>"
                itemHTML += "<button type='submit' class='btn btn-danger'>" + "Add to Favorites" + "</button>"
                itemHTML += "</div>"
                itemHTML += "<div>"
                itemHTML += "<button id='favoriteBtn' type='submit' class='btn btn-secondary'>" + "Saved to Favorites" + "</button>"
                itemHTML += "</div>"
                itemHTML += "<h5>Restaurant Description</h5>"
                itemHTML += "<p>" + restaurantDescription + "</p>"
                itemHTML += "<article class ='details'>"
                itemHTML += "<h6>" + "Restaurant Rating:" + "</h6>"
                itemHTML += "<p>" + restaurantRating + "</p>"
                itemHTML += "</article>"
                itemHTML += "<article class ='details'>"
                itemHTML += "<p>" + restaurantPrice + "</p>"
                itemHTML += "</article>"
                itemHTML += "<article class ='details'>"
                itemHTML += "<h6>" + "Phone Number:" + "</h6>"
                itemHTML += "<p>" + restaurantPhone + "</p>"
                itemHTML += "</article>"
                itemHTML += "<article class ='details'>"
                itemHTML += "<h6>" + "Address:" + "</h6>"
                itemHTML += "<p>" + address + "</p>"
                itemHTML += "<p>" + city + "," + state + "</p>"
                itemHTML += "<p>" + zipcode + "</p>"
                itemHTML += "</article>"
                itemHTML += "<article class ='details'>"
                itemHTML += "<h6>" + "Website:" + "</h6>"
                itemHTML += "<a href='" + restaurantUrl + "'>" + restaurantUrl + "</a>"
                itemHTML += "</article>"
                itemHTML += "</main>"
                itemHTML += "</form>"
                itemHTML += "<div sec:authorize='isAuthenticated()'>"
                itemHTML += "<h3>How was your visit? Let us know!</h3>"
                itemHTML += "<a class='btn btn-warning' th:href='@{/review/{id}(id=${restaurants.id})}' role='button'>" + "Leave a Review" + "</a>"
                itemHTML += "<a class='btn btn-warning' th:href='@{/reviews/byRestaurant/{id}(id=${restaurants.id})}' role='button'>" + "View Reviews" + "</a>"
                itemHTML += " </div>"
                // itemHTML += " </div>"
                // itemHTML += " </div>"
                // itemHTML += " </div>"

            })

            $("#viewMore").append(itemHTML);


        });


    // $.ajax({
    //     url: cors_url + '/' + yelp_search_url,
    //     headers: {
    //         'Authorization':'Bearer ' + YELP_TOKEN
    //     },
    //     method: 'GET',
    //     dataType: 'json',
    //     success: function (data) {
    //
    //         var totalresults = data.total;
    //         if (totalresults > 0) {
    //             // DISPLAYS # OF RESULTS
    //
    //
    //             $('#results').append('<h5>YOU HAVE ' + totalresults + ' POTENTIAL MATCHES!</h5>');
    //
    //             // JSON ARRAY OF 'businesses' FROM API
    //             $.each(data.businesses, function (i, item) {
    //                 var image = item.image_url;
    //
    //                 // APPENDS RESULTS
    //
    //                 $('#imgHere').append('<img src="' + image + '" style="width:200px;height:150px;"><br>'
    //
    //
    //
    //             );
    //
    //
    //             });
    //
    //         } else {
    //             // IF NO RESULTS
    //             $('#results').append('<h5>No matching results!</h5>');
    //
    //         }
    //     }
    // })
    //


            });

