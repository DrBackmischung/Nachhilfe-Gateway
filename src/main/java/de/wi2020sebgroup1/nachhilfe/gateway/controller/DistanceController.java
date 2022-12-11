package de.wi2020sebgroup1.nachhilfe.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import de.wi2020sebgroup1.nachhilfe.gateway.Variables;
import de.wi2020sebgroup1.nachhilfe.gateway.service.LogService;

@Controller
@RestController
public class DistanceController {
	
	@Autowired
	LogService logger;
	
	@GetMapping("/distance/{start}/{end}")
	public ResponseEntity<Object> get(@PathVariable String start, @PathVariable String end) {
		
		RestTemplate t = new RestTemplate();
		String URL = Variables.transportationServiceURL+"/api/distance/"+start+"/"+end;
		try {
			ResponseEntity<Object> result = t.getForEntity(URL, Object.class);
			return new ResponseEntity<Object>(result.getBody(), result.getStatusCode());
		} catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("Server error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
