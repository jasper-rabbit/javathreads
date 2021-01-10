package realworld.ch2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 현재 메인 클래스는 여러 책임을 모두 포함하므로 이를 개별로 분리해야한다.
 * 1. 입력 읽기
 * 2. 주어진 형식의 입력 파싱
 * 3. 결과 처리
 * 4. 결과 요약 리포트
 */
public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        printTotalSum();
        printJanuaryTotal();
    }

    public static void printTotalSum() throws IOException {
        final Path path = Paths.get(RESOURCES + "input.csv");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        for (final String line : lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }
        System.out.println("The total for all transaction is " + total);
    }

    public static void printJanuaryTotal() throws IOException {
        final Path path = Paths.get(RESOURCES + "input.csv");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (final String line : lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if (date.getMonth() == Month.JANUARY) {
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }
        System.out.println("The total for all transaction in January is " + total);
    }
}
