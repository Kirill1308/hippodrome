import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Test
    @Disabled
    @Timeout(22)
    void MethodExecutionTimeWithinLimit() throws Exception {
        Main.main(new String[]{});
    }
}