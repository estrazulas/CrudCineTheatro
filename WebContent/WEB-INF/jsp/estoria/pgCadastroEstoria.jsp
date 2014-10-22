<form id="cadestoriausuario" enctype="multipart/form-data" action="<c:url value="/estoria/fazCadastroEstoria"/>"	method="POST" class="form-horizontal">

<div class="jumbotron">

<h2>Cadastro de estória:</h2>

	<fieldset>
		
		
		<div class="control-group">
			<label class="control-label" for="nomeestoria">Nome da Estória:</label> 
			<div class="controls">
				<input id="nomeestoria" name="estoria.nome"   class="input-xlarge"  type="text" value="${estoria.nome}" />
	      	</div>			
		</div>	
			
		<div class="control-group">
			<label class="control-label" for="txtestoria">Texto:</label> 
			<div class="controls">
				<textarea id="txtestoria" class="ckeditor" name="estoria.texto" rows="10" cols="90" >${estoria.texto}</textarea>
	      	</div>			
		</div>	
			
		<div class="control-group">
			<label class="control-label" for="categoria">Categoria:</label> 
			<div class="controls">
				 <select id="categoria" style="width:13em;" name="estoria.categoria">
				 	<option value="">Categoria...</option>
				 	<c:forEach items="${categorias}" var="categoria">
				 		<option value="${categoria.name()}" <c:if test="${estoria.categoria.equals(categoria)}"> selected="selected" </c:if> >${categoria.getDescricao()}</option> 
				 	</c:forEach>
				 </select>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label" for="periodo">Período:</label> 
			<div class="controls">
				 <select id="periodo"  style="width:13em;"  name="estoria.periodo">
				 	<option value="">Período...</option>
				 	<c:forEach items="${periodos}" var="periodo">
				 		<option value="${periodo.name()}" <c:if test="${estoria.periodo.equals(periodo)}"> selected="selected" </c:if>>${periodo.getDescricao()}</option> 
				 	</c:forEach>
				 </select>
			</div>
		</div>	
				
		<div class="control-group">
			<label class="control-label" for="imagem">Imagem:</label> 
			<div class="controls">
				<input id="imagem" name="arquivoImagem"  type="file"  value="Imagem" /> 
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="audio">Audio:</label> 
			<div class="controls">
				<input id="audio" name="arquivoAudio"  type="file"  value="Audio" /> 
			</div>
		</div>
				
		<input type="hidden" id="estoria.id" name="estoria.id" value="${estoria.id}"/>
		
	</fieldset>



</div>

<c:if test="${ not usuarioWeb.isLogado() }">
<div class="jumbotron">

<h2>Dados Pessoais:</h2>


	<fieldset>
		
		
		<div class="control-group">
			<label class="control-label" for="nome">Nome Completo:</label> 
			<div class="controls">
				<input id="nome" name="usuario.nome"   class="input-xlarge"  type="text" value="${usuario.nome}" />
	      	</div>			
		</div>	
		
		<div class="control-group">
			<label class="control-label" for="email">E-mail:</label> 
			<div class="controls">
				<input id="email" name="usuario.email" <c:if test="${isedicao}">disabled="disabled"</c:if> class="input-xlarge" type="text"  value="${usuario.email}" /> 
			</div>
		</div>	
		
		<div class="control-group">
			<label class="control-label" for="cidade">Cidade:</label> 
			<div class="controls">
				<input id="cidade" name="usuario.cidade"  class="input-xlarge" type="text"  value="${usuario.cidade}" /> 
			</div>
		</div>	
		
		<div class="control-group">
			<label class="control-label" for="estado">Estado:</label> 
			<div class="controls">
				<input id="estado" autocomplete="off" name="usuario.estado"  class="input-xlarge" type="text"  value="${usuario.estado}" /> 
			</div>
		</div>		
				
		<div class="control-group">
			<label class="control-label" for="telefone">Telefone com DDD(números somente):</label> 
			<div class="controls">
				<input id="telefone" name="usuario.telefone"  class="input-xlarge" type="text"  value="${usuario.telefone}" /> 
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="cpf">CPF(números somente):</label> 
			<div class="controls">
				<input id="cpf" name="usuario.cpf"  class="input-xlarge" type="text"  value="${usuario.cpf}" /> 
			</div>
		</div>	
		
		<div class="control-group">
			<label class="control-label" for="senha">Senha:</label> 
			<div class="controls">
				<input id="senha" name="usuario.senha" autocomplete="off" class="input-xlarge"  type="password" />
	      	</div>			
		</div>	
	   
	    <div class="control-group">
			<label class="control-label" for="senhaconfirma">Confirmação de senha:</label> 
			<div class="controls">
				<input id="senhaconfirma" name="senhaconfirma" class="input-xlarge"  type="password" />
	      	</div>			
		</div>		

				
		<input type="hidden" id="usuario.id" name="usuario.id" value="${usuario.id}"/>
		
	</fieldset>

</div>
</c:if>

<div class="text-center row" >

	<button class="btn btn-sm btn-primary" type="submit" id="submit">Gravar</button>
	<c:if test="${not usuarioWeb.isLogado()}">
		<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='<c:url value="/"/>'">Voltar</button>
	</c:if>
	<c:if test="${usuarioWeb.isLogado()}">
		<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='<c:url value="/estoria/listar"/>'">Voltar</button>
	</c:if>	
</div>


</form>

<script src="<c:url value="/javascripts/crud/usuario.js"/>" charset="UTF-8"></script>
