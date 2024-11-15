/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.ServiceImpl;

import com.mycompany.LLH.Service.PreferenceService;
import com.mycompany.LLH.Dao.PreferenceDao;
import com.mycompany.LLH.Model.Preference;
import java.util.List;

/**
 *
 * @author User
 */
public class PreferenceServiceImpl implements PreferenceService{

    private PreferenceDao preferenceDao;
    
    public PreferenceServiceImpl(PreferenceDao preferenceDao){
        this.preferenceDao = preferenceDao;
    }
    
    @Override
    public List<Preference> getPreferences() {
        return preferenceDao.getPreferences();
    }
    
    
    
}
