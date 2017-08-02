package io.tourniquet.testdata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generator for personally identifiable information
 */
public final class PII {

    private static final List<String> FIRSTNAMES;
    private static final List<String> LASTNAMES;

    static {
        FIRSTNAMES = readDictionary("/firstnames.ch.dic");
        LASTNAMES = readDictionary("/lastnames.ch.dic");
    }

    private static List<String> readDictionary(final String resourceFile) {

        try (InputStream is = PII.class.getResourceAsStream(resourceFile);
             Scanner scanner = new Scanner(is)) {
            final List<String> names = new ArrayList<>();
            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine().trim());
            }
            return Collections.unmodifiableList(names);
        } catch (IOException e) {
        }
        return Collections.emptyList();
    }

    private PII() {

    }

    /**
     * Generates a random firstname
     * @return
     */
    public static String firstname() {

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        String baseName = FIRSTNAMES.get(rand.nextInt(FIRSTNAMES.size()));
        return baseName + rand.nextInt(1000, 10000);

    }

    /**
     * Generates a random lastname
     * @return
     */
    public static String lastname() {

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        String baseName = LASTNAMES.get(rand.nextInt(LASTNAMES.size()));
        return baseName + rand.nextInt(1000, 10000);

    }

    /**
     * Generates a random phone number
     * @return
     */
    public static String phone() {

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new StringBuilder().append("+41 ")
                                  .append(rand.nextInt(10, 100))
                                  .append(' ')
                                  .append(rand.nextInt(100, 1000))
                                  .append(' ')
                                  .append(rand.nextInt(1000, 10000))
                                  .toString();

    }

    /**
     * Generates a random organization id
     * @return
     */
    public static String org() {

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return "org" + rand.nextInt(1000, 10000);

    }

    /**
     * Generates a random email adress.
     * @return
     */
    public static String email() {
        return email(firstname(),lastname());
    }

    /**
     * Generates an email adress using the specifeid organisation using the patter: firstname.lastname@org.ch
     * @param firstname
     *  the first name used for the address
     * @param lastname
     *  the lastname used for the address
     * @param org
     *  the organisation used as domain
     * @return
     */
    public static String email(String firstname, String lastname, String org) {

        return new StringBuilder().append(firstname).append('.').append(lastname).append('@').append(org).append(".ch").toString();
    }

    /**
     * Generates an email address with a random domain
     * @param firstname
     * @param lastname
     * @return
     */
    public static String email(String firstname, String lastname) {

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        StringBuilder buf = new StringBuilder().append(firstname).append('.').append(lastname).append('@');

        for(int i = 0; i<10; i++){
            buf.append((char)rand.nextInt(97, 123));
        }

        return buf.append(".ch").toString();
    }

    /**
     * Generates a loginId
     * @return
     */
    public static String loginId() {

        return "TEST_" + ThreadLocalRandom.current().nextInt(1000000, 10000000) + "_generated";
    }
}
