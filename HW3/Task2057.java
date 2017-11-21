

/*

Ваша задача — реализовать структуру данных, которая умеет хранить мультимножество натуральных чисел, т.е. в этой структуре могут одновременно храниться несколько равных элементов.

Эта структура должна поддерживать две операции:
добавить элемент x в множество
удалить минимальный элемент в множестве и вернуть его значение (если минимальных элементов несколько, то удаляется только один из них)



Входные данные
Первая строка входных данных содержит число n (1 ≤ n ≤ 106) — количество операций. Далее в n строках даны описания операций над множеством. Описание представляет собой число — тип запроса (1 или 2) и число x (1 ≤ x ≤ 109) если это запрос первого типа.


Выходные данные
Для каждого запроса второго типа выведите результат на отдельной строке.
 */


import java.util.Scanner;
import java.util.StringTokenizer;

public class Task2057
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        String currStr;
        MultiSet<Integer> m = new MultiSet<>();
        StringTokenizer sTok;
        for (int i = 0; i<count; i++) {
            currStr = scanner.nextLine();
            sTok = new StringTokenizer(currStr, " ");
            if (sTok.countTokens() == 2 && Integer.parseInt(sTok.nextToken()) == 1 )
            {
                m.add(Integer.parseInt(sTok.nextToken()));
            }
            else
            {
                if (Integer.parseInt(sTok.nextToken()) == 2)
                    System.out.println(m.removeMinAndGetValue());

            }

        }

    }


}
