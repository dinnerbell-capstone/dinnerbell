"use strict"


var searchterms = '';


function getTerm(term) {
    console.log(term);
    $('.term').text(term);
}



$("#submit").on("click", function() {
    // event.preventDefault();
    searchterms = $("#searchbox").val();
    console.log(searchterms);
    // $(submit).val("");

    var numberOfResults = parseInt($("#numberOfResults").val());
    console.log(numberOfResults);

    var myurl = "https://cors-anywhere.herokuapp.com/https://api.yelp.com/v3/businesses/search?term=by-" + searchterms + "&location=sanantonio&limit=" + numberOfResults;
    console.log(myurl);

    // <---------------- IF WEBPAGE DOES NOT POP UP GO TO THIS LINK AND ENABLE DEMO SERVER

    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    // https://cors-anywhere.herokuapp.com/corsdemo


    $.ajax({
        url: myurl,
        headers: {
            'Authorization':'Bearer ' + YELP_TOKEN
        },
        method: 'GET',
        dataType: 'json',
        success: function (data) {

            var totalresults = data.total;
            if (totalresults > 0) {
                // DISPLAYS # OF RESULTS


                $('#results').append('<h5>YOU HAVE ' + totalresults + ' POTENTIAL MATCHES!</h5>');

                // JSON ARRAY OF 'businesses' FROM API
                $.each(data.businesses, function (i, item) {
                    var id = item.id;
                    var alias = item.alias;
                    var phone = item.display_phone;
                    var image = item.image_url;
                    var name = item.name;
                    var rating = item.rating;
                    var reviewcount = item.review_count;
                    var address = item.location.address1;
                    var city = item.location.city;
                    var state = item.location.state;
                    var zipcode = item.location.zip_code;

                    // APPENDS RESULTS

                    $('#results').append('<div id="' + id + '" style="margin-top:50px;margin-bottom:50px;"><img src="' + image + '" style="width:200px;height:150px;"><br>' +
                         name + '' +
                        '<br>Business ID: ' + id + '' +
                        '<br> Located at: ' + address + ' ' + city + ', ' + state + ' ' + zipcode + '' +
                        '<br>The phone number for this business is: ' + phone + '' +
                        '<br>This business has a rating of ' + rating + ' with ' + reviewcount + ' reviews.</div>' +

                        // LAUNCH MODAL WITH MAP AND ADDITIONAL DATA

                        '<button type="button" class="btn btn-primary" data-bs-toggle="modal"  data-bs-target="#staticBackdrop"> View More Details </button>');


                });

            } else {
                // IF NO RESULTS
                $('#results').append('<h5>No matching results!</h5>');

            }
        }
    })
});




//<------------ BOOSTRAP 5 MODAL CODE ---------->

//var myModal = document.getElementById('myModal')
// var myInput = document.getElementById('myInput')
//
// myModal.addEventListener('shown.bs.modal', function () {
//   myInput.focus()
// })



// let yelpAPI = require('yelp-api');

// // Create a new yelpAPI object with your API key
// let apiKey = YELP_TOKEN;
// let yelp = new yelpAPI(apiKey);
//
// // Set any parameters, if applicable (see API documentation for allowed params)
// let params = [
//     { businesses: 'restaurants' },
//     { country: 'US' },
//     { location: 'San Antonio'},
//     { limit: '5'},
//
// ];
//
// // Call the endpoint
// yelp.query('businesses/search?', params, )
//     .then(data => {
//         // Success
//         console.log(data);
//     })
//     .catch(err => {
//         // Failure
//         console.log(err);
//     });