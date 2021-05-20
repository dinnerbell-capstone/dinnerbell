'use strict';

const yelp = require('yelp-fusion');

// Place holder for Yelp Fusion's API Key. Grab them
// from https://www.yelp.com/developers/v3/manage_app
const apiKey = 'TA-VoasuDX6VFC343Qx46wtx-KCwoUlhH3aOcZevcHB4ZwML7zw_wjpogFPwUy_-_Jpnq8O0_3MA7e0CWEiR3NKL-v_uUqsckvpXMwGzK68Q3u-Y8cJF3mfzyz-dYHYx';

const searchRequest = {
    term:'Four Barrel Coffee',
    location: 'san francisco, ca'
};

const client = yelp.client(apiKey);

client.search(searchRequest).then(response => {
    const firstResult = response.jsonBody.businesses[0];
    const prettyJson = JSON.stringify(firstResult, null, 4);
    console.log(prettyJson);
}).catch(e => {
    console.log(e);
});