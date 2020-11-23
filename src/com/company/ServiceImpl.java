package com.company;

import com.company.validator.EmailValidator;
import com.company.validator.PhoneValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceImpl implements Service {

    @Override
    public void addPerson() {
        EmailValidator emailValidator = new EmailValidator();
        PhoneValidator phoneValidator = new PhoneValidator();
        Scanner in = new Scanner(System.in);
        int keyForPersons, keyForData, i;
        List<Person> newPerson = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./person.txt"))) {
            while (true) {
                newPerson.add((Person) ois.readObject());
            }
        } catch (Exception ex) {

        }


        System.out.println("Введите количество записей для добавления: ");
        do {
            System.out.println("Пожалуйста, введите число от 1 до 5");
            while (!in.hasNextInt()) {
                System.out.println("Это не число!");
                in.next();
            }
            keyForPersons = in.nextInt();
        } while (keyForPersons <= 0 || keyForPersons > 5);
        for (i = 0; i < keyForPersons; i++) {
            System.out.println("Введите имя: ");
            String firstname = in.next();
            System.out.println("Введите фамилию: ");
            String lastname = in.next();
            System.out.println("Введите почту: ");
            String email;
            do {
                System.out.println("Пожалуйста, введите почту в правильном формате");
                email = in.next();
            } while (!emailValidator.validate(email));
            System.out.println("Введите количество телефонов в записи: ");
            do {
                System.out.println("Пожалуйста, введите число от 1 до 3");
                while (!in.hasNextInt()) {
                    System.out.println("Это не число!");
                    in.next();
                }
                keyForData = in.nextInt();
            } while (keyForData <= 0 || keyForData > 3);

            System.out.println("Введите телефон: ");
            String phone;
            ArrayList<String> phones = new ArrayList<>();
            for (i = 0; i < keyForData; i++) {
                do {
                    System.out.println("Пожалуйста, введите телефон в правильном формате");
                    phone = in.next();
                } while (!phoneValidator.validate(phone));
                phones.add(phone);
            }


            System.out.println("Выберете роль: \n" +
                    "1.Администратор; \n" +
                    "2.Менеджер; \n" +
                    "3.Клиент. \n"
            );
            Role role = Role.CLIENT;
            do {
                System.out.println("Пожалуйста, введите число от 1 до 3");
                while (!in.hasNextInt()) {
                    System.out.println("Это не число!");
                    in.next();
                }
                keyForData = in.nextInt();
            } while (keyForData <= 0 || keyForData > 3);
            switch (keyForData) {
                case (1):
                    role = Role.ADMIN;
                    break;
                case (2):
                    role = Role.MANAGER;
                    break;
                case (3):
                    role = Role.CLIENT;
                    break;
                default:
                    System.out.println("Неверный выбор ");
                    break;
            }

            Person person = new Person();
            person.setFirstname(firstname);
            person.setLastname(lastname);
            person.setEmail(email);
            person.setPhone(phones);
            person.setRole(role);
            newPerson.add(person);
        }

        try {
            FileOutputStream f = new FileOutputStream(new File("./person.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            for (i = 0; i < newPerson.size(); i++)
                o.writeObject(newPerson.get(i));

            o.flush();
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    @Override
    public List<Person> printPersons() {

        List<Person> newPerson = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./person.txt"))) {
            while (true) {
                newPerson.add((Person) ois.readObject());
            }
        } catch (Exception ex) {

        }
        for (int i = 0; i < newPerson.size(); i++)
            System.out.println("Номер: " + i + " Имя: " + newPerson.get(i).getFirstname() +
                    " Фамилия: " + newPerson.get(i).getLastname() +
                    " Почта: " + newPerson.get(i).getEmail() +
                    " Телефон: " + newPerson.get(i).getPhone() +
                    " Роль: " + newPerson.get(i).getRole());

        return newPerson;
    }

    @Override
    public void printContacts() {

        List<Person> newPerson = new ArrayList<>();


        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./person.txt"))) {
            while (true) {
                newPerson.add((Person) ois.readObject());
            }
        } catch (Exception ex) {

        }
        for (int i = 0; i < newPerson.size(); i++)
            System.out.println(" Имя: " + newPerson.get(i).getFirstname() +
                    " Фамилия: " + newPerson.get(i).getLastname() +
                    " Почта: " + newPerson.get(i).getEmail() +
                    " Телефон: " + newPerson.get(i).getPhone());

    }

    @Override
    public void deletePerson(int key) {

        List<Person> newPerson = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./person.txt"))) {
            while (true) {
                newPerson.add((Person) ois.readObject());
            }
        } catch (Exception ex) {

        }
        newPerson.remove(key);

        try {
            FileOutputStream f = new FileOutputStream(new File("./person.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            for (int i = 0; i < newPerson.size(); i++) {
                o.writeObject(newPerson.get(i));
            }

            o.flush();
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }

    @Override
    public void editPerson(int key) {
        Scanner in = new Scanner(System.in);
        List<Person> newPerson = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./person.txt"))) {
            while (true) {
                newPerson.add((Person) ois.readObject());
            }
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
        System.out.println("Выберите параметр записи, который хотите изменить:  \n" +
                "1.Имя; \n" +
                "2.Фамилия; \n" +
                "3.Почта; \n" +
                "4.Телефон; \n" +
                "5.Роль; \n" +
                "6.Отмена.\n");
        int choise;
        String data;
        int num;
        do {
            System.out.println("Пожалуйста, введите число от 1 до 5");
            while (!in.hasNextInt()) {
                System.out.println("Это не число!");
                in.next();
            }
            choise = in.nextInt();
        } while (choise <= 0 || choise > 5);

        switch (choise) {
            case (1):
                System.out.println("Введите новое имя: \n");
                data = in.next();
                newPerson.get(key).setFirstname(data);
                break;
            case (2):
                System.out.println("Введите новую фамилию: \n");
                data = in.next();
                newPerson.get(key).setLastname(data);
                break;
            case (3):
                System.out.println("Введите новую почту: \n");
                data = in.next();
                newPerson.get(key).setEmail(data);
                break;
            case (4):
                System.out.println("Введите новый телефон: \n");
                System.out.println("Введите количество телефонов в записи: ");
                num = in.nextInt();
                System.out.println("Введите телефон: ");
                String phone;
                ArrayList<String> phones = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    phone = in.next();
                    phones.add(phone);
                }
                newPerson.get(key).setPhone(phones);
                break;
            case (5):
                System.out.println("Выберете роль: \n" +
                        "1.Администратор; \n" +
                        "2.Менеджер; \n" +
                        "3.Клиент. \n"
                );
               Role role = Role.CLIENT;
                do {
                    System.out.println("Пожалуйста, введите число от 1 до 3");
                    while (!in.hasNextInt()) {
                        System.out.println("Это не число!");
                        in.next();
                    }
                    key = in.nextInt();
                } while (key <= 0 || key > 3);
                switch (key) {
                    case (1):
                        role = Role.ADMIN;
                        break;
                    case (2):
                        role = Role.MANAGER;
                        break;
                    case (3):
                        role = Role.CLIENT;
                        break;
                    default:
                        System.out.println("Неверный выбор ");
                        break;
                }
                newPerson.get(key).setRole(role);
                break;
        }

        try {
            FileOutputStream f = new FileOutputStream(new File("./person.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            for (int i = 0; i < newPerson.size(); i++)
                o.writeObject(newPerson.get(i));

            o.flush();
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }

}
