/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package generateData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateData {

    public static final String FILE_OUTPUT_PATH = "E:\\customer.tsv";

    public static void main(String args[]) {
        // Output
        FileOutputStream os = null;
        PrintWriter pw = null;
        try {
            // Output file
            os = new FileOutputStream(FILE_OUTPUT_PATH);
            pw = new PrintWriter(os);
            Long startTime = System.currentTimeMillis();

            // setheader
            StringBuffer strHeader = new StringBuffer();
            strHeader.append("cust_id");
            strHeader.append("\t");
            strHeader.append("cust_name");
            strHeader.append("\t");
            strHeader.append("birth_date");
            strHeader.append("\t");
            strHeader.append("address");
            strHeader.append("\n");
            pw.println(strHeader);

            // 100000000
            int count = 0;
            for (int i = 1; i < 100000000; i++) {
                int custId = i;
                String custName = "name " + i;
                String birthDate = birthDate();
                String address = "add " + i;

                StringBuffer strRow = new StringBuffer();
                strRow.append(custId);
                strRow.append("\t");
                strRow.append(custName);
                strRow.append("\t");
                strRow.append(birthDate);
                strRow.append("\t");
                strRow.append(address);
                strRow.append("\n");
                pw.println(strRow);
                count++;
                if (count % 10000 == 0) {
                    System.out.println("tao duoc " + count + " ban ghi!" + Math.round((System.currentTimeMillis() - startTime) / 1000));
                }
            }
            System.out.println("tao duoc " + count + " ban ghi!" + " tg " + Math.round((System.currentTimeMillis() - startTime) / 1000));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            safeClose(pw);
            safeClose(os);
        }
    }

    public static String birthDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(1940, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedString = randomBirthDate.format(formatter);

        return formattedString;
    }

    protected static void safeClose(FileInputStream is) {
        try {
            is.close();
        } catch (Exception ex) {
            is = null;
        }
    }

    protected static void safeClose(PrintWriter is) {
        try {
            is.close();
        } catch (Exception ex) {
            is = null;
        }
    }

    protected static void safeClose(FileOutputStream is) {
        try {
            is.close();
        } catch (Exception ex) {
            is = null;
        }
    }

    protected static void safeClose(BufferedInputStream is) {
        try {
            is.close();
        } catch (Exception ex) {
            is = null;
        }
    }
}
