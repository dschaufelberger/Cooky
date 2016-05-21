/**
 * Created by Jasper on 29.11.2015.
 */
var count = 1;
var counter = 1;

function addRow (){
    $(".ingredients tbody").append("<tr>" +
        "<td><form:input path='ingredients["+ count +"].name' /></td> " +
        "<td><form:input path='ingredients["+count+"].amount' /></td> " +
        "<td><form:input path='ingredients["+count+"].unit'/></td>" +
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

function addIngredientRow() {
    var fieldsFilled = true;
    $(".ingredients").each(function () {
        if ($(this).val() == "") {
            fieldsFilled = false;
            return false;
        }
    });

    if (fieldsFilled) {
        $("</br><input id='ingredients"+ counter +".name' name='ingredients["+ counter +"].name' " +
            "onchange='addIngredientRow()' class='form-control ingredients' type='text' value=''>")
            .appendTo(".ingredientSuggestions");
        counter = counter + 1;
    }
};