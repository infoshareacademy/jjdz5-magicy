var card = $('#addresses-card');
var expButton = $('#cardExpander');
var extClass = 'expanded';
$(expButton).click(function () {
    if (card.hasClass(extClass)) {
        $(card).removeClass(extClass);
    } else {
        $(card).addClass(extClass);
    }
});