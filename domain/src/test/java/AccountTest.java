
import com.bin404.exceptions.IllegalOperationException;
import com.bin404.model.Account;
import com.bin404.model.AccountOperation;
import com.bin404.model.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account;

    @BeforeEach
    void initAccount() {
        account = new Account();
        account.setBalance(new BigDecimal(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10, 100, 100_000, 10_000_000, Integer.MAX_VALUE})
    void addDepositOperation(int i) throws IllegalOperationException {
        account.addOperation(OperationType.DEPOSIT, BigDecimal.valueOf(i));
        assertEquals(BigDecimal.valueOf(i), account.getBalance());
    }


    static Stream<BigDecimal> bigAmounts() {
        return Stream.of(
                BigDecimal.valueOf(Integer.MAX_VALUE).add(BigDecimal.valueOf(Integer.MAX_VALUE)),
                BigDecimal.valueOf(Long.MAX_VALUE).add(BigDecimal.valueOf(Long.MAX_VALUE))
        );
    }

    @ParameterizedTest
    @MethodSource("bigAmounts")
    void addDepositOperationMoreThanIntegerMax(BigDecimal bd) throws IllegalOperationException {
        account.addOperation(OperationType.DEPOSIT, bd);
        assertEquals(bd, account.getBalance());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -299, Integer.MAX_VALUE + 1})
    void addDepositIllegal(int i) {
        assertThrows(IllegalOperationException.class, () -> account.addOperation(OperationType.DEPOSIT, BigDecimal.valueOf(i)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, Integer.MAX_VALUE})
    void addWithdrawal(int i) throws IllegalOperationException {
        account.setBalance(BigDecimal.valueOf(i));
        account.addOperation(OperationType.WITHDRAWAL, BigDecimal.valueOf(i));
        assertEquals(BigDecimal.ZERO, account.getBalance());
        // assertThrows(IllegalOperationException.class, () -> account.addOperation(OperationType.WITHDRAWAL, BigDecimal.valueOf(i)));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -299, Integer.MAX_VALUE + 1})
    void addDWithdrawalIllegalSign(int i) {
        account.setBalance(BigDecimal.valueOf(i).abs());
        assertThrows(IllegalOperationException.class, () -> account.addOperation(OperationType.WITHDRAWAL, BigDecimal.valueOf(i)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 299, Integer.MAX_VALUE})
    void addDWithdrawalBiggerThanBalance(int i) {
        account.setBalance(BigDecimal.valueOf(i - 1));
        assertThrows(IllegalOperationException.class, () -> account.addOperation(OperationType.WITHDRAWAL, BigDecimal.valueOf(i)));
    }

    @Test
    void getHistoryCheckSort() throws IllegalOperationException, InterruptedException {
        account.addOperation(OperationType.DEPOSIT, BigDecimal.valueOf(6));
        Thread.sleep(1);
        account.addOperation(OperationType.WITHDRAWAL, BigDecimal.valueOf(3));
        Thread.sleep(1);
        account.addOperation(OperationType.DEPOSIT, BigDecimal.valueOf(3));
        List<LocalDateTime> dates = account.getHistory().getAccountOperations().stream().map(AccountOperation::getDate).collect(Collectors.toList());
        assertTrue(dates.get(0).isAfter(dates.get(1)));
        assertTrue(dates.get(1).isAfter(dates.get(2)));
    }
}