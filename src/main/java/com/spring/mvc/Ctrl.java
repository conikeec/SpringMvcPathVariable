package com.spring.mvc;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Size;

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

	// Max <= 6, let's say this is an acceptable partial mitigation. 
	@RequestMapping(value="/map/{countryName}/{userName}", method=RequestMethod.GET)
	public String getMapDataSize(@Valid @Size(min=6,max=6)  @PathVariable String input, @PathVariable Map<String, String> path) {
		String country = input;  
		return country;
	}

	// Max of 100 is > 6, so this is an unacceptable partial mitigation. 
    @RequestMapping(value={"/map/{countryName}/{userName}"}, method={RequestMethod.GET})
    public String getMapDataNotSanitized(@Valid @Size(min=6, max=100) @PathVariable String input, @PathVariable Map<String, String> path) {
        String country = input;
        return country;
    }	
	
	// Max of 100 is > 6, but the call uses the getSanitizedParam( ) protection. 
    @RequestMapping(value={"/map/{countryName}/{userName}"}, method={RequestMethod.GET})
    public String getMapDataSanitized(@Valid @Size(min=6, max=100) @PathVariable String input, @PathVariable Map<String, String> path) {
        String country = getSanitizedParam(input);
        return country;
    }	
	
    private String getSanitizedParam(String param) {
    	if (param.length() <= 7) {
    		return param;
    	} else {
    		return ""; 
    	}
    }
    
}
