package eightqueensGA;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

/*
 Genetic Algorithm Solving Eight Queen Problems
   Steps:
   1. first randomly generate a certain number of chromosomes;
   2. Select better individual chromosomes from the population;
   3. two parent chromosomes are generated in sequence for the two parent chromosomes. In theory, the daughter chromosome will gradually carry out more excellent chromosomes with excellent parent chromosomes;
   4. a gene encoding a random variation chromosome;
   By performing the above steps 2, 3, and 4 in a loop, the optimal solution is finally obtained.
*/
public class QueenGa {
	private static int PopSize = 8;
    private static int Board = 8;
    private static int time = 100000;
    private static int MutateP = 2;
    private static int Bestfitness = 0;
    private static Random r = new Random();
    private static List<Chromosome> chromo = new ArrayList<Chromosome>();
    private static Map<Chromosome, Integer> chromoFit = new LinkedHashMap<Chromosome, Integer>();

    //initinal
    private  void create() {
        for (int n = 0; n < PopSize; n++) {
           //StringBuffer sb = new StringBuffer();
            String sb ="";
            for (int i = 0; i < Board; i++) {
               sb= sb+String.valueOf(r.nextInt(Board));
            	//sb.append(r.nextInt(Board));
            }
            Chromosome c = new Chromosome(n, sb.toString());
            chromo.add(c);
            chromoFit.put(c, evolutionfint(c.getSolution()));
        }
    }

    //判断是否求得最优解
    private  boolean opSolution() {
        for (Chromosome c : chromo) {
            if (evolutionfint(c.getSolution()) == Bestfitness) {
                return true;
            }
        }
        return false;
    }

    // 选择
    private  void select() {
        double average = calAveFit();
        List<Chromosome> tem = new ArrayList<Chromosome>(chromo);
        chromo.clear();
        Iterator<Entry<Chromosome, Integer>> it = chromoFit.entrySet().iterator();
        while(it.hasNext()){
        	Entry<Chromosome, Integer> entry =it.next();
        	if (entry.getValue() >= average) {
                chromo.add(entry.getKey());
            }
        }
      /*  
        for (Entry<Chromosome, Integer> entry : chromoFit.entrySet()) {
            if (entry.getValue() >= average) {
                chromo.add(entry.getKey());
            }
        }
*/
        Collections.sort(tem);
        if (chromo.size() < PopSize / 2) {
            int firstIndex = chromo.size();
            int lastIndex = firstIndex + (PopSize / 2 - chromo.size());
            chromo.addAll(tem.subList(firstIndex, lastIndex));
        }
        int n = 0;
        Iterator<Chromosome> i =chromo.iterator();
        while(i.hasNext()){
        	Chromosome c=i.next();
        	c.setId(n);
            n = n+ 1;
        	
        }
   

        while (chromo.size() < PopSize && chromo.size() > 1) {
            Chromosome e = chromo.get(r.nextInt(chromo.size()));
            try {
                Chromosome clone = (Chromosome) e.clone();
                clone.setId(n);
                chromo.add(clone);
                n =n + 1;
            } catch (CloneNotSupportedException e1) {
            }
        }
        if (chromo.size() == 1) {
            System.out.println("chromosomes : " + chromo.size());
        }
        chromoFit.clear();
        refreshChromosomes();
    }

    //杂交
    private  void crossOver() {
    	int i=0; while(i < chromo.size())
        
        {
            int ranIndex = 1 + r.nextInt(Board - 1);
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
    // THe Chromesome mutate
    private  void mutate() {
        for (Chromosome c : chromo) {
            if (r.nextInt(Board) > MutateP) {
                String solution = c.getSolution();
                int first = r.nextInt(solution.length());
                String s1=solution.substring(first, first + 1);
                String s2=("" + r.nextInt(Board));
                c.setSolution(solution.replace(s1, s2));              
                //chromosome.setSolution(solution.replace(solution.substring(first, first + 1), ("" + r.nextInt(Board))));
            }
        }
    }

   
/*
    private  int getRandomIndex() {
        return (1 + r.nextInt(Board - 1));
    }
*/
    private  void refreshChromosomes() {
        Collections.sort(chromo);
        chromoFit.clear();
        for (Chromosome chromosome : chromo) {
            chromoFit.put(chromosome, evolutionfint(chromosome.getSolution()));
        }
    }

    public static int evolutionfint(String solution) {
        //int value=0;
    	int value = Bestfitness;
        for (int x1 = 0; x1 < Board; x1++) {
        int y1 = Integer.parseInt(solution.substring(x1, x1 + 1));
        for (int x2 = x1 + 1; x2 < Board; x2++) {
        int y2 = Integer.parseInt(solution.substring(x2, x2 + 1));
        if (Math.abs(y2 - y1) == (x2 - x1) || (y2 - y1) == 0) {
                    value =value- 1;
                }
            }
        }
        return value;
    }
    // Calculate the average of fitness
    private  double calAveFit() {
        int total = 0;
        for (Integer fitness : chromoFit.values()) {
            total += fitness;
        }
        double ave = total*1.0 / chromoFit.size();
        return ave;
    }
    private   void printChromosomes() {
        for (Entry<Chromosome, Integer> entry : chromoFit.entrySet()) {
            System.out.println(" THe Fitness : " + entry.getValue()+"-----------"+"The Solution : " + entry.getKey().getSolution());
        }
    }
    
    public void CalTime(Calendar a,Calendar b){
    	 long x = b.getTimeInMillis() - a.getTimeInMillis();
    	 long y = x/1000;
    	 x = x - 1000*y;
    	 System.out.println("The cost time："+y+"."+x+" seconds");
    	 }
    
    public  void run() {
    	 int f = 0; int i=1;
         while(i<Board){
         	f+=(Board -i);
         	i++;
         }
         Bestfitness = f;
        create();
        System.out.println("initial：");
        printChromosomes();
        System.out.println("initial the average of fitness：" + calAveFit());
        System.out.println("After the GA,The solution is:");
        int iterations = 0;
        while (!opSolution() && iterations < time) {
        	//System.out.println("----------------- The "+iterations+" Generation -------------------------");
            select();
            crossOver();
            mutate();
            iterations++;       
        }
        refreshChromosomes();
        printChromosomes();
        System.out.println("------------The average of fitness：" + calAveFit());
        System.out.println("------------The iterations is : " + iterations);
        System.out.println("when fitness="+ Bestfitness+",the Solution is the best value.");
        
    }

    public static void main(String[] args) {
        Calendar a = Calendar.getInstance(); 
        QueenGa queenGa = new QueenGa();
         queenGa.run();
         Calendar b = Calendar.getInstance(); 
        queenGa.CalTime(a, b);
    }
}