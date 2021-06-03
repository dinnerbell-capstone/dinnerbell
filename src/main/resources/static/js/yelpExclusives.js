"use strict"


var searchterms = '';


function getTerm(term) {
    console.log(term);
    $('.term').text(term);
}



$("#submit").on("click", function() {
    $('#results').empty()
    
    
    // searchterms = $("#searchbox").val();
    // console.log(searchterms);
    // $(submit).val("");

    var numberOfResults = parseInt($("#numberOfResults").val());
    console.log(numberOfResults);

    var myurl = "https://cors-anywhere.herokuapp.com/https://api.yelp.com/v3/events?categories=food-and-drink&location=sanantonio"

        // +
        // "search?categories=by-food-and-drink&location=sanantonio&limit=4"

        // + numberOfResults;
    console.log(myurl);

    // <---------------- IF WEBPAGE DOES NOT POP UP GO TO THIS LINK AND ENABLE DEMO SERVER

    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        // https://cors-anywhere.herokuapp.com/corsdemo


        $.ajax({
            url: myurl,
            headers: {
                'Authorization':'Bearer ' + YELP_EXCLUSIVES
            },
            method: 'GET',
            dataType: 'json',
            success: function (data) {

                var totalresults = data.total;
                if (totalresults > 0) {
                    // DISPLAYS # OF RESULTS

                    console.log(data)
                    let itemHTML = '';
                    let imageAppend = '';
                    let itemHTMLL = '';
                    let itemHTMLLL = '';
                    $('#results').append('<h5 class="text-white">YOU HAVE ' + totalresults + ' POTENTIAL MATCHES!</h5>');

                    // JSON ARRAY OF 'businesses' FROM API
                    $.each(data.events, function (i, item) {
                        var businessId = data.events[0].business_id
                        var category = data.events[0].categories
                        var description = data.events[0].description
                        var eventName = data.events[0].name
                        var image = data.events[0].image_url
                        var eventUrl = data.events[0].event_site_url
                        var address= data.events[0].location.display_address

                        var businessId1 = data.events[1].business_id
                        var category1 = data.events[1].categories
                        var description1 = data.events[1].description
                        var eventName1 = data.events[1].name
                        var image1 = data.events[1].image_url
                        var eventUrl1 = data.events[1].event_site_url
                        var address1= data.events[1].location.display_address

                        var businessId2 = data.events[2].business_id
                        var category2 = data.events[2].categories
                        var description2 = data.events[2].description
                        var eventName2 = data.events[2].name
                        var image2 = data.events[2].image_url
                        var eventUrl2 = data.events[2].event_site_url
                        var address2= data.events[2].location.display_address
                        if (i === 1) {
                            return false
                        }
                        console.log(businessId)
                        console.log(eventName)
                        console.log(description)
                        console.log(category)
                        console.log(image)
                        console.log(eventUrl)
                        console.log(address)

                        console.log(businessId1)
                        console.log(category1)
                        console.log(description1)
                        console.log(eventName1)
                        console.log(image1)
                        console.log(eventUrl1)
                        console.log(address1)

                        console.log(businessId2)
                        console.log(category2)
                        console.log(description2)
                        console.log(eventName2)
                        console.log(image2)
                        console.log(eventUrl2)
                        console.log(address2)


                        // APPENDS RESULTS
                        // imageAppend += "<img src='" + image + "' style='width:25px height:25px'>"
                        itemHTML += "<a href='" + eventUrl + "'>" + eventUrl + "</a>"

                        // itemHTMLL += "<img src='" + image1 + "' style='width:25px height:25px'>"
                        itemHTMLL += "<a href='" + eventUrl1 + "'>" + eventUrl1 + "</a>"
                        //
                        // itemHTMLLL += "<img src='" + image2 + "' style='width:25px height:25px'>"
                        itemHTMLLL += "<a href='" + eventUrl2 + "'>" + eventUrl2 + "</a>"



                        $("#businessId").html(businessId);
                        $("#eventName").html(eventName);;
                        $("#description").html(description);;
                        $("#address").html("Address:" + address);
                        $("#insertImage").append(imageAppend);
                        $("#links").append(itemHTML);

                        $("#businessId1").html(businessId1);
                        $("#eventName1").html(eventName1);;
                        $("#description1").html(description1);;
                        $("#address1").html("Address:" + address1);
                        // $("#insertImage1").append(itemHTMLL);
                        $("#links1").append(itemHTMLL);

                        $("#businessId2").html(businessId2);
                        $("#eventName2").html(eventName2);;
                        $("#description2").html(description2);;
                        $("#address2").html("Address:" + address2);
                        // $("#insertImage2").append(itemHTMLLL);
                        $("#links2").append(itemHTMLLL);
                        
                        
                    });



                } else {
                    // IF NO RESULTS
                    // $('#results').append('<h5>No matching results!</h5>');

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