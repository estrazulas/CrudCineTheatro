$('#frmavaliar').validate({
	rules : {
		'avaliacao.resposta' : {
			required : true,
			minlength:30
		},
		
		'avaliacao.decisao' : {
			temSelecao:'0',
		},	
			
	},
	
	messages : {

		'avaliacao.resposta' : {
			required : 'O campo resposta precisa ser preenchido!',
			minlength: 'A resposta está muito breve!'
		},
		
		'avaliacao.decisao' : {
			temSelecao:'A decisão precisa ser selecionada!',
		},		
	},
	
	errorPlacement: function(error, element) 
    {
       if (element.attr("name") == "avaliacao.decisao") 
       {
       		error.insertBefore("#resposta");
       }
      	else{
    	    error.insertBefore(element);
       }
    },
    
});

function gravar(){
	$('#frmavaliar').submit();
}

function fechar(){
	window.close();
}

$( window ).unload(function() {
	opener.loadRecursos();
});
