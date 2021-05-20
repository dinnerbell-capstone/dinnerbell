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