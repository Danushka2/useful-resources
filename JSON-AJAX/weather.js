//declaring variable to store API Token
var APIID = "";
//declaring array to store city ids from the JSON file
var ID = new Array();
//declaring variable to store the unit type
var UNITS = "metric";

$.ajax({
    //calling RESTful APIs to get CityCodes from the JSON file
    url: "./JSON/Step1.json",
    type: "GET",
    dataType: "json",
    cache: false,
    success: function (data) {
        
        //loop throught the data and store the CityCodes in the array 
        $(data.List).each(function (index) {
            var value = data.List[index];
            ID.push(value.CityCode);
        });

        //calling myFunction
        myFunction(ID);
    }

});

//creating myFunction function to get the latest weather information and display it in the browser
function myFunction(ID) {

    $.ajax({
        //calling RESTfull APIs to get information from the server
        url: "https://api.openweathermap.org/data/2.5/group?id="+ ID +"&units="+ UNITS +"&appid=" + APIID,
        type: "GET",
        dataType: "json",
        success: function (data) {
            //display all the data in the console
            console.log(data);
            //loop throught the data and display it in the browser
            $(data.list).each(function (index) {
                var arr = data.list[index];
                $('#container').append('<div class="testing">ID: ' + arr.id + '<br>Name: ' + arr.name + '<br>Temp: ' + arr.main.temp + '<br>Weather: ' + arr.weather[0].description + '<br><br></div>')
            });
        }
    });
}
