package com.minimal.brick.breaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Donnees {

public static Preferences prefs;
	
	public static void Load(){
		prefs = Gdx.app.getPreferences("Donnees");
		
		if (!prefs.contains("Niveau")) {
		    prefs.putInteger("Niveau", 1);
		}
		
		if (!prefs.contains("Groupe")) {
		    prefs.putInteger("Groupe", 1);
		}
		
		if (!prefs.contains("Vitesse")) {
		    prefs.putInteger("Vitesse", 2);
		}
		
		if (!prefs.contains("Langue")) {
		    prefs.putInteger("Langue", 1);
		}
		
		if (!prefs.contains("Son")) {
		    prefs.putBoolean("Son", true);
		}
		
		if (!prefs.contains("Microgravite")) {
		    prefs.putBoolean("Microgravite", false);
		}
		
		if (!prefs.contains("Epileptique")) {
		    prefs.putBoolean("Epileptique", false);
		}
		
		if (!prefs.contains("RateCount")) {
		    prefs.putInteger("RateCount", 2);
		}
		
		if (!prefs.contains("Rate")) {
		    prefs.putBoolean("Rate", false);
		}
		
		if (!prefs.contains("PROMOTE_COSMONAUT")) {
		    prefs.putBoolean("PROMOTE_COSMONAUT", false);
		}
	}
	
	public static void setNiveau(int val) {
	    prefs.putInteger("Niveau", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getNiveau() {
	    return prefs.getInteger("Niveau");
	}
	
	public static void setGroupe(int val) {
	    prefs.putInteger("Groupe", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getGroupe() {
	    return prefs.getInteger("Groupe");
	}
	
	public static void setVitesse(int val) {
	    prefs.putInteger("Vitesse", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getVitesse() {
	    return prefs.getInteger("Vitesse");
	}
	
	public static void setLangue(int val) {
	    prefs.putInteger("Langue", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getLangue() {
	    return prefs.getInteger("Langue");
	}
	
	public static void setSon(boolean val) {
	    prefs.putBoolean("Son", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static boolean getSon() {
	    return prefs.getBoolean("Son");
	}
	
	public static void setMicrogravite(boolean val) {
	    prefs.putBoolean("Microgravite", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static boolean getMicrogravite() {
	    return prefs.getBoolean("Microgravite");
	}
	
	public static void setEpileptique(boolean val) {
	    prefs.putBoolean("Epileptique", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static boolean getEpileptique() {
	    return prefs.getBoolean("Epileptique");
	}
	
	public static void setRateCount(int val) {
	    prefs.putInteger("RateCount", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static int getRateCount() {
	    return prefs.getInteger("RateCount");
	}
	
	public static void setRate(boolean val) {
	    prefs.putBoolean("Rate", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static boolean getRate() {
	    return prefs.getBoolean("Rate");
	}
	
	public static void setPromoteCosmonaut(boolean val) {
	    prefs.putBoolean("PROMOTE_COSMONAUT", val);
	    prefs.flush();							//Sert à sauvegarder
	}

	public static boolean getPromoteCosmonaut() {
	    return prefs.getBoolean("PROMOTE_COSMONAUT");
	}
}
