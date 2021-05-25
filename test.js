let yelpAPI = require('yelp-api');

// Create a new yelpAPI object with your API key
let apiKey = 's-cm1_5WJP-dLQgCFrMEJHEAYSP3iS7f5BC9TGn6nYMMZbLLVyQgk3IxrdKPx3dcgMK5emrpqG5aRhHxIMSi3zF2VMjjPPt7J0ATXvpIhqsyIgKbo5BwExsDVLqrYHYx';
let yelp = new yelpAPI(apiKey);

// Set any parameters, if applicable (see API documentation for allowed params)
let params = [
    { businesses: 'restaurants' },
    { country: 'US' },
    { location: 'San Antonio'},
    { limit: '5'},

];

// Call the endpoint
yelp.query('businesses/search?', params, )
    .then(data => {
        // Success
        console.log(data);
    })
    .catch(err => {
        // Failure
        console.log(err);
    });