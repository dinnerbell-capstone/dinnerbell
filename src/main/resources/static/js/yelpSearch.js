"use strict"


var searchterms = '';


// function getTerm(term) {
//     console.log(term);
//     $('.term').text(term);
// }

$("#clearButton").on("click", function() {
    location.reload()
})

$("#submit").on("click", function() {
    $('#results').empty()
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
                'Authorization': 'Bearer ' + yelpKey
            },
            method: 'GET',
            dataType: 'json',
            success: function (data) {

                var totalresults = data.total;
                if (totalresults > 0) {
                    // DISPLAYS # OF RESULTS

                    let itemHTML = '';
                    $('#totalNumber').html('<h5>YOU HAVE ' + totalresults + ' POTENTIAL MATCHES!</h5>');

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
console.log(data)

                        // if (itemHTML === numberOfResults) {
                        //     return false
                        // }
                        // APPENDS RESULTS


                        // itemHTML += "<div class='card__content'>"
                        // itemHTML += "<div class='card__title'>" + name + "</div>"
                        // itemHTML += "<div class='card__snippet'>"
                        // itemHTML += "<p>" + "Rating:" + rating + "</p>"
                        // itemHTML += "<p>" + "Address:" + address + "</p>"
                        // itemHTML += "<p>" + city + "," + state + " " + zipcode + "</p>"

                        //
                        // $(".image").html(image);
                        // $(".card__title").html(name);
                        // $(".content1").html(rating);
                        // $(".content2").html(phone);
                        // $(".content3").html(address);
                        // $(".content4").html(city + state + zipcode);


                        // itemHTML += "<p>" + state + "</p>"
                        // itemHTML += "<p>" + zipcode + "</p>"
                        // itemHTML += "<article class ='details'>"
                        // itemHTML += "<h6>" + "Phone Number:" + "</h6>"
                        // itemHTML += "<p>" + restaurantPhone + "</p>"
                        // itemHTML += "</article>"

                        // < div className = "card__image" style = "background-image: url('../../static/img/pexels-photo-670705.jpeg')" > < /div>
                        // <div className="card__content">
                        // <div className="card__title">Sake Cafe</div>
                        // <div className="card__snippet">
                        //     <script src="https://apps.elfsight.com/p/platform.js" defer></script>
                        //     <div className="elfsight-app-af12e0df-1c91-49bf-a211-1854f06190dc"></div>
                        // </div>
                        // <div className="card__readmore">Like</div>
                        // </div>
                        // itemHTML += "<a href='" + eventUrl2 + "'>" + eventUrl2 + "</a>"

                        $('#results').append(
                            '<div class="card text-white" style="background-color: darkorange; width:50%">' +
                            '<div class="card__title cards text-white" style="background-color: #101010">' + name + '' +  '</div>' +
                            '<div id="' + id + '" style="margin-top:20px;margin-bottom:25px;">' +
                            '<img src="' + image + '" style="width:200px;height:150px;"><br>' +
                            '<div class="card-content">' +
              
                            '<br> Located at: ' + address + ' ' + city + ', ' + state + ' ' + zipcode + '' +
                            '<br>The phone number for this business is: ' + phone + '' +
                            '<br>This business has a rating of ' + rating + ' with ' + reviewcount + ' reviews.</div>' +
                            '</div>' +
                            '</div>'




                        );


                        // REDIRECT TO VIEW MORE DETAILS OF RESTAURANT PAGE

                        // '<a href="http://www.google.com/"><input type="button" value="Visit More Details" /></a>'


                    });

                } else {
                    // IF NO RESULTS
                    $('#totalNumber').html('<h5>No matching results!</h5>');

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