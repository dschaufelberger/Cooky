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
    $('.ratings_stars').hover(
        function () {
            $(this).prevAll().andSelf().addClass("glyphicon-star");
            $(this).prevAll().andSelf().removeClass("glyphicon-star-empty");
        },
        function () {
            $(this).nextAll().removeClass("glyphicon-star");
            $(this).nextAll().addClass("glyphicon-star-empty");
        }
    );
});

function rate (id) {
    var currentRating = id;
    var recipeId = $(".recipeId").val();
    $.ajax({
        url: "../rateRecipe",
        data: {
            id : recipeId,
            rating : currentRating
        },
    });
}







