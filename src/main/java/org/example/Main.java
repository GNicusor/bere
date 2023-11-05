package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        List<Angajat> angajatList = citire("src/main/resources/angajat.json");
        Scanner scanner = new Scanner(System.in);
        int opt;
        do{
            System.out.println("1. Afișarea listei de angajați folosind referințe la metode");
            System.out.println("2. Afișarea angajaților care au salariul peste 2500 RON.");
            System.out.println("3. Crearea unei liste cu angajații din luna aprilie, a anului trecut, care au funcție de conducere (postul conține unul din cuvintele sef sau director).");
            System.out.println("Alegeti optiunea:");
            opt = scanner.nextInt();
            switch (opt){
                case 1:
                    angajatList.stream().forEach(System.out::println);
                    break;
                case 2:
                    Predicate<Angajat> isAbove2500 = st -> st.getSalariul() > 2500;

                    angajatList.stream()
                            .filter(isAbove2500)
                            .forEach(st -> {
                                System.out.println("Nume: " + st.getNumele() + ", Salariul: " + st.getSalariul());
                            });
                    break;
                case 3:
                    LocalDate lastYearApril = LocalDate.parse("2022-04-30");
                    LocalDate lastMarch = LocalDate.parse("2022-03-31");
                    List<Angajat> angajatiAprilie = angajatList
                            .stream()
                            .filter(angajat -> angajat.getData_angajarii().isBefore(lastYearApril) && angajat.getData_angajarii().isAfter(lastMarch))
                            .filter(angajat -> Objects.equals(angajat.getPostul(), "sef") || Objects.equals(angajat.getPostul(), "director"))
                            .collect(Collectors.toList());
                    angajatiAprilie.forEach(System.out::println);
                    break;
                case 4:
                    angajatList
                            .stream()
                            .filter(angajat -> Objects.equals(angajat.getPostul(), "sef") || Objects.equals(angajat.getPostul(), "director"))
                            .sorted(Comparator.comparingDouble(Angajat::getSalariul).reversed())
                            .forEach(System.out::println);
                    break;
                case 5:
                    List<String> angajatiMAJ = angajatList
                            .stream()
                            .map( nume -> nume.getNumele().toUpperCase())
                            .collect(Collectors.toList());
                    angajatiMAJ.forEach(System.out::println);
                    break;
                case 6:
                    angajatList
                            .stream()
                            .filter(angajat -> angajat.getSalariul() < 3000)
                            .map(Angajat::getSalariul)
                            .forEach(System.out::println);
                    break;
                case 7:
                    Optional<LocalDate> dataMinima = angajatList
                            .stream()
                            .min((a1,a2) -> a1.getData_angajarii().compareTo(a2.getData_angajarii()))
                            .map(Angajat::getData_angajarii);
                    if(dataMinima.isEmpty()){
                        System.out.println("Nu exista un angajat");
                    }
                    else
                        System.out.println(dataMinima);
                    break;
                case 8:
                    DoubleSummaryStatistics summaryStatistics = angajatList
                            .stream()
                            .collect(Collectors.summarizingDouble(Angajat::getSalariul));
                    System.out.println("Average: " + summaryStatistics.getAverage() + "\n" +
                            "Max: " + summaryStatistics.getMax() + "\n" +
                            "Min: " + summaryStatistics.getMin());
                    break;

            }

        }while (opt != 0);
    }

    public static List<Angajat> citire(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<Angajat> angajatList;
        File file = new File(path);
        angajatList = objectMapper.readValue(file, new TypeReference<List<Angajat>>() {});
        return angajatList;
    }

}
