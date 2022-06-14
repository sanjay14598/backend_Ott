package com.gamotrance.OTT.services;

import java.util.List;

import com.gamotrance.OTT.Model.Help;

public interface HelpServicesDao {
	
	Help addHelp(Help help);
	List<Help> getHelpALL();
	List<Help> getByListHelp(int userId);
	List<Help> getHelpById(int id);
	Help getHelpByIds(int id);
	boolean deleteHelp(Help help);

}
