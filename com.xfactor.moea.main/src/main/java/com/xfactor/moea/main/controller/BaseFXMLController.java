package com.xfactor.moea.main.controller;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;


public class BaseFXMLController{
	
	
    
    @Autowired
    private ResourceBundle i18nResourceBundle;
    
    public ResourceBundle getI18nResourceBundle() {
        return i18nResourceBundle;
    }
    
}
