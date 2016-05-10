/**
 * Created by Jasper on 29.11.2015.
 */
var count = 1;
function addRow (){
    $("#ingredientsBody").append("<tr>" +
        "<td><input id='ingredientsname' name='ingredients["+ count +"].name' type='text' value=''></td> " +
        "<td><input id='ingredients"+ count +".amount' name='ingredients["+count+"].amount' type='text' value=''></td> " +
        "<td><input id='ingredients"+count+".unit' name='ingredients["+count+"].unit' type='text' value=''></td>" +
        "</tr>")
    count++;
};

$(document).ready(function () {
    var rating = $(".currentRating").val();
    for (var i = 1; i <= rating; i++) {
        $(".star" + i).addClass("cooky-recipeRating");
    }

    $('.ratings_stars').hover(
        function () {
            $(this).prevAll().andSelf().addClass("cooky-recipeRating");
        },
        function () {
            $(this).nextAll().andSelf().removeClass("cooky-recipeRating");
        }
    );
});







