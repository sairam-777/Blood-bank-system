package bb.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;


import bb.rep.RequestsRepository;
import bb.rep.DonorsRepository;

@Service
public class RequestsManager 
{
	@Autowired
	RequestsRepository RR;
	@Autowired
	DonorsRepository DR;
	

	public String insertrequest(Requests R)
	{
	   try {
		   RR.save(R);
		  return "200::new Request Added successfully";
	   }
	   catch(Exception e) {
		   return "401::"+e.getMessage();
	   }
	   
		
	}
	public String readrequest() {
		try 
		{
		  List<Requests> requestList = RR.findAll();
		  return new GsonBuilder().create().toJson(requestList);
			
		} 
		catch (Exception e) 
		{
			return "401::"+e.getMessage();
		}
	}
	public String getRequestDetailsbyId(Long id) {
		try 
		{
			Requests R=RR.findById(id).get();
			return new GsonBuilder().create().toJson(R); 
			
		} 
		catch (Exception e)
		{
			return "401::"+e.getMessage();
		}
	}

	public String updaterequest(Requests R) 
	{
		try
		{
		    RR.save(R);
		    return "200 :: Request details are updated";	
		} 
		catch (Exception e)
		{
			return "401::"+e.getMessage();
		}
	}
	public String deleterequest(Long id) {
	try 
	{
		RR.deleteById(id);
		return "200::Request details deleted successfully";
	}
	catch (Exception e) 
	{
		return "401::"+e.getMessage();
	}
	
	}
	
	public String getInventory() {
	    try {
	        List<Donors> donors = DR.findAll();
	        Map<String, Long> donorInventory = donors.stream()
	            .collect(Collectors.groupingBy(
	                d -> d.getBloodgroup() + d.getRhfactor(),
	                Collectors.counting()
	            ));

	        // Return the updated inventory
	        return new GsonBuilder().setPrettyPrinting().create().toJson(donorInventory);
	    } catch (Exception e) {
	        return "401::" + e.getMessage();
	    }
	}

	

}
