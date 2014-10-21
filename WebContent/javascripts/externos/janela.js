
function janela(url, largura, altura,nome){
	 var left = (screen.width/2)-(largura/2);
	 var top = (screen.height/2)-(altura/2);
	 myWindow=window.open(url,nome,'width='+largura+',height='+altura+",top="+top+", left="+left+",scrollbars=yes");
	 myWindow.focus();
}

function atualizaJanelaPrincipal(){
	 window.opener.location.reload(true);
}