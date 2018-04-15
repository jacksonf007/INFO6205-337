package com.metaarchit.mike.eq;

public class Chromosome implements Comparable<Chromosome>, Cloneable {

    private Integer id;

    private String code;

    public Chromosome() {
    }

    public Chromosome(String code) {
        this.code = code;
    }

    public Chromosome(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int compareTo(Chromosome o) {
        if (o != null) {
            int codeCompare = new Integer(Main.fintness(o.code)).compareTo(new Integer(Main.fintness(code)));
            return codeCompare;
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
        Chromosome clone = new Chromosome(code);
        return clone;
    }

}
