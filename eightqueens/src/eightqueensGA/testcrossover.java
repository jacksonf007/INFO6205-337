package eightqueensGA;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class testcrossover {
	private static List<Chromosome> chromo = new LinkedList<Chromosome>();
	Random r = new Random();
	
	  private  void crossOver() {
		  for (int n = 0; n < 8; n++) {
	           //StringBuffer sb = new StringBuffer();
	            String sb ="";
	            for (int i = 0; i < 8; i++) {
	               sb= sb+String.valueOf(r.nextInt(8));
	            	
	            }
	            Chromosome c = new Chromosome(n, sb.toString());
	            chromo.add(c);
	            //chromoFit.put(c, evolutionfint(c.getSolution()));
	        }
	    	int i=0; while(i < chromo.size())
	        
	        {
	            int ranIndex = 1 + r.nextInt(8 - 1);
	            Chromosome c1 = chromo.get(i);
	            Chromosome c2 = chromo.get(i + 1);
	            String solution1 = c1.getSolution();
	            String solution2 = c2.getSolution();
	            String temp1 =solution1.substring(0, ranIndex)+solution2.substring(ranIndex, solution2.length());
	            String temp2 =solution2.substring(0, ranIndex)+solution1.substring(ranIndex, solution1.length());   
	            c1.setSolution(temp1);
	            c2.setSolution(temp2);
	     
	            i = i+2;
	        }
	    }
	

	@Test
	public void testCrossover01(){
	//	Random r = new Random();
		//int n = r.nextInt();
		
		String solution1 = "00000000";
		String solution2 = "66666666";
		 String temp1=solution1.substring(0, 3)+solution2.substring(3, solution2.length());
		 String temp2=solution2.substring(0, 3)+solution1.substring(3, solution1.length());
		 
		assertEquals("00006666", solution1 );
		assertEquals("66660000", solution2 );
		
	}
}