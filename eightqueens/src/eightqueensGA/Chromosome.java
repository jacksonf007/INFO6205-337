package eightqueensGA;

public class Chromosome implements Comparable<Chromosome>, Cloneable {
	
	//auto override the hashCode and equal;
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((solution == null) ? 0 : solution.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chromosome other = (Chromosome) obj;
		if (solution == null) {
			if (other.solution != null)
				return false;
		} else if (!solution.equals(other.solution))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	private int id;
    private String solution;
    public Chromosome() {    }

    public Chromosome(String solution) {
        this.solution = solution;
        }
    public Chromosome(int id, String solution) {
        this.id = id;
        this.solution = solution;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSolution() {
        return solution;
    }
    public void setSolution(String solution) {
        this.solution = solution;
    }
    
    @Override
    public int compareTo(Chromosome o) {
        if (o != null) {
            int solutionCompare = new Integer(QueenGa.evolutionfint(o.solution)).compareTo(new Integer(QueenGa.evolutionfint(solution)));
            return solutionCompare;
        }
        return 0;
    }

    public Chromosome cloneChromosome() {
        try {
            return (Chromosome) clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Chromosome clone = new Chromosome(solution);
        return clone;
    }
    
}

