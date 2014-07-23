$( document ).ready(function() {
$('#cssmenuVertical ul ul li:odd').addClass('odd');
$('#cssmenuVertical ul ul li:even').addClass('even');
$('#cssmenuVertical > ul > li > a').click(function() {
  $('#cssmenuVertical li').removeClass('active');
  $(this).closest('li').addClass('active');	
  var checkElement = $(this).next();
  if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
    $(this).closest('li').removeClass('active');
    checkElement.slideUp('normal');
  }
  if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
    $('#cssmenuVertical ul ul:visible').slideUp('normal');
    checkElement.slideDown('normal');
  }
  if($(this).closest('li').find('ul').children().length == 0) {
    return true;
  } else {
    return false;	
  }		
});
});