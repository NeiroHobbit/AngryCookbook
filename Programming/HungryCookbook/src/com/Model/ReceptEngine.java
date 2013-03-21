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
				String[] parts = newLine.split("\\|");
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
				String[] parts = newLine.split("\\|");
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

	public Product getProductByName(String name) {
		for (Product prod : arrayProduct) {
			if (prod.getNameProduct().equals(name)) {
				return prod;
			}
		}
		return null;
	}

	// TODO: �����-����� ������� �����
	public ArrayList<ReceptWithPriority> findRecepts(ArrayList<Product> products) {
		ArrayList<ReceptWithPriority> receptsWPArray = new ArrayList<ReceptWithPriority>();
		for (Recept recept : arrayRecept) {
			double inrgCount = getMainIngridientsCount(recept.getIdRecept());
			double findIngrCount = 0;
			double prio = 0;
			for (Product product : products) {
				for (String productID : getIngridients(recept.getIdRecept())) {
					if (productID.equals(product.getIdProduct())) {
						findIngrCount++;
					}
				}
			}
			prio = findIngrCount / inrgCount * 100;
			ReceptWithPriority rwp = new ReceptWithPriority(recept, prio);
			//rwp.setPrior(findIngrCount / inrgCount * 100);
			receptsWPArray.add(rwp);

		}

		return receptsWPArray;
	}

	private ArrayList<String> getIngridients(String idRecept) {
		ArrayList<String> ingridients = new ArrayList<String>();
		for (PR pr : arrayPR) {
			if (idRecept.equals(pr.getIdR())) {
				ingridients.add(pr.getIdP());
			}
		}
		return ingridients;
	}

	private double getMainIngridientsCount(String IDRecept) {
		int counter = 0;
		for (PR pr : arrayPR) {
			String prgetIdR = pr.getIdR();
			//if (new String(IDRecept).intern() == new String(pr.getIdR()).intern()) {
			if (pr.getIdR().equals(IDRecept)) {
				counter++;
			}
		}

		return counter;
	}

	public String digitToStringRT(int digit) {
		switch (digit) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "C";
		case 3:
			return "D";
		case 4:
			return "E";
		case 5:
			return "F";
		case 6:
			return "G";
		}
		return null;
	}

	public ArrayList<Recept> getRecepts() {
		return arrayRecept;
	}

	public ArrayList<Product> getProducts() {
		return arrayProduct;
	}

}
