package com.Model;

import java.io.Serializable;

import android.os.Parcelable;

public class ReceptWithPriority extends Recept implements Serializable{
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