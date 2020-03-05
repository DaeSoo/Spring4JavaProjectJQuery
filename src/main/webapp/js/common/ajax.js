function _ajaxPOST(url,param,successBack){
	_Ajax(url,'POST',param,successBack);
}

function _ajaxGET(url,param,successBack){
	_Ajax(url,'GET',param,successBack);
}

function _Ajax(url,type,param,successBack){
	var JsonData = "";
/*	console.log(typeof param)
	if(typeof param == 'object'){
		for(var key in param.serialize()){
			console.log(key);
			console.log(param[key]);
		}
	} */
	 $.ajax({
         url:url,
         type:type,
         async: false ,
         data: param,
         dataType:'json',
         timeout: 3000,
         contentType : 'application/json; charset=UTF-8',
         error: function (request, status, error) {      
        	 Alert("처리중오류발생")
         }, success: function (data) {
        	 successBack(data);
        	 
         }, beforeSend: function () {
        	 
         }
         
     });
	 
}
$.fn.serializeObject = function()

{

   var o = {};

   var a = this.serializeArray();

   $.each(a, function() {

       if (o[this.name]) {

           if (!o[this.name].push) {

               o[this.name] = [o[this.name]];

           }

           o[this.name].push(this.value || '');

       } else {

           o[this.name] = this.value || '';

       }

   });

   return o;

};