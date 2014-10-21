<style type="text/css">
      .form-signin {
 		max-width: 600px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
   	 .form-signin .form-signin-heading{
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
        width:100%;
      }
       div#wrappingright{
       		text-align:right;
       }
       input#submitbtn{
            padding:1em;
            background-color: #51a351;
            font-size:1.3em;
       }  
       h3{
       	font-weight: bold;
       }
</style>

<form id="formlogin" class="form-signin" method="POST" action="<c:url value="/usuario/dologin"/>">
	<div id="wrapping">
	<font style="font-weight:bold">Já possui cadastro? Faça login e registre a sua estória!</font><br/><br/>
	</div>
	<div id="wrapping">
		Usu&aacute;rio:<input type="text" name="login" id="name" placeholder="Informe o e-mail de cadastro" autocomplete="off" class="input-block-level">
	</div>
	<div id="wrapping">
		Senha:<input type="password" name="senha" id="email" placeholder="Informe a senha" autocomplete="off" class="input-block-level">
	</div>

	<c:if test="${usuarioWeb.muitasTentativasLogin()}">
		<div class="control-group">
			<label class="control-label" for="captcha"  style="margin-top:0.5em;color:red" >Digite as letras da imagem abaixo:*</label> 
			<div class="controls">
				<input type='text' name='captcha' style="margin-top:0.5em;margin-bottom:0.5em;"  value=''><br/>
				<img src="<c:url value="/captcha"/>">			
			</div>
		</div>
	</c:if> 
	<br/><a href="<c:url value="/usuario/cadastro"/>">Cadastre-se e registre a sua primeira estória</a>	
	<div id="wrappingright">
		<input type="submit" name="submit" id="submitbtn" class="btn btn-large btn-success span3" value="Entrar">
	</div>
	
</form>

<script src="<c:url value="/javascripts/crud/usuario.js"/>" charset="UTF-8"></script>
