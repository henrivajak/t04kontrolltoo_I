import java.io.*;


public class Kontrolltoo {
    private static final String FILENAME = "kaalutudkeskmine.txt";
    private String asukoht;

    private Kontrolltoo(String asukoht) {
        this.asukoht = asukoht;
    }

    private void loeArvutaKirjuta() throws IOException {
        BufferedReader loe = new BufferedReader(new FileReader(asukoht));
        try  {
            loe.readLine(); // skippime rea
            String rida = loe.readLine();
            double eapSumma = 0;
            int eapJaHindeKorrutis = 0;
            int puuduvaid = 0;
            int hinne;
            while (rida != null) {
                String[] m = rida.split(",");
                try {
                    if (m[1].toUpperCase().equals("A")) {
                        hinne = 5;
                    } else if (m[1].toUpperCase().equals("B")) {
                        hinne = 4;
                    } else if (m[1].toUpperCase().equals("C")) {
                        hinne = 3;
                    } else if (m[1].toUpperCase().equals("D")) {
                        hinne = 2;
                    } else if (m[1].toUpperCase().equals("E")) {
                        hinne = 1;
                    } else {
                        hinne = 0;
                    }
                    eapSumma += Integer.parseInt(m[2]);
                    eapJaHindeKorrutis += Integer.parseInt(m[2]) * hinne;
                } catch (Exception ex) {
                    puuduvaid++;
                }
                rida = loe.readLine();
            }
            loe.close();
            if (puuduvaid > 0) {
                System.err.print("Leitud on 1 vigane rida " + puuduvaid);
            }
            kirjuta(eapJaHindeKorrutis / eapSumma);

        } catch (Exception ex) {
            System.err.print("error");
        }
    }

    private void kirjuta(double kaalutudKeskmine) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
        try
        {
            bw.write("kaalutud keskmine on: " + kaalutudKeskmine);

        } catch (Exception veaandmed) {
            System.err.print("Tekkis probleem");
        }
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Kontrolltoo kontrolltoo = new Kontrolltoo("hinded.txt");
        kontrolltoo.loeArvutaKirjuta();
    }
}