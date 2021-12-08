import java.util.Iterator;

import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheQuery;
import com.intersys.objects.Database;
import com.intersys.objects.Id;

import Sample.*;

public class App {

	public static void main(String[] args) {
	      String url="jdbc:Cache://localhost:56773/SAMPLES";
	      String username="_SYSTEM";
	      String pwd="SYS";
	      
	      try {
	    	  Database db = CacheDatabase.getDatabase(url, username, pwd);
	    	  Person person = (Person)(Person._open(db, new Id(1)));
	    	  System.out.println(person.getName());
	    	  System.out.println(person.getDOB());
	    	  
	    	  db.closeObject(person.getOref());
	    	  person = null;
	    			  
	          CacheQuery query = Person.query_ByName(db);
	          java.sql.ResultSet rs=query.execute("A");
	          while (rs.next()){
	        	  System.out.println("Name: "+rs.getString(2));
		      }	    	  
	    	  
	          Object[] qargs = {"30"};
	          String sql = "SELECT Sample.Person.%ID FROM Sample.Person WHERE AGE<?";
	          Iterator iter = db.openByQuery(sql, qargs);
    	      while (iter.hasNext()){
    	    	  Person p = (Person)iter.next();
    	          System.out.println("Name: " + p.getName()+"Age: " + p.getAge());
    	       }
	          
	      }
	      catch(Exception e) {
	    	  e.printStackTrace();
	      }
	}

}
