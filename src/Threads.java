import java.io.*;
import java.util.Scanner;

class TextToFile implements Runnable {
    private String text;
    private String filename; // имя файла, в который будем записывать
    private String threadName; // имя потока
    private Thread thr; // ссылка на текущий поток

    public TextToFile(String text, String filename, String threadName) {

        this.text = text; //текст, который нужно записать в файл;
        this.filename = filename; //имя файла, в который записывается массив
        this.threadName = threadName; //threadName - имя потока

        thr = new Thread(this, "MyThread");
    }

    public void start() {
        thr.start();
    }

    public void run() {

        System.out.println("Begin thread: " + threadName);

        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            PrintStream printStream = new PrintStream(fileOut);

            printStream.print(text);

            printStream.close();
            fileOut.close();

        } catch (IOException e) {
            // Выводим сообщение об ошибке
            System.out.println("Error: " + e.getMessage());
        }

        // Сообщаем о завершении потока
        System.out.println("End thread: " + threadName);
    }
}

public class Threads {

    public static void main(String[] args) throws IOException {
        // Записываем массивы в разные файлы в разных потоках
        // 1. Создаем текстовый массив и заполняем его с клавиатуры
        Scanner scanner = new Scanner(System.in);
        String[] text = new String[3]; // записываемый массив

        int i = 0;
        for (String s : text) {
            System.out.println("Введите " + (i + 1) + "-ый " + "текст:");
            text[i] = scanner.nextLine();
            i++;
        }

        // 2. Создаем три потока
        TextToFile t1 = new TextToFile(text[0], "Text_1.txt", "thr1");
        TextToFile t2 = new TextToFile(text[1], "Text_2.txt", "thr2");
        TextToFile t3 = new TextToFile(text[2], "Text_3.txt", "thr3");

        // 3. Запускаем потоки на выполнение
        t1.start();
        t2.start();
        t3.start();
    }
}
