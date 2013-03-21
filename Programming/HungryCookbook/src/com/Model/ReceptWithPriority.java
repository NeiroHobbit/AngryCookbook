package com.Model;

public class ReceptWithPriority extends Recept{
	private double prior = 0;
	
	public ReceptWithPriority(Recept receptPapa, double pr){
		setIdRecept(receptPapa.getIdRecept());
		setNameRecept(receptPapa.getNameRecept());
		setTextRecept(receptPapa.getTextRecept());
		setTypeRecept(receptPapa.getTypeRecept());
		setPrior(pr);
	}

	public double getPrior() {
		return prior;
	}

	public void setPrior(double pror) {
		this.prior = pror;
	}
	
}