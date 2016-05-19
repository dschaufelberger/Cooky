/**
 * Created by Jasper on 29.11.2015.
 */
var count = 1;
function addRow (){
    $(".ingredients tbody").append("<tr>" +
        "<td><form:input path='ingredients["+ count +"].name' /></td> " +
        "<td><form:input path='ingredients["+count+"].amount' /></td> " +
        "<td><form:input path='ingredients["+count+"].unit'/></td>" +
        "</tr>")
    count++;
};

$(document).ready(function () {
    var rating = $(".currentRating").val();
    for (var i = 1; i <= rating; i++) {
        $(".star" + i).addClass("starImage");
    }

    $('.ratings_stars').hover(
        function () {
            $(this).prevAll().andSelf().addClass("starImage");
        },
        function () {
            $(this).nextAll().andSelf().removeClass("starImage");
        }
    );
});







