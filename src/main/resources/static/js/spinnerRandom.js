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

$.ajax(requestObj)
    .done(function(data) {

        console.log('typeof data = ' + typeof data)
        console.log('data = ', data)
        var businessName = data.businesses[arrayRandomNumber].name
        var businessRating = data.businesses[arrayRandomNumber].rating
        var businessLocation=  data.businesses[arrayRandomNumber].location
        var businessImage=  data.businesses[arrayRandomNumber].image_url
        var image = document.getElementById("image");

        // image.src = data.businesses[arrayRandomNumber].image_url;


        console.log(businessName)
        console.log(businessRating)
        console.log(businessLocation)



        $('#results').append(businessName + ""
            + businessLocation + ""
            + businessRating   + ""
            + businessImage   + ""




        );


        // console.log(offsetRandomNumber)
        // console.log(arrayRandomNumber)

        //<---- PULLING FIRST RESULT FROM ARRAY. USE # RANDOM # GENERATOR TO POPULATE RANDOM RESTAURANT
    })









var foodWheel = 0;

function setup()  {
    createCanvas(600, 600);

    foodWheel = new wheel(400);

    foodWheel.addSegment("Pizza Place Place");
    foodWheel.addSegment("Taco Bell");
    foodWheel.addSegment("Waffle House");
    foodWheel.addSegment("Yum yum");
    foodWheel.addSegment("Lui's");
    foodWheel.addSegment("Acai Express");
    foodWheel.addSegment("Tropical Smoothie");
    foodWheel.addSegment("Hangry Hanks");

    textFont('Helvetica');
}

function draw() {
    background("#f5eee6");

    fill('orange');
    noStroke();
    ellipse(width / 2, height / 2, 420, 420);

    //Render and update the wheel
    textAlign(LEFT, CENTER);
    textSize(18);
    foodWheel.update();
    foodWheel.show();

    //Draw FPS
    fill(0);
    textAlign(LEFT);
    text(floor(frameRate()), 50, 50);
}

function spinWheel()  {
    if(typeof foodWheel != "undefined") {
        foodWheel.spin();
    }
}

wheel = function(radius)  {
    this.pos = new p5.Vector(width / 2, height / 2);
    this.circleRadius = radius;
    this.startingRadius = 0;
    this.radiusAddition = 0;
    this.spinSpeed = 0;
    this.currentlyPointed = 0;  //The index of the currently selected
    this.segments = [];
    this.triangleRotation = 0;

    this.addSegment = function(name)  {
        var lengthOverage = name.length - 12;
        if(lengthOverage > 0)  {
            name = name.slice(0, -lengthOverage);
            name += "..."
        }

        this.segments.push({
            name:name
        });

        this.radiusAddition = TWO_PI / this.segments.length;
    }

    this.update = function()  {
        if(this.spinSpeed > 0)  {
            this.startingRadius += abs(sin(this.spinSpeed));
            if(this.startingRadius > TWO_PI)  {
                this.startingRadius = 0;
            }
            this.spinSpeed *= 0.97;
            if(this.spinSpeed < 0.001)  {
                this.spinSpeed = 0;
            }
        }
    }

    this.show = function()  {
        var xx = width / 2, yy = height / 2;

        noStroke();

        var len = this.segments.length;
        var currentRad = this.startingRadius;
        var colorIndex = 0;

        if(len > 1)  {
            for(var i = 0; i < len; i++)  {
                var colors = ["#ffeaa5","#fe5f55", "#c7efcf", "#eef5db"];
                fill(colors[colorIndex]);
                colorIndex++;
                if(colorIndex >=colors.length - 1)  {
                    colorIndex = 0;
                }
                var nextRad = currentRad + this.radiusAddition;
                if(nextRad > TWO_PI)  {
                    nextRad -= TWO_PI;
                }
                if(nextRad <= HALF_PI + this.radiusAddition && nextRad > HALF_PI) {
                    //Currently pointing to
                    if(this.currentlyPointed != i)  {
                        this.triangleRotation = -0.3;
                        this.currentlyPointed = i;
                    }
                    fill('#32cd32');
                }

                arc(xx, yy, this.circleRadius, this.circleRadius, currentRad, nextRad);

                fill(0);
                push();
                translate(xx, yy);
                rotate(currentRad + this.radiusAddition / 2);
                text(this.segments[i].name, 60, 0);
                pop();

                currentRad += this.radiusAddition;
                if(currentRad > TWO_PI) {
                    currentRad -= TWO_PI;
                }
            }
        } else if(len === 1) {
            //If there's only one item, it just draws a circle
            fill(255);
            ellipse(xx, yy, this.circleRadius, this.circleRadius);
            fill(0);
            text(this.segments[0].name, xx, yy);
        }
        //Draw the triangle
        fill("orange");
        stroke(0);
        strokeWeight(2);
        var xx = width / 2, yy = height / 2 + (this.circleRadius / 2);
        push();
        translate(xx, yy);
        if(this.triangleRotation < 0) {
            rotate(this.triangleRotation);
            this.triangleRotation += 0.025;
        }
        triangle(-5, 0, 5, 0, 0, -40);
        pop();
    }

    this.spin = function()  {
        //Randomizes the wheel before spinning
        this.startingRadius = random(TWO_PI);
        this.spinSpeed = 0.5;
    }
}