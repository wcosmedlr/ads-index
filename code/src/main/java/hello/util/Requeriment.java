package hello.util;

import hello.entity.Ad;

public abstract class Requeriment{
    private int evaluation;

    public Requeriment(){ }

    public int analyze(Ad ad) {
        setEvaluation(process(ad));
        return getEvaluation();
    }

    protected abstract int process(Ad ad);

    protected int getEvaluation() {
        return evaluation;
    }

    protected void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

}
