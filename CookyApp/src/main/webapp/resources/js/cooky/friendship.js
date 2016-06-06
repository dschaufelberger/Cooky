/**
 * Created by Dodo on 05.06.2016.
 */

$(function () {
    var friendRequest = $('.cooky-friend-request');
    $(friendRequest).on('click', '.friend-request-accept', {requestUrl: '/cookys/accept'}, requestSendHandler);
    $(friendRequest).on('click', '.friend-request-reject', {requestUrl: '/cookys/reject'}, requestSendHandler);
});

var requestSendHandler = function (event) {
    var requestContainer = event.delegateTarget;
    var inquirer = $('.request-inquirer', requestContainer).val();
    var requested = $('.request-requested', requestContainer).val();
    var token = $('meta[name="_csrf_token"]').attr("content");

    var url = event.data.requestUrl;
    var data = {
        inquirer: inquirer,
        requested: requested,
        _csrf: token
    };
    $.post(url, data, function () {
        $(requestContainer).closest('li').remove();
        var requestCount = $('.friend-request-dropdown > li').length - 1;
        var requestCountBadge = $('#friendRequests').find('.badge');
        if (requestCount !== 0) {
            $(requestCountBadge).text(requestCount);
        } else {
            $('.friend-request-label').text('No pending friend requests.');
            $(requestCountBadge).text('');
        }
    });
    event.stopPropagation();
};