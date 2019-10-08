package com.minimal.brick.breaker;

public class Langue {

	public String commencer, options, noter,
					langage, vitesse, sons, plusDApp,
					lent, normal, rapide,
					activé, désactivé,
					groupe, niveau,
					choisirGroupe,
					reprendre, recommencer, menu, quitter, 
					niveauComplete, suivant, rejouer, fini, debloque, perdu, jeuComplete,
					microgravite, micrograviteDebloque, epileptique, epileptiqueDebloque, optionBloquee, microGraviteBloquee,
					textNotation, oui, plusTard, jamais,
					jouer, nouveauJeu;
	private int langue;
	
	public Langue(){
	}
	
	public void setLangue(int i){
		langue = i;
		
		switch(langue){
		case 1:								//Anglais
			commencer = "Start";
			options = "Options";
			noter = "Rate";
			langage = "Language";
			vitesse = "Speed";
			sons = "Sound";
			plusDApp = "More Apps";
			lent = "Slow";
			normal = "Normal";
			rapide = "Fast";
			activé = "On";
			désactivé = "Off";
			groupe = "Group";
			niveau = "Level";
			choisirGroupe = "Choose a group";
			reprendre = "Resume";
			recommencer = "Restart";
			menu = "Menu";
			quitter =  "Quit";
			niveauComplete = "Level Cleared";
			suivant = "Next";
			rejouer = "Replay";
			fini = "Finished";
			debloque = "Unlocked";
			perdu = "You lost !";
			jeuComplete = "Game Cleared !";
			microgravite = "Microgravity";
			micrograviteDebloque = "Microgravity\nMode Unlocked !";
			epileptique = "Epileptic";
			epileptiqueDebloque = "Epileptic\nMode Unlocked !";
			optionBloquee = "Finish the game\nto unlock this mode";
			microGraviteBloquee = "Finish the group 3\nto unlock this mode";
			textNotation = "Do you enjoy\nMINIMAL BRICK BREAKER?\nDo you want to rate it ?";
			oui = "YES";
			plusTard = "LATER";
			jamais = "NEVER";
			jouer = "Play";
			nouveauJeu = "Try our new game !";
			break;
		case 2:								//Français
			commencer = "Jouer";
			options = "Options";
			noter = "Évaluer";
			langage = "Langage";
			vitesse = "Vitesse";
			sons = "Son";
			plusDApp = "Plus\nd'applis";
			lent = "Lent";
			normal = "Normal";
			rapide = "Rapide";
			activé = "Activé";
			désactivé = "Désactivé";
			groupe = "Groupe";
			niveau = "Niveau";
			choisirGroupe = "Choisissez un groupe";
			reprendre = "Reprendre";
			recommencer = "Recommencer";
			menu = "Menu";
			quitter =  "Quitter";
			niveauComplete = "Niveau Terminé";
			suivant = "Suivant";
			rejouer = "Rejouer";
			fini = "Fini";
			debloque = "Débloqué";
			perdu = "Vous avez perdu !";
			jeuComplete = "Jeu Terminé !";
			microgravite = "Microgravité";
			micrograviteDebloque = "Mode Microgravité\nDébloqué !";
			epileptique = "Epileptique";
			epileptiqueDebloque = "Mode Epileptique\nDébloqué !";
			optionBloquee = "Finissez le jeu pour\ndébloquer ce mode";
			microGraviteBloquee = "Finissez le groupe 3 pour\ndébloquer ce mode";
			textNotation = "Vous aimez\nMINIMAL BRICK BREAKER?\nVoulez vous le noter ?";
			oui = "OUI";
			plusTard = "PLUS\nTARD";
			jamais = "JAMAIS";
			jouer = "Jouer";
			nouveauJeu = "Essayez notre nouveau jeu !";
			break;
		case 3:								//Espagnol
			commencer = "Jugar";
			options = "Opciones";
			noter = "Evaluar";
			langage = "Idioma";
			vitesse = "Velocidad";
			sons = "Sonido";
			plusDApp = "Más Apps";
			lent = "Lento";
			normal = "Normal";
			rapide = "Rápido";
			activé = "Encendido";
			désactivé = "Apagado";
			groupe = "Grupo";
			niveau = "Nivel";
			choisirGroupe = "Seleccione un grupo";
			reprendre = "Continuar";
			recommencer = "Reiniciar";
			menu = "Menú";
			quitter =  "Salir";
			niveauComplete = "Nivel Completado";
			suivant = "Siguiente";
			rejouer = "Repetir";
			fini = "Terminado";
			debloque = "Desbloqueado";
			perdu = "Has perdido !";
			jeuComplete = "Juego Completado !";
			microgravite = "Micro\ngravedad";
			micrograviteDebloque = "Microgravedad Modo\ndesbloqueado !";
			epileptique = "Epiléptico";
			epileptiqueDebloque = "Epiléptico Modo\ndesbloqueado !";
			optionBloquee = "Completa el juego para\ndesbloquear esta modo";
			microGraviteBloquee = "Completa el grupo 3 para\ndesbloquear esta modo";
			textNotation = "¿Te gusta\nMINIMAL BRICK BREAKER?\n¿Le evalúe?";
			oui = "SÍ";
			plusTard = "MÁS\nTARDE";
			jamais = "NUNCA";
			jouer = "Jugar";
			nouveauJeu = "Pruebe nuestro nuevo juego !";
			break;		
		}
	}
}
