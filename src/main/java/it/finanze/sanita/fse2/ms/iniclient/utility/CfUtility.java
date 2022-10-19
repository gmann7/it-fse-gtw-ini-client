/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.ms.iniclient.utility;

public class CfUtility {
    private CfUtility() {}
    public static final int CF_NON_CORRETTO = 0;
    public static final int CF_OK_16 = 1;
    public static final int CF_CHECK_DIGIT_16 = 2;
    public static final int CF_OK_11 = 1;
    public static final int CF_CHECK_DIGIT_11 = 2;
    public static final int CF_ENI_OK = 3;
    public static final int CF_STP_OK = 4;

    public static int validaCF(String cfIn) {
        // gli esiti per cf16 sono gli stessi di cf11
        int esito = CF_NON_CORRETTO;
        if (!StringUtility.isNullOrEmpty(cfIn)) {
            String cf = cfIn.toUpperCase();

            // Controllo lunghezza e formato
            if (cf.length() == 17) {
                cf = cf.substring(0, 16);
            }
            if (cf.length() == 16) {
                esito = validaCF16(cf);
            } else if (cf.length() == 11) {
                esito = validaCF11(cf);
            }
        }

        return esito;
    }

    public static boolean isValidCf(final String fiscalCode) {
        return validaCF(fiscalCode) == CF_OK_16 || validaCF(fiscalCode) == CF_OK_11
            || validaCF(fiscalCode) == CF_ENI_OK || validaCF(fiscalCode) == CF_STP_OK;
    }

    private static final int validaCF11(String cfIn) {
        if (cfIn == null)
            return CF_NON_CORRETTO;
        int i, c, s;
        if (cfIn.length() == 0)
            return CF_NON_CORRETTO;

        if (cfIn.length() != 11)
            return CF_NON_CORRETTO;
        for (i = 0; i < 11; i++) {
            if (cfIn.charAt(i) < '0' || cfIn.charAt(i) > '9')
                return CF_NON_CORRETTO;
        }
        s = 0;
        for (i = 0; i <= 9; i += 2)
            s += cfIn.charAt(i) - '0';
        for (i = 1; i <= 9; i += 2) {
            c = 2 * (cfIn.charAt(i) - '0');
            if (c > 9)
                c = c - 9;
            s += c;
        }

        if ((10 - s % 10) % 10 != cfIn.charAt(10) - '0')
            return CF_CHECK_DIGIT_11;
        else
            return CF_OK_11;
    }

    private static final int validaCF16(String cfIn) {
        if (cfIn == null)
            return CF_NON_CORRETTO;
        int i = 0;
        int[] cifre = new int[16];
        int somma = 0;
        char check = ' ';
        String cf = cfIn.toUpperCase();

        if (cf.length() != 16)
            return CF_NON_CORRETTO;

        // Se e' ENI/STP, deve avere 13 caratteri numerici e non si fa il controllo
        // check digit
        String cfEniStp = cf.substring(0, 3);
        if (cfEniStp.equals("ENI")) {
            cfEniStp = cf.substring(3, 16);
            try { // se tutti i caratteri dopo il 3° sono numerici, si tratta di un CF ENI/STP -
                  // non si fanno altri controlli
                Long.parseLong(cfEniStp);
                return CF_ENI_OK;
            } catch (NumberFormatException nfe) {
                // Continua: non e' un CF eni/stp
            }
        }

        if (cfEniStp.equals("STP")) {
            cfEniStp = cf.substring(3, 16);
            try { // se tutti i caratteri dopo il 3° sono numerici, si tratta di un CF ENI/STP -
                  // non si fanno altri controlli
                Long.parseLong(cfEniStp);
                return CF_STP_OK;
            } catch (NumberFormatException nfe) {
                // Continua: non e' un CF eni/stp
            }
        }

        // primi sei caratteri alfabetici
        for (i = 0; i < 6; i++) {
            if (!Character.isLetter(cf.charAt(i)))
                return CF_NON_CORRETTO;
        }

        // CHECK DIGIT
        // Conversione caratteri (tranne il sedicesimo)
        for (i = 0; i < 15; i = i + 2) {
            // converti dispari
            cifre[i] = convertiDispari(cf.charAt(i));
            // converti pari
            cifre[i + 1] = convertiPari(cf.charAt(i + 1));
        }
        // somma di tutte le cifre
        for (i = 0; i < 15; i++) {
            if (cifre[i] >= 0)
                somma = somma + cifre[i];
            else
                return CF_NON_CORRETTO;
        }
        // resto della divisione per 26
        somma = somma % 26;

        // conversione resto
        check = convertiResto(somma);

        if (check == cf.charAt(15))
            return CF_OK_16;
        else
            return CF_CHECK_DIGIT_16;
    }

    public static final boolean checkDigit(String cf) {
        return true;
    }

    private static final int convertiDispari(char c) {
        switch (c) {
            case '0':
                return 1;
            case '1':
                return 0;
            case '2':
                return 5;
            case '3':
                return 7;
            case '4':
                return 9;
            case '5':
                return 13;
            case '6':
                return 15;
            case '7':
                return 17;
            case '8':
                return 19;
            case '9':
                return 21;
            case 'A':
                return 1;
            case 'B':
                return 0;
            case 'C':
                return 5;
            case 'D':
                return 7;
            case 'E':
                return 9;
            case 'F':
                return 13;
            case 'G':
                return 15;
            case 'H':
                return 17;
            case 'I':
                return 19;
            case 'J':
                return 21;
            case 'K':
                return 2;
            case 'L':
                return 4;
            case 'M':
                return 18;
            case 'N':
                return 20;
            case 'O':
                return 11;
            case 'P':
                return 3;
            case 'Q':
                return 6;
            case 'R':
                return 8;
            case 'S':
                return 12;
            case 'T':
                return 14;
            case 'U':
                return 16;
            case 'V':
                return 10;
            case 'W':
                return 22;
            case 'X':
                return 25;
            case 'Y':
                return 24;
            case 'Z':
                return 23;
            default:
                return -1;
        }
    }

    private static final int convertiPari(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            case 'K':
                return 10;
            case 'L':
                return 11;
            case 'M':
                return 12;
            case 'N':
                return 13;
            case 'O':
                return 14;
            case 'P':
                return 15;
            case 'Q':
                return 16;
            case 'R':
                return 17;
            case 'S':
                return 18;
            case 'T':
                return 19;
            case 'U':
                return 20;
            case 'V':
                return 21;
            case 'W':
                return 22;
            case 'X':
                return 23;
            case 'Y':
                return 24;
            case 'Z':
                return 25;
            default:
                return -1;
        }
    }

    private static final char convertiResto(int i) {
        char[] table = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        return table[i];
    }
}