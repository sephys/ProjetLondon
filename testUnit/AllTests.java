package testUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCarte.class, TestEtalage.class, TestJoueur.class })
public class AllTests {

}
