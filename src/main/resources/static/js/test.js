$("button.search__submit").click(function () {
    $("li.search-result__item").each(function () {
        var searchRequest = $(this).find("p.search-result__title").text();
        var resultQuantity = $(this).find("span.search-result__number").text();

        var searchItem = this;

        $.ajax({
            type: "GET",
            url: "/search",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            timeout: 300000,
            data: {
                searchRequest: searchRequest,
                resultQuantity: resultQuantity
            },
            success: function (data) {
                $(searchItem).find("p.search-result__title").append("<span class='search-result-done-span'>" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Done</span>");
                addResult(data)
            },
            beforeSend: function () {
                $(searchItem).find("p.search-result__title").append("<span class='processing'>" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;->Processing ...</span>");
            },
            complete: function () {
                $(searchItem).find("span.processing").remove();
            }
        });
    });
});


//TODO remove button isn't assigned
function addResult(result) {
    $.each(result, function (index, site) {
        $("ul.result__list").append(
            "<li class=\'result__item\'>" +
            "<div class=\'result__content\'>" +
            "<span class=\'result__dot\'>&#8226;</span>" +
            "<span class=\'result__position\'>#" + site.position + "</span>" +
            "<p class=\'result__title\'><a href=\'#\' class=\'result__link\'>" + site.domain + "</a>" +
            "<span class=\'result__subtitle\'>" + site.searchRequest + "</span></p>" +
            "<div class=\'result__time\'>" +
            "<p class=\'result__time-date\'><img src=\'img/calendar.svg\' class=\'result-icon\' alt=\'icon\'" +
            "width=\'24\'><span>" + site.date + "</span></p>" +
            "<p class=\'result__time-hours\'><img src=\'img/clock.svg\' class=\'result-icon\' alt=\'icon\'" +
            "width=\'24\'><span>4:33pm</span></p>" +
            "</div>" +
            "<button class=\'search-result__btn-remove-item\'><i class=\'far fa-trash-alt\'></i>&nbsp;&nbsp;&nbsp;Удалить" +
            "</button>" +
            "</div>" +
            "</li>"
        );
    });

}


$("button.search__btn-incr").click(function () {
    var request = $("input.search__input").val();
    var quantity = $("input.search__input--number").val();

    $("ul.search-result__list").append(
        "<li class=\'search-result__item\'>" +
        "<div class=\'search-result__content\'>" +
        "<span class=\'search-result__dot\'>&#8226;</span>" +
        "<p class=\'search-result__title\'>" + request + "</p>" +
        "<span class=\'search-result__number\'>" + quantity + "</span>" +
        "<button class=\'search-result__btn-remove-item\'><i class=\'far fa-trash-alt\'></i>&nbsp;&nbsp;&nbsp;Удалить" +
        "</button>" +
        "</div>" +
        "</li>"
    );

    $("button.search-result__btn-remove-item").click(function () {
        $(this).closest("li").remove();
    });

});

