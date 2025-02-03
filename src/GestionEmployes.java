import java.util.Arrays;
import java.util.Scanner;

public class GestionEmployes {
    private static Employe[] tableauEmployes = new Employe[50]; // Tableau des employés
    private static int compteur = 0; // Compteur pour suivre le nombre d'employés dans le tableau

    // Méthode pour afficher le menu des options
    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher les employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("8. Quitter");
        System.out.print("Choisissez une option: ");
    }

    // Méthode pour ajouter un employé dans le tableau
    public static void ajouterEmploye(Employe employe) {
        // Vérifier si l'ID de l'employé existe déjà
        for (int i = 0; i < compteur; i++) {
            if (tableauEmployes[i].getId() == employe.getId()) {
                System.out.println("Erreur : L'ID de l'employé existe déjà !");
                return; // Arrête l'ajout de l'employé si l'ID est déjà pris
            }
        }
        // Si le tableau n'est pas plein, ajouter l'employé
        if (compteur < tableauEmployes.length) {
            tableauEmployes[compteur++] = employe;
            System.out.println("Employé ajouté avec succès !");
        } else {
            System.out.println("Le tableau est plein. Impossible d'ajouter un nouvel employé.");
        }
    }

    // Méthode pour modifier un employé existant à partir de son ID
    public static void modifierEmploye(int id, String nouveauNom, String nouveauPoste, double nouveauSalaire) {
        for (int i = 0; i < compteur; i++) {
            if (tableauEmployes[i].getId() == id) {
                tableauEmployes[i].setNom(nouveauNom);
                tableauEmployes[i].setPoste(nouveauPoste);
                tableauEmployes[i].setSalaire(nouveauSalaire);
                System.out.println("Employé modifié avec succès !");
                return;
            }
        }
        System.out.println("Employé non trouvé !");
    }

    // Méthode pour supprimer un employé du tableau à partir de son ID
    public static void supprimerEmploye(int id) {
        for (int i = 0; i < compteur; i++) {
            if (tableauEmployes[i].getId() == id) {
                for (int j = i; j < compteur - 1; j++) {
                    tableauEmployes[j] = tableauEmployes[j + 1];
                }
                tableauEmployes[--compteur] = null;
                System.out.println("Employé supprimé avec succès !");
                return;
            }
        }
        System.out.println("Employé non trouvé !");
    }

    // Méthode pour afficher tous les employés
    public static void afficherEmployes() {
        if (compteur == 0) {
            System.out.println("Aucun employé à afficher.");
            return;
        }
        for (int i = 0; i < compteur; i++) {
            System.out.println(tableauEmployes[i].toString());
        }
    }

    // Méthode pour rechercher un employé par nom ou poste
    public static void rechercherEmploye(String critere) {
        boolean trouve = false;
        for (int i = 0; i < compteur; i++) {
            if (tableauEmployes[i].getNom().contains(critere) || tableauEmployes[i].getPoste().contains(critere)) {
                System.out.println(tableauEmployes[i].toString());
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun employé trouvé avec le critère \"" + critere + "\".");
        }
    }

    // Méthode pour calculer la masse salariale totale des employés
    public static void calculerMasseSalariale() {
        double masseSalariale = 0;
        for (int i = 0; i < compteur; i++) {
            masseSalariale += tableauEmployes[i].getSalaire();
        }
        System.out.println("La masse salariale totale est de: " + masseSalariale);
    }

    // Méthode pour trier les employés par salaire, croissant ou décroissant
    public static void trierEmployesParSalaire(boolean ordreCroissant) {
        Arrays.sort(tableauEmployes, 0, compteur, (e1, e2) -> ordreCroissant ? Employe.compareParSalaire(e1, e2) : -Employe.compareParSalaire(e1, e2));
        afficherEmployes();
    }

    // Méthode principale pour exécuter l'application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            printMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour à la ligne

            switch (choix) {
                case 1:
                    System.out.print("Entrez l'ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez le nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez le poste: ");
                    String poste = scanner.nextLine();
                    System.out.print("Entrez le salaire: ");
                    double salaire = scanner.nextDouble();
                    ajouterEmploye(new Employe(id, nom, poste, salaire));
                    break;
                case 2:
                    System.out.print("Entrez l'ID de l'employé à modifier: ");
                    int idModif = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez le nouveau nom: ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Entrez le nouveau poste: ");
                    String nouveauPoste = scanner.nextLine();
                    System.out.print("Entrez le nouveau salaire: ");
                    double nouveauSalaire = scanner.nextDouble();
                    modifierEmploye(idModif, nouveauNom, nouveauPoste, nouveauSalaire);
                    break;
                case 3:
                    System.out.print("Entrez l'ID de l'employé à supprimer: ");
                    int idSupp = scanner.nextInt();
                    supprimerEmploye(idSupp);
                    break;
                case 4:
                    afficherEmployes();
                    break;
                case 5:
                    System.out.print("Entrez le critère de recherche (nom ou poste): ");
                    String critere = scanner.nextLine();
                    rechercherEmploye(critere);
                    break;
                case 6:
                    calculerMasseSalariale();
                    break;
                case 7:
                    System.out.print("Tri croissant ? (true/false): ");
                    boolean ordreCroissant = scanner.nextBoolean();
                    trierEmployesParSalaire(ordreCroissant);
                    break;
                case 8:
                    continuer = false;
                    break;
                default:
                    System.out.println("Option invalide !");
                    break;
            }
        }

        scanner.close();
    }
}