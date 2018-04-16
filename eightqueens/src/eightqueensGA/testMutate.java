package eightqueensGA;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.junit.Test;
public class testMutate {
	 Random r = new Random();
	 private static List<Chromosome> chromo = new LinkedList<Chromosome>();
	 
	 
	 private  void mutate() {
	        for (Chromosome c : chromo) {
	           if (r.nextInt(8) > 2) {
	                String solution = c.getSolution();
	                int first = r.nextInt(solution.length());
	                String s1=solution.substring(first, first + 1);
	                String s2=("" + r.nextInt(8));
	                c.setSolution(solution.replace(s1, s2));              
	                
	            }
	        }
	    }

	 @Test
	 public void testmutate(){
		 String s1="1";
		 String s2="0";
		 String solution="01";
		assertEquals("10",solution.replace(s1, s2));
		 
		 
	 }

}
