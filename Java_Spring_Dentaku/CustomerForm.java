package com.example.demo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//Bean
public class CustomerForm {
	@NotNull
    @Size(min = 1, max = 127)
    private String Formula;
	
	public String getFormula() {
        return Formula;
    }

    public void setFormula(String Formula) {
        this.Formula = Formula;
    }
}