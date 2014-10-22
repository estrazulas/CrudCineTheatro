package com.rhcloud.pugmg.cinetheatrosys.modelo;


/**
 * @author Daniel Severo Estrázulas
 */
public enum TipoCategoria {

	ROMANTICA,
	TURISMO, 
	HISTORIA,
	PERSONALIDADES,
	ARQUITETURA,
	CULTURA;
	
    public String getDescricao() {
		if(this.equals(TipoCategoria.ROMANTICA)){
			return "Romântica";
		}else if(this.equals(TipoCategoria.TURISMO)){
			return "Turismo";
		}
		else if(this.equals(TipoCategoria.HISTORIA)){
			return "História";
		}else if(this.equals(TipoCategoria.PERSONALIDADES)){
			return "Personalidades";
		}else if(this.equals(TipoCategoria.ARQUITETURA)){
			return "Arquitetura";
		}else if(this.equals(TipoCategoria.CULTURA)){
			return "Cultura";
		}
		return "??";
    }
    
    @Override
    public String toString() {
        return this.getDescricao();
    }
}
