$("button.result__btn-save-item").bind("click", function (event) {
    var li = $(event.target).closest("li");

    var domain = li.find("a.result__link").text();
    var email = li.find("input.email").val();
    var label = li.find("input.label-input").val();
    var PSVersion = li.find("#prestashop_version").val();
    var template = li.find(".template-input").val();
    var adaptive = li.find(".adaptive-input").val();
    var googleSpeedDesktop = li.find(".googleSpeedDesktop-input").val();
    var googleSpeedMobile = li.find(".googleSpeedMobile-input").val();
    var downloadTime = li.find(".downloadTime-input").val();
    var errors = li.find(".errors-input").val();
    var seo = li.find("#seo").val();
    var cpu = li.find("#cpu").val();
    var structure = li.find("#structure").val();
    var headings = li.find("#headings").val();
    var links = li.find("#links").val();
    var texts = li.find("#texts").val();

    $.ajax({
        type: "PUT",
        url: "/sites/" + domain + "/description",
        contentType: "application/json; charset=utf-8",
        // dataType: "json",
        data: JSON .stringify({
            domain: domain,
            email: email,
            label: label,
            template: template,
            adaptive: adaptive,
            googleSpeedDesktop: googleSpeedDesktop,
            downloadTime: downloadTime,
            errors: errors,
            seo: seo,
            friendlyLinks: cpu,
            structure: structure,
            extraHeadings: headings,
            links: links,
            texts: texts,
            gstmobile: googleSpeedMobile,
            psversion: PSVersion
        })
    });

});