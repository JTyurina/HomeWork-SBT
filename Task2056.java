import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.*;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.max;

    /*

    Дан текст, ваша задача — найти слово, которое встречается в тексте наибольшее количество раз. Текст состоит только из латинских букв, пробелов, переводов строк.

    Слово — это последовательность подряд идущих латинских букв, регистр не имеет значения.

    Если искомых слов несколько, ваша задача — найти все такие слова.
     */



public class Task2056 {


    public static void main(String[] args) {


        try {
            final int[] maxFrequency = {0};
            FileInputStream fstream = new FileInputStream("C:\\Users\\TurUli\\Desktop\\zdf\\Task2056.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            StringTokenizer sTok;
            ArrayList<String> arrWords = new ArrayList();
            ArrayList<String> arrStrings = new ArrayList();
            java.lang.Object[] str = br.lines().toArray();
            for (int i = 0; i < str.length; i++) {
                arrStrings.add((String) str[i]);
                sTok = new StringTokenizer(str[i].toString(), " ");
                while (sTok.hasMoreTokens()) {
                    String strToken = sTok.nextToken();
                    arrWords.add(strToken.toLowerCase());
                }
            }


            Collections.sort(arrWords, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {

                    int freq1 = Collections.frequency(arrWords, o1);
                    int freq2 = Collections.frequency(arrWords, o2);
                    if (Integer.max(freq2,freq1)> maxFrequency[0])
                        maxFrequency[0] = Integer.max(freq2,freq1);
                    return Integer.compare(freq2,freq1);
                }
            });


            arrWords.removeIf(x -> (Collections.frequency(arrWords, x)<maxFrequency[0]));


            Collections.sort(arrWords, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            LinkedHashSet<String> uniqueWords = new LinkedHashSet<>(arrWords);



            for (String s : uniqueWords)
                System.out.println(s);


        } catch (Exception e) {
        }


    }
}
