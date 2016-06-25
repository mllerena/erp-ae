/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aeopensolutions.view.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.aeopensolutions.model.ejb.facades.AdUserFacade;
import org.aeopensolutions.model.entities.AdUser;

/**
 *
 * @author Marcos Llerena <mllerenap@aeosolutions.com>
 */
@ManagedBean(name = "adUserView")
@ViewScoped
public class AdUserController implements Serializable{
    
    @Inject
    private AdUserFacade adUserFacade;
    
    protected List<AdUser> listAdUser;
    
    @PostConstruct
    public void initialize(){
        listAdUser = adUserFacade.findAll();
    }

    public List<AdUser> getListAdUser() {
        return listAdUser;
    }

    public void setListAdUser(List<AdUser> listAdUser) {
        this.listAdUser = listAdUser;
    }
    
    
    
}
