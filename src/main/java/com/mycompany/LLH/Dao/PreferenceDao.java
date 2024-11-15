/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.LLH.Dao;

import com.mycompany.LLH.Model.Preference;
import java.util.List;

/**
 *
 * @author User
 */
public interface PreferenceDao {
    List<Preference> getPreferences();
}
