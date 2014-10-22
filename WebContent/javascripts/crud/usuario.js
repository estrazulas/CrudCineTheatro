$.validator.addMethod('filesize', function(value, element, param) {
    return  this.optional(element) || (element.files[0].size <= param);
});


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

$(document).ready(function(){
	$('#cadestoriausuario').validate({
		
		rules : {
			
			'estoria.texto' : {
			  required: function() 
	          {
	           CKEDITOR.instances.txtestoria.updateElement();
	          },
			  minlength:5
			},		
			
			'estoria.nome' : {
				required : true,
				minlength:5
			},	
			'usuario.nome' : {
				required : true,
				minlength:5
			},
		
			'usuario.estado' : {
				required : true,
				maxlength:2
			},
			
			'usuario.cidade' : {
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
	
			'arquivoImagem':{
				required:true,
				accept: "image/*",
				filesize:3000000
			},
			
			'arquivoAudio':{
				required:true,
				accept: "audio/*",
				filesize:10000000
			},
			
			'estoria.categoria':{
				temSelecao:''
			},
			'estoria.periodo':{
				temSelecao:''
			},
			'usuario.senha':{
				minlength:8,
				required:true
			},
			'senhaconfirma':{
				equalTo : "#senha"
			}
		},
		
		messages : {
			
			'estoria.texto' : {
				required : 'O campo texto é obrigatório',
				minlength:'O campo está muito curto!'
			},
			
			'usuario.estado' : {
				required : "O estado é obrigatório!",
				maxlength: "O estado precisa ter 2 letras!"
			},
			
			'usuario.cidade' : {
				required : "A cidade é obrigatória!",
				minlength: "A cidade está muito curta!"
			},		
			
			'estoria.nome' : {
				required : 'O nome é obrigatório!',
				minlenght: 'O nome é muito curto!'
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
			
			'usuario.senha':{
				minlength:'A senha precisa ter 8 caracteres',
				required:'A senha é obrigatória'
			},
			
			'senhaconfirma':{
				equalTo : 'A senha de confirmação precisa ser igual a senha!'
			},
			
			'usuario.cpf' : {
				required : 'O CPF é obrigatório!',
				number:'Digite apenas números no campo CPF!',
				minlength:'O CPF deve ter pelo menos 11 dígitos',
				maxlength:'O CPF deve ter no máximo 11 dígitos',
				validaCPF:'O CPF digitado não é valido!'
			},
			
			'arquivoImagem':{
				required:'Selecione uma imagem!',
				accept: "Só é aceito formato de imagem!",
				filesize:"Tamanho máximo 3MB"
			},
			
			'arquivoAudio':{
				required:'Selecione um arquivo de audio!',
				accept: "Só é aceito formato de audio!",
				filesize:"Tamanho máximo 10MB"
			}
			
		},
		
		ignore: [] ,
        
		errorPlacement: function(error, element) 
        {
            if (element.attr("name") == "estoria.texto") 
           {
            error.insertBefore("textarea#txtestoria");
            } else {
            error.insertAfter(element);
            }
        }
	    
	});
});

