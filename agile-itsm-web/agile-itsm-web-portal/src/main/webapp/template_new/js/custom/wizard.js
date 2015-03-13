// wizard

	$('.wizard_steps ul li:not(".current") a').live('click',function(){
	
	
		$('.wizard_steps ul li').removeClass('current');
		$(this).parent('li').addClass('current');
		
		var step = $(this).attr('href');
		var step_num = $(this).attr('href').replace('#step_','');
		var step_multiplyby = (100 / $(".wizard_steps > ul > li").size());
		var prog_val = (step_num*step_multiplyby);
		
		$( ".wizard_progressbar").progressbar({ value: prog_val });
		
		$('.wizard_content').children().hide();
		$('.wizard_content').children(step).fadeIn(1000);
		
		return false;
	});
	
	var initialProg = (100 / $(".wizard_steps > ul > li").size());
	$( ".wizard_progressbar").progressbar({ value : initialProg});
	
	
	$('.button_bar button.move').live('click', function(){
		
		var goTo = $(this).attr("data-goto").replace('step_','');;
	
		$('.wizard_steps ul li:nth-child('+goTo+') a').trigger('click');

	});
