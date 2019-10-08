package com.minimal.brick.breaker;

import com.badlogic.gdx.graphics.Color;

public class Couleurs {

	int groupe;
	private Color couleur0, couleur1, couleur2, couleur3, couleur4, couleur5;
	
	public Couleurs(int groupe){
		this.groupe = groupe;
		
		if(groupe == 1){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(239/256f,201/256f,76/256f, 1);
			couleur2 = new Color().set(226/256f,122/256f,63/256f, 1);
			couleur3 = new Color().set(223/256f,73/256f,73/256f, 1);
			couleur4 = new Color().set(51/256f,77/256f,92/256f, 1);
			couleur5 = new Color().set(0.27f,0.695f,0.613f, 1);
		}
		else if(groupe == 2){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(108/256f,163/256f,144/256f, 1);
			couleur2 = new Color().set(204/256f,121/256f,52/256f, 1);
			couleur3 = new Color().set(204/256f,52/256f,71/256f, 1);
			couleur4 = new Color().set(81/256f,62/256f,71/256f, 1);
			couleur5 = new Color().set(204/256f,190/256f,148/256f, 1);
		}
		else if(groupe == 3){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(135/256f,190/256f,158/256f, 1);
			couleur2 = new Color().set(215/256f,214/256f,168/256f, 1);
			couleur3 = new Color().set(241/256f,158/256f,52/256f, 1);
			couleur4 = new Color().set(189/256f,66/256f,24/256f, 1);
			couleur5 = new Color().set(41/256f, 116/256f, 109/256f, 1);
		}
		else if(groupe == 4){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(137/256f,202/256f,217/256f, 1);
			couleur2 = new Color().set(242/256f,179/256f,102/256f, 1);
			couleur3 = new Color().set(242/256f,149/256f,68/256f, 1);
			couleur4 = new Color().set(217/256f,61/256f,61/256f, 1);
			couleur5 = new Color().set(3/256f, 166/256f, 150/256f, 1);
		}
		else if(groupe == 5){ 										//SELECTIONE			
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(179/256f,68/256f,45/256f, 1);
			couleur2 = new Color().set(255/256f,209/256f,159/256f, 1);
			couleur3 = new Color().set(0/256f,112/256f,92/256f, 1);
			couleur4 = new Color().set(176/256f,131/256f,42/256f, 1);
			couleur5 = new Color().set(54/256f, 44/256f, 41/256f, 1);
		}
		else if(groupe == 6){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur2 = new Color().set(252/256f,150/256f,46/256f, 1);
			couleur3 = new Color().set(232/256f,104/256f,7/256f, 1);
			couleur4 = new Color().set(150/256f,41/256f,3/256f, 1);
			couleur1 = new Color().set(255/256f,187/256f,77/256f, 1);
			couleur5 = new Color().set(94/256f, 23/256f, 66/256f, 1);
		}
		else if(groupe == 7){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(52/256f,73/256f,94/256f, 1);
			couleur2 = new Color().set(239/256f,201/256f,76/256f, 1);
			couleur3 = new Color().set(226/256f,122/256f,63/256f, 1);
			couleur4 = new Color().set(223/256f,73/256f,73/256f, 1);
			couleur5 = new Color().set(26/256f, 37/256f, 48/256f, 1);
		}
		else if(groupe == 8){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(242/256f,240/256f,208/256f, 1);
			couleur2 = new Color().set(242/256f,185/256f,136/256f, 1);
			couleur3 = new Color().set(242/256f,159/256f,128/256f, 1);
			couleur4 = new Color().set(1/256f,6/256f,38/256f, 1);
			couleur5 = new Color().set(2/256f, 103/256f, 115/256f, 1);
		}
		else if(groupe == 9){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(3/256f,163/256f,151/256f, 1);
			couleur2 = new Color().set(2/256f,137/256f,141/256f, 1);
			couleur3 = new Color().set(0/256f,51/256f,90/256f, 1);
			couleur4 = new Color().set(243/256f,70/256f,0/256f, 1);
			couleur5 = new Color().set(1/256f, 86/256f, 141/256f, 1);
		}
		else if(groupe == 10){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(243/256f,237/256f,211/256f, 1);
			couleur2 = new Color().set(32/256f,214/256f,199/256f, 1);
			couleur3 = new Color().set(213/256f,79/256f,88/256f, 1);
			couleur4 = new Color().set(17/256f,63/256f,89/256f, 1);
			couleur5 = new Color().set(25/256f, 190/256f, 192/256f, 1);
		}
		else if(groupe == 11){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(235/256f,175/256f,60/256f, 1);
			couleur2 = new Color().set(6/256f,136/256f,148/256f, 1);
			couleur3 = new Color().set(233/256f,102/256f,44/256f, 1);
			couleur4 = new Color().set(0/256f,172/256f,101/256f, 1);
			couleur5 = new Color().set(43/256f, 43/256f, 43/256f, 1);
		}
		else if(groupe == 12){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(135/256f,131/256f,180/256f, 1);
			couleur2 = new Color().set(97/256f,93/256f,129/256f, 1);
			couleur3 = new Color().set(86/256f,76/256f,180/256f, 1);
			couleur4 = new Color().set(49/256f,44/256f,104/256f, 1);
			couleur5 = new Color().set(25/256f, 22/256f, 63/256f, 1);
		}
		else if(groupe == 13){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(255/256f,255/256f,255/256f, 1);
			couleur2 = new Color().set(225/256f,235/256f,245/256f, 1);
			couleur3 = new Color().set(121/256f,189/256f,224/256f, 1);
			couleur4 = new Color().set(255/256f,83/256f,13/256f, 1);
			couleur5 = new Color().set(41/256f, 53/256f, 64/256f, 1);
		}
		else if(groupe == 14){ 											
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(131/256f,191/256f,179/256f, 1);
			couleur2 = new Color().set(99/256f,254/256f,224/256f, 1);
			couleur3 = new Color().set(69/256f,178/256f,157/256f, 1);
			couleur4 = new Color().set(49/256f,127/256f,112/256f, 1);
			couleur5 = new Color().set(175/256f, 254/256f, 239/256f, 1);
		}
		else if(groupe == 15){ 											
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(0/256f,102/256f,128/256f, 1);
			couleur2 = new Color().set(128/256f,62/256f,0/256f, 1);
			couleur3 = new Color().set(0/256f,41/256f,52/256f, 1);
			couleur4 = new Color().set(52/256f,25/256f,0/256f, 1);
			couleur5 = new Color().set(20/256f, 167/256f, 205/256f, 1);
		}
		else if(groupe == 16){ 							//SELECTIONE						
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(182/256f,217/256f,216/256f, 1);
			couleur2 = new Color().set(220/256f,71/256f,24/256f, 1);
			couleur3 = new Color().set(35/256f,59/256f,95/256f, 1);
			couleur4 = new Color().set(126/256f,0/256f,10/256f, 1);
			couleur5 = new Color().set(250/256f, 163/256f, 27/256f, 1);
		}
		else if(groupe == 17){ 													
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(191/256f,114/256f,69/256f, 1);
			couleur2 = new Color().set(115/256f,50/256f,50/256f, 1);
			couleur3 = new Color().set(115/256f,26/256f,50/256f, 1);
			couleur4 = new Color().set(89/256f,17/256f,57/256f, 1);
			couleur5 = new Color().set(106/256f, 166/256f, 132/256f, 1);
		}
		else if(groupe == 18){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(245/256f,204/256f,112/256f, 1);
			couleur2 = new Color().set(242/256f,103/256f,107/256f, 1);
			couleur3 = new Color().set(50/256f,89/256f,130/256f, 1);
			couleur4 = new Color().set(191/256f,78/256f,108/256f, 1);
			couleur5 = new Color().set(89/256f, 146/256f, 199/256f, 1);
		}
		else if(groupe == 19){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(255/256f,206/256f,116/256f, 1);
			couleur2 = new Color().set(151/256f,209/256f,122/256f, 1);
			couleur3 = new Color().set(250/256f,110/256f,105/256f, 1);
			couleur4 = new Color().set(76/256f,141/256f,166/256f, 1);
			couleur5 = new Color().set(91/256f, 96/256f, 140/256f, 1);
		}
	}
	
	public void setGroupe(int i){
		groupe = i;
		
		if(groupe == 1){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(239/256f,201/256f,76/256f, 1);
			couleur2 = new Color().set(226/256f,122/256f,63/256f, 1);
			couleur3 = new Color().set(223/256f,73/256f,73/256f, 1);
			couleur4 = new Color().set(51/256f,77/256f,92/256f, 1);
			couleur5 = new Color().set(0.27f,0.695f,0.613f, 1);
		}
		else if(groupe == 2){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(108/256f,163/256f,144/256f, 1);
			couleur2 = new Color().set(204/256f,121/256f,52/256f, 1);
			couleur3 = new Color().set(204/256f,52/256f,71/256f, 1);
			couleur4 = new Color().set(81/256f,62/256f,71/256f, 1);
			couleur5 = new Color().set(204/256f,190/256f,148/256f, 1);
		}
		else if(groupe == 3){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(135/256f,190/256f,158/256f, 1);
			couleur2 = new Color().set(215/256f,214/256f,168/256f, 1);
			couleur3 = new Color().set(241/256f,158/256f,52/256f, 1);
			couleur4 = new Color().set(189/256f,66/256f,24/256f, 1);
			couleur5 = new Color().set(41/256f, 116/256f, 109/256f, 1);
		}
		else if(groupe == 4){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(137/256f,202/256f,217/256f, 1);
			couleur2 = new Color().set(242/256f,179/256f,102/256f, 1);
			couleur3 = new Color().set(242/256f,149/256f,68/256f, 1);
			couleur4 = new Color().set(217/256f,61/256f,61/256f, 1);
			couleur5 = new Color().set(3/256f, 166/256f, 150/256f, 1);
		}
		else if(groupe == 5){ 										//SELECTIONE			
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(179/256f,68/256f,45/256f, 1);
			couleur2 = new Color().set(255/256f,209/256f,159/256f, 1);
			couleur3 = new Color().set(0/256f,112/256f,92/256f, 1);
			couleur4 = new Color().set(176/256f,131/256f,42/256f, 1);
			couleur5 = new Color().set(54/256f, 44/256f, 41/256f, 1);
		}
		else if(groupe == 6){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur2 = new Color().set(252/256f,150/256f,46/256f, 1);
			couleur3 = new Color().set(232/256f,104/256f,7/256f, 1);
			couleur4 = new Color().set(150/256f,41/256f,3/256f, 1);
			couleur1 = new Color().set(255/256f,187/256f,77/256f, 1);
			couleur5 = new Color().set(94/256f, 23/256f, 66/256f, 1);
		}
		else if(groupe == 7){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(52/256f,73/256f,94/256f, 1);
			couleur2 = new Color().set(239/256f,201/256f,76/256f, 1);
			couleur3 = new Color().set(226/256f,122/256f,63/256f, 1);
			couleur4 = new Color().set(223/256f,73/256f,73/256f, 1);
			couleur5 = new Color().set(26/256f, 37/256f, 48/256f, 1);
		}
		else if(groupe == 8){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(242/256f,240/256f,208/256f, 1);
			couleur2 = new Color().set(242/256f,185/256f,136/256f, 1);
			couleur3 = new Color().set(242/256f,159/256f,128/256f, 1);
			couleur4 = new Color().set(1/256f,6/256f,38/256f, 1);
			couleur5 = new Color().set(2/256f, 103/256f, 115/256f, 1);
		}
		else if(groupe == 9){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(3/256f,163/256f,151/256f, 1);
			couleur2 = new Color().set(2/256f,137/256f,141/256f, 1);
			couleur3 = new Color().set(0/256f,51/256f,90/256f, 1);
			couleur4 = new Color().set(243/256f,70/256f,0/256f, 1);
			couleur5 = new Color().set(1/256f, 86/256f, 141/256f, 1);
		}
		else if(groupe == 10){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(243/256f,237/256f,211/256f, 1);
			couleur2 = new Color().set(32/256f,214/256f,199/256f, 1);
			couleur3 = new Color().set(213/256f,79/256f,88/256f, 1);
			couleur4 = new Color().set(17/256f,63/256f,89/256f, 1);
			couleur5 = new Color().set(25/256f, 190/256f, 192/256f, 1);
		}
		else if(groupe == 11){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(235/256f,175/256f,60/256f, 1);
			couleur2 = new Color().set(6/256f,136/256f,148/256f, 1);
			couleur3 = new Color().set(233/256f,102/256f,44/256f, 1);
			couleur4 = new Color().set(0/256f,172/256f,101/256f, 1);
			couleur5 = new Color().set(43/256f, 43/256f, 43/256f, 1);
		}
		else if(groupe == 12){ 											//SELECTIONE
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(135/256f,131/256f,180/256f, 1);
			couleur2 = new Color().set(97/256f,93/256f,129/256f, 1);
			couleur3 = new Color().set(86/256f,76/256f,180/256f, 1);
			couleur4 = new Color().set(49/256f,44/256f,104/256f, 1);
			couleur5 = new Color().set(25/256f, 22/256f, 63/256f, 1);
		}
		else if(groupe == 13){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(255/256f,255/256f,255/256f, 1);
			couleur2 = new Color().set(225/256f,235/256f,245/256f, 1);
			couleur3 = new Color().set(121/256f,189/256f,224/256f, 1);
			couleur4 = new Color().set(255/256f,83/256f,13/256f, 1);
			couleur5 = new Color().set(41/256f, 53/256f, 64/256f, 1);
		}
		else if(groupe == 14){ 											
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(131/256f,191/256f,179/256f, 1);
			couleur2 = new Color().set(99/256f,254/256f,224/256f, 1);
			couleur3 = new Color().set(69/256f,178/256f,157/256f, 1);
			couleur4 = new Color().set(49/256f,127/256f,112/256f, 1);
			couleur5 = new Color().set(175/256f, 254/256f, 239/256f, 1);
		}
		else if(groupe == 15){ 											
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(0/256f,102/256f,128/256f, 1);
			couleur2 = new Color().set(128/256f,62/256f,0/256f, 1);
			couleur3 = new Color().set(0/256f,41/256f,52/256f, 1);
			couleur4 = new Color().set(52/256f,25/256f,0/256f, 1);
			couleur5 = new Color().set(20/256f, 167/256f, 205/256f, 1);
		}
		else if(groupe == 16){ 							//SELECTIONE						
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(182/256f,217/256f,216/256f, 1);
			couleur2 = new Color().set(220/256f,71/256f,24/256f, 1);
			couleur3 = new Color().set(35/256f,59/256f,95/256f, 1);
			couleur4 = new Color().set(126/256f,0/256f,10/256f, 1);
			couleur5 = new Color().set(250/256f, 163/256f, 27/256f, 1);
		}
		else if(groupe == 17){ 													
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(191/256f,114/256f,69/256f, 1);
			couleur2 = new Color().set(115/256f,50/256f,50/256f, 1);
			couleur3 = new Color().set(115/256f,26/256f,50/256f, 1);
			couleur4 = new Color().set(89/256f,17/256f,57/256f, 1);
			couleur5 = new Color().set(106/256f, 166/256f, 132/256f, 1);
		}
		else if(groupe == 18){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(245/256f,204/256f,112/256f, 1);
			couleur2 = new Color().set(242/256f,103/256f,107/256f, 1);
			couleur3 = new Color().set(50/256f,89/256f,130/256f, 1);
			couleur4 = new Color().set(191/256f,78/256f,108/256f, 1);
			couleur5 = new Color().set(89/256f, 146/256f, 199/256f, 1);
		}
		else if(groupe == 19){
			couleur0 = new Color().set(0,0,0,0);
			couleur1 = new Color().set(255/256f,206/256f,116/256f, 1);
			couleur2 = new Color().set(151/256f,209/256f,122/256f, 1);
			couleur3 = new Color().set(250/256f,110/256f,105/256f, 1);
			couleur4 = new Color().set(76/256f,141/256f,166/256f, 1);
			couleur5 = new Color().set(91/256f, 96/256f, 140/256f, 1);
		}
	}
	
	public Color getCouleur0(){
		return couleur0;
	}
	
	public Color getCouleur1(){
		return couleur1;
	}
	
	public Color getCouleur2(){
		return couleur2;
	}
	
	public Color getCouleur3(){
		return couleur3;
	}
	
	public Color getCouleur4(){
		return couleur4;
	}
	
	public Color getCouleur5(){
		return couleur5;
	}
}
