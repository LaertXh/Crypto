package Ps1;
import java.math.BigInteger;
import java.util.Random; 

public class ModTest {
public static void main(String[] args) {
	BigInteger one = new BigInteger("1");
	BigInteger two = new BigInteger("2");
	BigInteger three = new BigInteger("3");
	BigInteger prime = new BigInteger("61845915503831114091865164962647232917206327870669899");
	BigInteger range = (prime.subtract(one)).divide(two);
	BigInteger r1 = new BigInteger("23476518809109841512388888255597834570025548669239101");
	BigInteger r2 = new BigInteger("5815015754374921280955691220093049847105334794690583");
	BigInteger test = new BigInteger("1");
	boolean state = true;
	System.out.println("Start");
	while(state) {
	if(test.mod(new BigInteger("1000")).equals(new BigInteger("0"))) System.out.println(test);
	BigInteger power = getRandomBigInteger(range);
	BigInteger rem = power1(three, power, prime);
		if(rem.equals(r1)) {
			System.out.println("Found solution r1, power is " + power);
			state = false;
		}
		if(rem.equals(r2)) {
			System.out.println("Found solution r2, power is " + power);
			state = false;
		}
		test = test.add(one);
	}
}

	public static BigInteger power1(BigInteger base, BigInteger power, BigInteger prime) {
		BigInteger ret = new BigInteger("1");
		if(power.equals(new BigInteger("0")))
			return (ret.mod(prime));
		if(power.mod(new BigInteger("2")).equals(new BigInteger("1"))) 
			ret = base;
		return (ret.multiply(power1(base, power.divide(new BigInteger("2")), prime).pow(2))).mod(prime);
	}

public static BigInteger getRandomBigInteger(BigInteger n) {
        Random rand = new Random();//initialize new seed
        BigInteger upperLimit = n;//set the limit to n
        BigInteger result;
        do {
            result = new BigInteger(upperLimit.bitLength(), rand); 
        }
        while(result.compareTo(upperLimit) >= 0); 
        return result;
    }

}