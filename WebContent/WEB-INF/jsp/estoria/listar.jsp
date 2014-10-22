<link rel="stylesheet" href="<c:url value="/lib/DataTables-1.10.2/media/css/jquery.dataTables.min.css"/>" type="text/css"/>
<script src="<c:url value="/lib/DataTables-1.10.2/media/js/jquery.dataTables.min.js"/>" type="text/javascript"></script> 
<script>
$(document).ready(function(){
	 
	$.LABDT = new Object();
	$.LABDT.url = "<c:url value="/"/>";
	$.LABDT.dataTables_LanguageFile = $.LABDT.url + "javascripts/datatables/pt-BR.txt";
	$.LABDT.dataTables_SearchName = "Procurar por título";
	
	$('#estoriasDataTable').dataTable({
		"bAutoWidth":true, 
		"bPaginate": true,  
		"bFilter": true,  
		"bSort": true,  
		"bInfo": true,  
		"bJQueryUI": false,  
		"sPaginationType": "full_numbers", 
		"aoColumns": [
			             null, // Id
			             null, // Nome
			             null, // Categoria
			             null, // Período
			             {
			            	 "mData": null,
			            	 "bSortable": false,		            	 
			            	 "mRender": function( data, type, full )
			            	 {
			            		 var strlinks="";
			            		 	strlinks += "<a href='javascript:location.href = \"" + $.LABDT.url + "estoria/remover/" + data[0] + "\"'>Remover</a>";
			            		 return strlinks;
			            	 }
			             }			            
			             ],					             
		"bProcessing": true,
		"bServerSide": true,  
		"oLanguage": {
			"sUrl": $.LABDT.dataTables_LanguageFile,
			"sSearch": $.LABDT.dataTables_SearchName
		},		
		"sAjaxSource": '<c:url value="/estoria/json/paginacao"/>',
		"sServerMethod": "POST"
		
	});
	
});
function janelaEstoria(url){
	janela(url,500,600,'cadestorias');
}
</script>
<h2>Listagem de Estórias:</h2><br />
<table id="estoriasDataTable">
    <thead>  
   		<tr>
   			<th colspan="6" style="text-align: left;" >
   				<a  href="javascript:janelaEstoria('<c:url value="/estoria/pgCadastroEstoria"></c:url>');" title="Nova Estória"><img alt="Nova Estória" src="<c:url value="/imagens/icones/bt_novo.gif"></c:url>"/>Nova Estória</a>
   			</th>
   		</tr>
        <tr> 
        	<th>Id</th> 
            <th>Nome</th>
            <th>Categoria</th>
            <th>Período</th> 
            <th>Ações</th> 
        </tr>  
    </thead> 	
    <tbody>  
           <tr>  
               <td></td>  
               <td></td>  
               <td></td>
               <td></td>
               <td></td>
           </tr>
   	</tbody>
	
</table>