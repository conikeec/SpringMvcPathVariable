package com.spring.mvc;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Ctrl {

	// Method with multiple arguments appended in the url.
	@RequestMapping(value="/str/{countryName}/{userName}", method=RequestMethod.GET) 
	public ModelAndView getStringData(@PathVariable("countryName") String cname, @PathVariable("userName") String uname) {

		ModelAndView m = new ModelAndView();
		m.addObject("msg", "Country: " + cname + " <=======>  Name:  " + uname);
		m.setViewName("success");
		return m;
	}

	// Method with multiple arguments and fetching it from the map.
	@RequestMapping(value="/map/{countryName}/{userName}", method=RequestMethod.GET)
	public ModelAndView getMapData(@PathVariable Map<String, String> path) {

		String country = path.get("countryName"), 
				name = path.get("userName");

		ModelAndView m = new ModelAndView();
		m.addObject("msg", "Country: " + country + " <=======>  Name:  " + name);
		m.setViewName("success");
		return m;
	}
}