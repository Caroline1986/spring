const settings = {
    "async": true,
    "crossDomain": true,
    "url": "https://shazam.p.rapidapi.com/auto-complete?term=kiss%20the&locale=en-US",
    "method": "GET",
    "headers": {
        "x-rapidapi-key": "rapid_api_key",
        "x-rapidapi-host": "shazam.p.rapidapi.com"
    }
};

$.ajax(settings).done(function (response) {
    console.log(response);
});

