/**
 * 공통 함수 선언
 */







$(function() {
	//달력
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		showMonthAfterYear: true, //년도 뒤에 월표시
		changeYear: true,
		changeMonth: true,

	})

	//파일첨부관련처리
	$('#attach-file').change(function() {
		//console.log( this.files[0] ) //선택한 파일 정보
		var attached = this.files[0];
		if (attached) {
			$('#delete-file').css('display', 'inline'); // 파일선택하면 삭제 버튼 보이게 처리
			//미리보기 태그가 있는 경우 선택한 이미지파일을 보이게 처리
			if ($('#preview').length > 0) {
						
				
				//선택한 파일이 이미지인 경우만
				if(isImage(attached.name)){
				
					$('#preview').html('<img class=\'profile\'>');
					var reader = new FileReader();

					reader.onload = function(e) {
						$('#preview img').attr('src', e.target.result);
					}
						reader.readAsDataURL(attached);
				} else {
					//이미지가 아닌경우
					$('#preview').empty();
					$('#delete-file').css('display','none');
					initAttach();
				}
				
				
			}
		} else {
			initAttach();
		}
	});
	$('#delete-file').click(function(){
		initAttach();
	})


})

function initAttach(){
		// 휴지통 버튼누르면 실제 첨부한 파일 정보 없애기
		$('#attach-file').val('');
		
		// 삭제버튼 안 보이게 처리 
		$('#delete-file').css('display','none');
		
		// 이미지 안보이게
		$('#preview').html('');
		
}



//이미지 인지 판단하는 함수
function isImage( filename ){
	// 파일의 확장자로 이미지파일인지 판단 : abc.png abc.JPG
	var ext = filename.substring( filename.lastIndexOf('.')+1 ).toLowerCase();
	var imgs = ['png','jpg','jpeg','bmp','gif','webp'];
	if ( imgs.indexOf (ext) == -1 ){
		return false;		
	} else {	
		return true;
	}
	
	
}



//필수입력항목 입력여부확인 함수 
function emptyCheck() {
	var ok = true;
	$('.chk').each(function() {
		if ($.trim($(this).val()) == '') {
			var title = $(this).attr('placeholder');
			alert(title + ' 입력하세요');
			$(this).val('');
			$(this).focus();
			ok = false;
			return ok;
		}
	});
	return ok;
}


