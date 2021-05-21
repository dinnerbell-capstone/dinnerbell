"use strict"

var myurl = "https://cors-anywhere.herokuapp.com/https://api.yelp.com/v3/businesses/search?term=by-restaurant&mcdonalds&location=sanantonio&limit5";
//https://cors-anywhere.herokuapp.com/corsdemo          <---------------- IF WEBPAGE DOES NOT POP UP GO TO THIS LINK AND ENABLE DEMO SERVER
$.ajax({
    url: myurl,
    headers: {
        'Authorization':'Bearer TA-VoasuDX6VFC343Qx46wtx-KCwoUlhH3aOcZevcHB4ZwML7zw_wjpogFPwUy_-_Jpnq8O0_3MA7e0CWEiR3NKL-v_uUqsckvpXMwGzK68Q3u-Y8cJF3mfzyz-dYHYx',
    },
    method: 'GET',
    dataType: 'json',
    success: function(data){
        var totalresults = data.total;
        if (totalresults > 0){
            // DISPLAYS # OF RESULTS

            $('#results').append('<h5>YOU HAVE ' + totalresults + ' POTENTIAL MATCHES!</h5>');

            // JSON ARRAY OF 'businesses' FROM API
            $.each(data.businesses, function(i, item) {
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
                    'We found <b>' + name + '' +
                    '</b> (' + alias + ')' +
                    '<br>Business ID: ' + id + '' +
                    '<br> Located at: ' + address + ' ' + city + ', ' + state + ' ' + zipcode + '' +
                    '<br>The phone number for this business is: ' + phone + '' +
                    '<br>This business has a rating of ' + rating + ' with ' + reviewcount + ' reviews.</div>');
            });
        } else {
            // IF NO RESULTS
            $('#results').append('<h5>No matching results!</h5>');
        }
    }
});
