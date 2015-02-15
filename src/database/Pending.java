package database;

	public class Pending {
		 private long id;
		 private Double latitude;
		 private Double longitude;
		 private String type;
		 private String name;
		 
		 //SETERS
		  public void setId(long id) {
		      this.id = id;
		  }
		  
		 public void setLatitude(Double latitude) {
		   this.latitude = latitude;
		 }
		   
		 public void setLongitude(Double longitude) {
		     this.longitude = longitude;
		  }
		 
		 public void setType(String type) {
		     this.type = type;
		  }
		 
		 public void setName(String name) {
		     this.name = name;
		  }
		 
		 
		 //GETTERS
		 
		 public long getId() {
		     return id;
		   }

		 public Double getLongitude() {
		   return longitude;
		 }
		 public Double getLatitude() {
		  return latitude;
		 }
		 
		 public String getType() {
		  return type;
		 }
		 
		 public String getName() {
		  return name;
		 }
		 
		  // Will be used by the ArrayAdapter in the ListView
		   @Override
		   public String toString() {
			   return name +" ("+type+")";
		   } 
		
		   
		   
		}

