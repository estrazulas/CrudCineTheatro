package com.rhcloud.pugmg.cinetheatrosys.modelo;


/**
 * @author Daniel Severo Estrázulas
 */
public enum TipoPeriodo {

	A19301960,
	B19611990,
	C19911999, 
	D2006hoje;
	
    public String getDescricao() {
		if(this.equals(TipoPeriodo.A19301960)){
			return "1930-1960";
		}else if(this.equals(TipoPeriodo.B19611990)){
			return "1961-1990";
		}
		else if(this.equals(TipoPeriodo.C19911999)){
			return "1991-1999";
		}else if(this.equals(TipoPeriodo.D2006hoje)){
			return "2006-hoje";
		}
		return "??";
    };
    
    @Override
    public String toString() {
        return this.getDescricao();
    }
}
