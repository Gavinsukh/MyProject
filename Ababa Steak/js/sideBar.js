$('.btn i').click(function () {
	$(this).toggleClass('click');
	$('.sidebar').toggleClass('show');
	var btnState = $('.material-icons').text();
	if (btnState == 'menu') {
		$('.material-icons').text('close');
	} 
	else {
		$('.material-icons').text('menu');
	}
});

var counter = 1;
setInterval(function () {
	document.getElementById('radio' + counter).checked = true;
	counter++;
	if (counter > 4) {
		counter = 1;
	}
}, 4000);

$(function () {
	var width = 400;
	var animationSpeed = 1000;
	var pause = 3000;
	var currentSlide = 1;

	var $slider2 = $('.sauce');
	var $slideContainer = $slider2.find('.slides2');
	var $slides2 = $slideContainer.find('.slide2');

	var interval;

	function startSlider() {
		interval = setInterval(function () {
			$slideContainer.animate({ 'margin-left': '-=' + width }, animationSpeed, function () {
				if (++currentSlide === $slides2.length) {
					currentSlide = 1;
					$slideContainer.css('margin-left', 0);
				}
			});
		}, pause);
	}
	function pauseSlider() {
		clearInterval(interval);
	}

	$slideContainer.on('mouseenter', pauseSlider).on('mouseleave', startSlider);

	startSlider();
});

$(function () {
	var width = 400;
	var animationSpeed = 1000;
	var pause = 3000;
	var currentSlide = 1;

	var $slider2 = $('.side');
	var $slideContainer = $slider2.find('.slides2');
	var $slides2 = $slideContainer.find('.slide2');

	var interval;

	function startSlider() {
		interval = setInterval(function () {
			$slideContainer.animate({ 'margin-left': '-=' + width }, animationSpeed, function () {
				if (++currentSlide === $slides2.length) {
					currentSlide = 1;
					$slideContainer.css('margin-left', 0);
				}
			});
		}, pause);
	}
	function pauseSlider() {
		clearInterval(interval);
	}

	$slideContainer.on('mouseenter', pauseSlider).on('mouseleave', startSlider);

	startSlider();
});

$(function () {
	var width = 400;
	var animationSpeed = 1000;
	var pause = 3000;
	var currentSlide = 1;

	var $slider2 = $('.style');
	var $slideContainer = $slider2.find('.slides2');
	var $slides2 = $slideContainer.find('.slide2');

	var interval;

	function startSlider() {
		interval = setInterval(function () {
			$slideContainer.animate({ 'margin-left': '-=' + width }, animationSpeed, function () {
				if (++currentSlide === $slides2.length) {
					currentSlide = 1;
					$slideContainer.css('margin-left', 0);
				}
			});
		}, pause);
	}
	function pauseSlider() {
		clearInterval(interval);
	}

	$slideContainer.on('mouseenter', pauseSlider).on('mouseleave', startSlider);

	startSlider();
});