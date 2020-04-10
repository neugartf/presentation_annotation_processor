import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ExampleBugKaptTest {

    private val example = Example.EXAMPLE_FAIL

    @Test
    fun example_test() {
        assert(true)
    }

    companion object {
        enum class Example {
            EXAMPLE_FAIL
        }
    }
}