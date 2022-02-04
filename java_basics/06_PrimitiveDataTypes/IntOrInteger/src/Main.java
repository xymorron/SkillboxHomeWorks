public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.
        //little letter
        for (int i = 'а'; i <= (int) 'я'; i++){
            System.out.println((char) i + " - " + i);
        }
        //big letters
        for (int i = 'А'; i <= (int) 'Я'; i++){
            System.out.println((char) i + " - " + i);
        }
        //yo-yO
        int yo = 'ё';
        int bigYo = 'Ё';
        System.out.println((char) yo + " - " + yo);
        System.out.println((char) bigYo + " - " + bigYo);
    }
}
