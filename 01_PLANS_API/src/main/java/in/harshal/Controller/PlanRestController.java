package in.harshal.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.harshal.constants.AppConstant;
import in.harshal.entity.Plan;
import in.harshal.props.AppProperties;
import in.harshal.service.PlanService;

@RestController
public class PlanRestController {
      
	
	private PlanService planService;
	
	private Map<String, String> messages;
	
	
	public PlanRestController(PlanService planService, AppProperties appProps) {
		this.planService= planService;
		this.messages = appProps.getMessages();
	}
	
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer,String>> planCategories(){
    
		Map<Integer,String> categories = planService.getPlanCategories();
      return new ResponseEntity<>(categories,HttpStatus.OK);
     }
	
	
	
	 @PostMapping("/plan")
	 public ResponseEntity<String> saveplan(@RequestBody Plan plan){
	   	 
		 String responseMsg= AppConstant.EMPTY_STR;
		 boolean isSaved = planService.savePlan(plan);
		 
		 if(isSaved) {
			 String planSaveSucc = 
			 responseMsg = messages.get(AppConstant.PLAN_SAVE_SUCC);
		 }else {
			 responseMsg = messages.get(AppConstant.PLAN_SAVE_FAIL);
		 }
		  return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	 }
	 
	 
	 
	    @GetMapping("/plans")
	    public ResponseEntity<List<Plan>> plans(){
	    	List<Plan> allPlans = planService.getAllPlans();
	    	 return new ResponseEntity<>(allPlans, HttpStatus.OK);	 
	    }
	    

	    
	    @GetMapping("/Plan/{planId}")
	     public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
	    	 Plan plan = planService.getPlanById(planId);
	    	 return new ResponseEntity<>(plan, HttpStatus.OK);
	    	 
	     }
	    
	    
	    
	    @PutMapping("/plan")
	    public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
	    	boolean isUpdated = planService.updatePlan(plan);
	    	 
	    	 String msg =AppConstant.EMPTY_STR;
	    	 if(isUpdated) {
	    		 msg = messages.get(AppConstant.PLAN_UPADTE_SUCC);
	    	 }else {
	    		 msg = messages.get(AppConstant.PLAN_UPDATE_FAIL); 
	    	 }
	    	  return new ResponseEntity<>(msg, HttpStatus.OK);
	    }
	    
	    
	    
	    @DeleteMapping("/plan/{planId}")
	     public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
	    	  boolean isDeleted = planService.deletePlanById(planId);
	    	  
	    	  String msg = AppConstant.EMPTY_STR;
	    	  if(isDeleted) {
	    		  msg = messages.get(AppConstant.PLAN_DELETE_SUCC);
	    	  }else {
	    		  msg = messages.get(AppConstant.PLAN_DELETE_FAIL);
	    	  }
	   	   return new ResponseEntity<>(msg, HttpStatus.OK);
	     }
	    
	     
	    @PutMapping("/status-change/{planId}/{status}")
	    public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String  status){
	    	String msg =AppConstant.EMPTY_STR;
	    	boolean isStatusChange = planService.planStatusChange(planId, status);
	    	
	    	if(isStatusChange) {
	    		msg = messages.get(AppConstant.PLAN_STATUS_CHANGE);
	    	}else {
	    		msg = messages.get(AppConstant.PLAN_STATUS_CHANGE_FAIL);
	    	}
	          return new ResponseEntity<>(msg, HttpStatus.OK);
	    }
	    
}

