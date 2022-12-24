function emptyCheck(){
	var ok = true;
	$('.chk').each(function(){
		if( $.trim($(this).val())=='' ){
			var title =    $(this).attr('placeholder') ?  $(this).attr('placeholder') :$(this).attr('title') ;
			alert(title + ' 입력하세요');
			$(this).val('');
			$(this).focus();
			ok = false;
			return ok;
		}		
	});
	return ok;
} 