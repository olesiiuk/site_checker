$(function(){

	$(".owl-carousel").owlCarousel();

	$(".fancybox").fancybox();

	new WOW().init();

	// Select Styling

	$(document).ready(function(){
    	$('#prestashop_version, #seo, #cpu, #structure, #headings, #links, #texts').awselect({
	    background: "#ffffff",
		active_background:"#19289c",
		placeholder_color: "#3e3d3d",
		placeholder_active_color: "#ffffff",
		option_color:"#ffffff",
		vertical_padding: "15px",
		horizontal_padding: "20px",
        immersive: false
    	});
	});

	// Tabs for nav

	$(".tab_item").not(":first").hide();

	$(".nav__list .tab").click(function() {
		$(".nav__list .tab").removeClass("nav__item--active").eq($(this).index()).addClass("nav__item--active");
		$(".tab_item").hide().eq($(this).index()).fadeIn()
	}).eq(0).addClass("nav__item--active");

	// Accordion

	$('.result__btn-more-item').click( function() {
		$(this).parent().next('.catalog__sort').toggle(400);
	});

	$('.catalog__sort-form input, .catalog__sort-form select').change( function() {

		// НАДО ПРАВИЛЬНО ОПРЕДЕЛИТЬ СЕЛЕКТОР

		// $(this).closest('.result__content').children('.result__btn-save-item').show();
		// $(this).closest('.result__content').children('.result__btn-more-item').hide();

		$('.result__btn-save-item').show();
		$('.result__btn-more-item').hide();
	});

	$('.result__btn-save-item').click( function() {
		$(this).hide().siblings(".result__btn-more-item").show();
	});

});