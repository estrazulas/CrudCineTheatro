$("a#confirmdelete").click(function(e) {
	e.preventDefault();
	var location = $(this).attr('href');
	bootbox.confirm("Confirmar exclusão?", function(confirmed) {
		if (confirmed) {
			window.location.replace(location);
		}
	});
});

$.validator.addMethod("temSelecao", function(value, element, arg) {
	return (value != undefined && value!="" );
	
}, "Você deve selecionar um item!");

$.validator.addMethod("validaCPF", function(value, element, arg) {
	return (isCpf(value));
	
}, "Digite um CPF válido!");


$.validator.addMethod('requireOne', function(value, element, arg) {
	var pelomenosumsem= false;
	//procura todos os inputs com esta classe
	$('.'+arg).each(function( index ) {
		if($( this ).is(':checked')){
			pelomenosumsem = true;
			//sai do foreach
			return false;
		}
	});
    //retorna se teve pelo menos um selecionado
	return pelomenosumsem;
    
}, 'Selecione pelo menos uma das opções! ');

function isEmpty(variavel){
	return (variavel ==undefined || variavel == "" || variavel ==null);
}

function selecaoTodos(namechecks){
    var checkBoxes = $("input[name="+namechecks+"\\[\\]]");
    checkBoxes.prop("checked", !checkBoxes.prop("checked"));
}

function peloMenosUmChecado(nomedocheckbox){
	return $("input[name="+nomedocheckbox+"]:checked").length > 0;
}

function verificaLetrasEVirgula(checkString){
	if (checkString != "") {
	    if ( /[^\d\,]/.test(checkString)) {
	    	alert("Utilize apenas números e virgulas");
	    	return (false);
	    }
	}
	return true;
}