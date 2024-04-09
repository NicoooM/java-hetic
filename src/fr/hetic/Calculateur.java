public class Calculateur {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculateur <nombre1> <nombre2> <operateur>");
            return;
        }

        try {
            double nombre1 = Double.parseDouble(args[0]);
            double nombre2 = Double.parseDouble(args[1]);
            String operateur = args[2];
            double resultat;

            switch (operateur) {
                case "+":
                    resultat = nombre1 + nombre2;
                    break;
                case "-":
                    resultat = nombre1 - nombre2;
                    break;
                case "*":
                    resultat = nombre1 * nombre2;
                    break;
                default:
                    System.out.println("Opérateur non reconnu. Les opérateurs valides sont +, -, *.");
                    return;
            }

            // Afficher le résultat
            System.out.println("Résultat: " + resultat);
        } catch (NumberFormatException e) {
            System.out.println("Les deux premiers arguments doivent être des nombres.");
        }
    }
}
