/* Operations */
$(function(){
	
	$('body').on('click', 'a#btnimport', function() {
		var filePath = $("#filePath").val();
		
		$('#importInfoForm').submit();
		
		/*var filePath = $("input#filePath").val();
		console.log("filePath ",filePath);
		
		 $.ajax({
	         url: '/jmchr/employee/importexcel',
	         type: 'post',
	         dataType: 'json',
	         data: filePath,
	         success: function(data) {
	             alert(data);
	         }
		 });*/
		
	});
	
	
	
    // The options used for the login/register modal

/*	
    function copyShippingForm() {
        $('.cloneable').each(function() {
            $("#billing_info input[name='" + $(this).attr('name') + "']").val($(this).val()).attr("disabled", "disabled");
            $("#billing_info select[name='" + $(this).attr('name') + "']").val($(this).val()).attr("disabled", "disabled");
        })
    }*/

/*    var updateState = function(newState,$context){
        $context.find("input[name='address.state']").val(newState);
    }
*/
	/*$('body').on('click', 'a#btn-import-employee', function() {
		
		var filePath = $("#text-file-path").val();
		console.log("filename ",filename);
		
		 $.ajax({
             url: '/jmchr/employee/importexcel',
             type: 'post',
             dataType: 'json',
             data: filename,
             success: function(data) {
                 
             }
		 });
		
    });*/
	
	/*$('#btn-import-employee').submit(function( event ) {
	  
		var filename = $("#text-file-path").val();
		console.log("filename ",filename);
		
		alert( "Handler for .submit() called." );
	  event.preventDefault();	  
	});*/
   
});
