package com.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.res.AssetManager;

public class ReceptEngine {

	private AssetManager am;
	private ArrayList<Product> arrayProduct;
	private ArrayList<Recept> arrayRecept;
	private ArrayList<PR> arrayPR;
	private RTDict dict;

	private static volatile ReceptEngine instance;

	public static ReceptEngine getInstance(AssetManager assmen) {
		ReceptEngine localInstance = instance;
		if (localInstance == null) {
			synchronized (ReceptEngine.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ReceptEngine(assmen);
				}
			}
		}
		return localInstance;
	}

	public ReceptEngine(AssetManager assmen) {
		am = assmen;
		arrayProduct = new ArrayList<Product>();
		arrayRecept = new ArrayList<Recept>();
		arrayPR = new ArrayList<PR>();
		dict = new RTDict(am);
		init();
	}

	private void init() {

		readProducts();
		readPR();
		readRecepts();

	}

	private void readRecepts() {
		InputStream is;
		try {
			is = am.open("Recept");

			String newLine;
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			while ((newLine = br.readLine()) != null) {
				String[] parts = newLine.split("\\|");
				Recept recept = new Recept();
				recept.setIdRecept(parts[0]);
				recept.setNameRecept(parts[1]);
				recept.setTextRecept(parts[2]);
				recept.setTypeRecept(parts[3]);
				arrayRecept.add(recept);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readPR() {
		InputStream is;
		try {
			is = am.open("PR");

			String newLine;
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			while ((newLine = br.readLine()) != null) {
				String[] parts = newLine.split("|");
				PR pr = new PR();
				pr.setIdP(parts[0]);
				pr.setIdR(parts[1]);
				pr.setKolvo(parts[2]);
				pr.setEi(parts[3]);
				pr.setObyaz(parts[4]);
				arrayPR.add(pr);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readProducts() {
		InputStream is;
		try {
			is = am.open("Product");

			String newLine;
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			while ((newLine = br.readLine()) != null) {
				String[] parts = newLine.split("|");
				Product tp = new Product();
				tp.setIdProduct(parts[0]);
				tp.setNameProduct(parts[1]);
				arrayProduct.add(tp);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getTypes() {
		return dict.getTypes();
	}

	public ArrayList<Recept> getReceptsByType(String type) {
		ArrayList<Recept> rl = new ArrayList<Recept>();

		for (Recept recept : arrayRecept) {
			if (recept.getTypeRecept().substring(0, 1).equals(type)) {
				rl.add(recept);
			}
		}

		return rl;
	}

}
