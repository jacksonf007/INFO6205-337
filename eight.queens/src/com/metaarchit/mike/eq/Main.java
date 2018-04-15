package com.metaarchit.mike.eq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

/**
 * <p><b>����ͨ���Ŵ��㷨���˻ʺ����������(�ȽϾ���)</b></p>
 * ���裺
 * <p>1�������������һ����Ⱥ������Ⱦɫ�壻</p>
 * <p>2������Ⱥ��ѡ���Ϊ�����Ⱦɫ����壻</p>
 * <p>3����˳����������Ⱦɫ������������Ⱦɫ�壬�����ϣ���Ⱦɫ�����������ĸ�Ⱦɫ���𽥽��л����������Ⱦɫ�壻</p>
 * <p>4���������Ⱦɫ����ĳ��������룻</p>
 * 
 * ͨ��ѭ��ִ�����ϵ�2,3,4���裬���տɵõ����Ž⡣
 * @author MikeLyn
 *
 */
public class Main {

    private static final int GRID_SIZE = 8;

    private static final int DEFAULT_POPULATION_SIZE = 8;

    private static final int MUTTATE_P = 2;

    private static int BEST_FITNESS_VALUE = 0;

    private static int TIMES = 100000;

    private static final Random RANDOM = new Random();

    private static List<Chromosome> chromosomes = new LinkedList<Chromosome>();

    private static Map<Chromosome, Integer> fitnessOfChromosome = new LinkedHashMap<Chromosome, Integer>();

    public static void main(String[] args) {
        run();
    }

    /**
     * ����
     */
    public static void run() {
        caculateBestFitnessValue();
        init();

        System.out.println("��ʼ��");
        printChromosomes();
        System.out.println("��ʼ��Ӧ��ֵƽ��ֵ��" + caculateAverageFitness());
        int counter = 0;
        while (!isGetSolution() && counter < TIMES) {
            select();
            swap();
            mutate();
            counter++;
        }
        refreshChromosomes();
        System.out.println("������" + ", counter : " + counter);
        printChromosomes();
        System.out.println("������Ӧ��ֵƽ��ֵ��" + caculateAverageFitness());
    }

    /**
     * ��ʼ��
     */
    private static void init() {
        for (int n = 0; n < DEFAULT_POPULATION_SIZE; n++) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < GRID_SIZE; i++) {
                sb.append(RANDOM.nextInt(GRID_SIZE));
            }
            Chromosome chromosome = new Chromosome(n, sb.toString());
            chromosomes.add(chromosome);
            fitnessOfChromosome.put(chromosome, fintness(chromosome.getCode()));
        }
    }

    /**
     * �ж��Ƿ�������Ž�
     * 
     * @return
     */
    private static boolean isGetSolution() {
        for (Chromosome chromosome : chromosomes) {
            if (fintness(chromosome.getCode()) == 28) {
                return true;
            }
        }
        return false;
    }

    /**
     * ѡ��
     */
    private static void select() {
        double average = caculateAverageFitness();
        List<Chromosome> tempChromosome = new ArrayList<Chromosome>(chromosomes);
        chromosomes.clear();
        for (Entry<Chromosome, Integer> entry : fitnessOfChromosome.entrySet()) {
            if (entry.getValue() >= average) {
                chromosomes.add(entry.getKey());
            }
        }

        Collections.sort(tempChromosome);
        if (chromosomes.size() < DEFAULT_POPULATION_SIZE / 2) {
            int fromIndex = chromosomes.size();
            int toIndex = fromIndex + (DEFAULT_POPULATION_SIZE / 2 - chromosomes.size());
            chromosomes.addAll(tempChromosome.subList(fromIndex, toIndex));
        }

        int n = 0;
        for (Chromosome chromosome : chromosomes) {
            chromosome.setId(n);
            n += 1;
        }

        while (chromosomes.size() < DEFAULT_POPULATION_SIZE && chromosomes.size() > 1) {
            Chromosome e = chromosomes.get(RANDOM.nextInt(chromosomes.size()));
            try {
                Chromosome clone = (Chromosome) e.clone();
                clone.setId(n);
                chromosomes.add(clone);
                n += 1;
            } catch (CloneNotSupportedException e1) {
            }
        }
        if (chromosomes.size() == 1) {
            System.out.println("chromosomes : " + chromosomes.size());
        }

        fitnessOfChromosome.clear();
        refreshChromosomes();
    }

    /**
     * �ӽ�
     */
    private static void swap() {
        for (int i = 0; i < chromosomes.size();) {
            int randomIndex = getRandomIndex();
            Chromosome chromosome = chromosomes.get(i);
            Chromosome next = chromosomes.get(i + 1);
            swapCodeByIndex(randomIndex, chromosome, next);
            i += 2;
        }
    }

    /**
     * ����
     */
    private static void mutate() {
        for (Chromosome chromosome : chromosomes) {
            if (RANDOM.nextInt(GRID_SIZE) > MUTTATE_P) {
                String code = chromosome.getCode();
                int begin = RANDOM.nextInt(code.length());
                chromosome.setCode(code.replace(code.substring(begin, begin + 1), ("" + RANDOM.nextInt(GRID_SIZE))));
            }
        }
    }

    /**
     * ͨ�����������ض��Ļ������
     * 
     * @param randomIndex
     * @param chromosome
     * @param next
     */
    private static void swapCodeByIndex(int randomIndex, Chromosome chromosome, Chromosome next) {
        String code1 = chromosome.getCode();
        String code2 = next.getCode();
        chromosome.setCode(code1.substring(0, randomIndex).concat(code2.substring(randomIndex, code2.length())));
        next.setCode(code2.substring(0, randomIndex).concat(code1.substring(randomIndex, code1.length())));
    }

    /**
     * ������Ӧ�ȵ�ƽ��ֵ
     * 
     * @return
     */
    private static double caculateAverageFitness() {
        int sum = 0;
        for (Integer fitness : fitnessOfChromosome.values()) {
            sum += fitness;
        }
        double average = sum * 1.0 / fitnessOfChromosome.size();
        return average;
    }

    private static int getRandomIndex() {
        return (1 + RANDOM.nextInt(GRID_SIZE - 1));
    }

    private static void refreshChromosomes() {
        Collections.sort(chromosomes);
        fitnessOfChromosome.clear();
        for (Chromosome chromosome : chromosomes) {
            fitnessOfChromosome.put(chromosome, fintness(chromosome.getCode()));
        }
    }

    public static int fintness(String code) {
        int value = BEST_FITNESS_VALUE;
        for (int i = 0; i < GRID_SIZE; i++) {
            int num1 = Integer.parseInt(code.substring(i, i + 1));
            for (int j = i + 1; j < GRID_SIZE; j++) {
                int num2 = Integer.parseInt(code.substring(j, j + 1));
                if (Math.abs(num2 - num1) == (j - i) || (num2 - num1) == 0) {
                    value -= 1;
                }
            }
        }
        return value;
    }

    public static void caculateBestFitnessValue() {
        int f = 0;
        for (int j = 1; j < GRID_SIZE; j++) {
            f += (GRID_SIZE - j);
        }
        BEST_FITNESS_VALUE = f;
    }

    private static void printChromosomes() {
        for (Entry<Chromosome, Integer> entry : fitnessOfChromosome.entrySet()) {
            System.out.println("Code : " + entry.getKey().getCode() + ", Fitness : " + entry.getValue());
        }
    }

}
