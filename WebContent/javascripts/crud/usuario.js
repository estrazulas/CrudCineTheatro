$('#formlogin').validate({
	rules : {
		'login' : {
			required : true,
		},
		
		'senha' : {
			required : true,
		},					

		'captcha' : {
			required : true,
		},
		
		
	},
	messages : {

		'login' : {
			required : "O usuário ou e-mail é obrigatório!",
		},
		
		'senha' : {
			required : "A senha não pode estar em branco!",
		},					

		'captcha' : {
			required:"Preencha as letras!",
		},
	
	},
});


$('#cadusuario').validate({
	rules : {
		'usuario.login' : {
			required : true,
		},
		
		'usuario.nome' : {
			required : true,
			minlength:5
		},
		
		'usuario.email' : {
			required : true,
			email : true,
			maxlength: 150
		},	
		
		'usuario.telefone' : {
			required : true,
			number:true,
			minlength:10
		},
		
		'usuario.cpf' : {
			required : true,
			number:true,
			minlength:11,
			maxlength:11,
			validaCPF:true
		},		
		
		
	},
	
	messages : {

		'usuario.login' : {
			required : "O usuário ou e-mail é obrigatório!",
		},
		
		'usuario.nome' : {
			required : 'O nome completo é obrigatório!',
			minlenght: 'O nome completo é muito curto!'
		},
		
		'usuario.email' : {
			required : 'O campo e-mail é obrigatório!',
			email : 'O e-mail precisa ser válido!',
			maxlength: 'O tamanho do email não pode exceder 150 caracteres!'
		},	
		
		'usuario.telefone' : {
			required : 'O telefone para contato com o elaborador é obrigatório!',
			number:'Digite apenas números no campo de telefone!',
			minlength:'O telefone precisar preenchido com no mínimo 10 dígitos!'
		},
		'usuario.cpf' : {
			required : 'O CPF é obrigatório!',
			number:'Digite apenas números no campo CPF!',
			minlength:'O CPF deve ter pelo menos 11 dígitos',
			maxlength:'O CPF deve ter no máximo 11 dígitos',
			validaCPF:'O CPF digitado não é valido!'
		},
		
	},
    
});


