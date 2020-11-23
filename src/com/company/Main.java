package com.company;

import com.company.validator.EmailValidator;
import java.util.Scanner;

public class Main {

    private static EmailValidator emailValidator;

    public static void main(String[] args) {
        ServiceImpl service = new ServiceImpl();
        Scanner in = new Scanner(System.in);

        System.out.println("Выберите нужное действие:  \n" +
                "1.Добавить новую запись; \n" +
                "2.Редактировать запись; \n" +
                "3.Удалить запись; \n" +
                "4.Вывести контакты пользователей; \n" +
                "5.Вывести всю информацию о пользователях." +
                "\n"
        );
        int key, choise;
        do {
            System.out.println("Пожалуйста, введите число от 1 до 5");
            while (!in.hasNextInt()) {
                System.out.println("Это не число!");
                in.next();
            }
            choise = in.nextInt();
        } while (choise <= 0 || choise > 5);
        while(choise!=3) {
            switch (choise) {
                case (1):
                    service.addPerson();
                    break;
                case (2):
                    service.printPersons();
                    System.out.println("Введите номер записи для редактирования. \n");
                    do {
                        System.out.println("Пожалуйста, введите номер существующей записи");
                        while (!in.hasNextInt()) {
                            System.out.println("Это не число!");
                            in.next();
                        }
                        key = in.nextInt();
                    } while (key < 0 || key > service.printPersons().size());
                    service.editPerson(key);
                    break;
                case (3):
                    service.printPersons();
                    System.out.println("Введите номер записи для удаления. \n");
                    do {
                        System.out.println("Пожалуйста, введите номер существующей записи");
                        while (!in.hasNextInt()) {
                            System.out.println("Это не число!");
                            in.next();
                        }
                        key = in.nextInt();
                    } while (key < 0 || key > service.printPersons().size());
                    service.deletePerson(key);
                    break;
                case (4):
                    service.printPersons();
                    break;
                case (5):
                    service.printContacts();
                    break;
                default:
                    System.out.println("Неверный выбор ");
                    break;
            }
            System.out.println("Выберите нужное действие:  \n" +
                    "1.Добавить новую запись; \n" +
                    "2.Редактировать запись; \n" +
                    "3.Удалить запись; \n" +
                    "4.Вывести контакты пользователей; \n" +
                    "5.Вывести всю информацию о пользователях." +
                    "\n"
            );
            do {
                System.out.println("Пожалуйста, введите число от 1 до 5");
                while (!in.hasNextInt()) {
                    System.out.println("Это не число!");
                    in.next();
                }
                choise = in.nextInt();
            } while (choise <= 0 || choise > 5);
        }

    }
}
