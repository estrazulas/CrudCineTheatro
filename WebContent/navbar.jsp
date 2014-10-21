<style type="text/css">
body {
	padding-top: 20px;
	padding-bottom: 20px;
}

.navbar {
	margin-bottom: 20px;
}
</style>
<div class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a class="navbar-brand" href="<c:url value="/"/>">CINETHEATRO</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:if test="${usuarioWeb.isLogado()}">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Estórias<b class="caret"></b></a>

						<ul class="dropdown-menu">
								<li class="dropdown-header">COMISSÃO</li>
								<li><a
									href="<c:url value="/estoria/pgCadastroEstoria"/>">Cadastro
										estórias</a></li>
							
					</ul></li>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<c:url value="/usuario/logout"/>">Sair(x)</a></li>
					</ul>

				</c:if>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="<c:url value="/"/>">Home</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</div>