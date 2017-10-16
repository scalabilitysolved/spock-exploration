import com.scalabilitysolved.Login
import spock.lang.Specification
import spock.lang.Unroll

class LoginValidatorTest extends Specification {

    @Unroll
    def 'role of #role and #failedAuthAttempts failed auth attempts is valid'() {
        when:
        def login = newLogin(role, failedAuthAttempts)

        then:
        login.isValid()

        where:
        role    | failedAuthAttempts
        'guest' | 123
        'admin' | 5
        'user'  | 0
    }

    @Unroll
    def 'role of #role and #failedAuthAttempts failed auth attempts is not valid'() {
        when:
        def login = newLogin(role, failedAuthAttempts)

        then:
        !login.isValid()

        where:
        role        | failedAuthAttempts
        'admin'     | 6
        'admin'     | 100
        'user'      | 1
        'fake user' | 22
    }

    def newLogin(String role, Integer failedAuth) {
        Login.builder().role(role).failedAuthenticationAttempts(failedAuth).build()
    }

}
