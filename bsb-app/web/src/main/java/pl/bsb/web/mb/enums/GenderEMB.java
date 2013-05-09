package pl.bsb.web.mb.enums;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import bsbapp.model.enums.GenderE;

@ManagedBean
public class GenderEMB {
	public SelectItem[] getGenderValues() {
	    SelectItem[] items = new SelectItem[GenderE.values().length+1];
	    items[0] = new SelectItem(null, "");
	    int i = 1;
	    for(GenderE g: GenderE.values()) {
	      items[i++] = new SelectItem(g, g.getLabel());
	    }
	    return items;
	  }

}
