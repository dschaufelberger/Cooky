/**
 * Created by Jasper on 29.11.2015.
 */
var countForAddIngredient = 1;
var countForSuggestions = 1;

function addRow (){
    $(".ingredients tbody").append("<tr>" +
        "<td><form:input path='ingredients["+ countForAddIngredient +"].name' /></td> " +
        "<td><form:input path='ingredients["+countForAddIngredient+"].amount' /></td> " +
        "<td><form:input path='ingredients["+countForAddIngredient+"].unit'/></td>" +
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
        $("</br><input id='ingredients"+ countForSuggestions +"' name='ingredients["+ countForSuggestions +"]' " +
            "onchange='addIngredientRow()' class='form-control ingredients' type='text' value=''>")
            .appendTo(".ingredientSuggestions");
        countForSuggestions = countForSuggestions + 1;
    }
};