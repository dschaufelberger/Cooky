/**
 * Created by Jasper on 29.11.2015.
 */
$(document).ready(function () {
    $('.ratings_stars').hover(
        function () {
            $(this).prevAll().andSelf().each(function (index, item) {
                var element = $(item);
                element.data('beforeHoverClasses', element.attr('class'));
                element.addClass('glyphicon-star');
                element.removeClass("glyphicon-star-empty");
            });
            $(this).nextAll().each(function (index, item) {
                var element = $(item);
                element.data('beforeHoverClasses', element.attr('class'));
                element.removeClass('glyphicon-star');
                element.addClass('glyphicon-star-empty');
            });
        },
        function () {
            $('.ratings_stars').each(function (index, item) {
                var element = $(item);
                element.attr('class', element.data('beforeHoverClasses'));
            });
        }
    );
});

function rate(id) {
    var currentRating = id;
    var recipeId = $(".recipeId").val();

    var csrf_token = $('#csrf_token').val();
    var settings = {
        method: 'POST',
        url: '/recipes/rate/',
        data: {
            id: recipeId,
            rating: currentRating
        },
        headers: {
            'X-CSRF-TOKEN': csrf_token
        }
    };

    $.ajax(settings);
}







