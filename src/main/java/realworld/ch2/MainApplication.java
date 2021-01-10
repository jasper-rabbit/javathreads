package realworld.ch2;

import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer =
                new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser2();
        bankStatementAnalyzer.analyze("input.csv", bankStatementParser);
    }
}
