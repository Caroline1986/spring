// import java.net.URLEncoder;
// import com.mashape.unirest.http.HttpResponse;
// import com.mashape.unirest.http.JsonNode;
// import com.mashape.unirest.http.Unirest;

var unirest = require("unirest");

var req = unirest("GET", "https://shazam.p.rapidapi.com/auto-complete");

req.query({
    "term": "kiss the",
    "locale": "en-US"
});

req.headers({
    "x-rapidapi-key": "a72390726amsh9dfa2ca4e208462p1b8129jsnff84f0f14396",
    "x-rapidapi-host": "shazam.p.rapidapi.com",
    "useQueryString": true
});


req.end(function (res) {
    if (res.error) throw new Error(res.error);

    console.log(res.body);
});


