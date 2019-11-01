package Ps1;
import java.math.BigInteger;


public class Kangaroo {
	public static void main(String[] args) {
		System.out.println("Start");
		BigInteger g = new BigInteger("3");
		BigInteger x = new BigInteger("3124532153132532131231243143123124");
		BigInteger p = new BigInteger("61845915503831114091865164962647232917206327870669899");
		BigInteger h = power(g, x, p);
		
		//set b very close to x
		BigInteger b = x.add(new BigInteger("900000000"));
		System.out.println(kangaroo(g, h, p, b, new BigInteger("1000"), new BigInteger("100000")));
		
	
	}
	
	//fast exponential function
	public static BigInteger power(BigInteger base, BigInteger power, BigInteger prime) {
		BigInteger ret = new BigInteger("1");
		if(power.equals(new BigInteger("0")))
			return (ret.mod(prime));
		if(power.mod(new BigInteger("2")).equals(new BigInteger("1"))) 
			ret = base;
		return (ret.multiply(power(base, power.divide(new BigInteger("2")), prime).pow(2))).mod(prime);
	}
	
	//sudo random mapping function
	public static BigInteger f(BigInteger x, BigInteger k) {
		return (x.multiply(x).add(new BigInteger("1")).mod(k).add(new BigInteger("1")));
	}
	
	//g^x = h (mod p)
	public static BigInteger kangaroo(BigInteger g, BigInteger h, BigInteger p, BigInteger b, BigInteger k, BigInteger n) {
		BigInteger answer = new BigInteger("-1");
		BigInteger z = new BigInteger("0");
		
		//step 0 initiate tame kangaroo, distance = b
		BigInteger t = power(g, b, p);
		BigInteger d_t = b;
		
		//step 1: tame kangaroo jump n times then set the trap 
		for(BigInteger i = new BigInteger("0"); i.compareTo(n) < 0; i = i.add(new BigInteger("1"))) {
				d_t = (d_t.add((f(t,k))));
				t = (power(g, f(t,k), p).multiply(t)).mod(p);
		}

		
		System.out.println("Trap set");
		while(answer.equals(new BigInteger("-1"))) {
			//step 2: Initialize wild kangaroo 
			BigInteger w = (h.multiply(power(g, z, p)));
			BigInteger d_w = z;
			
			//step 3: Wild kangaroo jump 
			while(d_w.compareTo(d_t) < 0 || d_w.equals(d_t)) {
				if(w.equals(t)) {
					//we trap the wild kangaroo 
					answer = (d_t.subtract(d_w));
					break;
				}
				else {
					d_w = (d_w.add(f(w,k)));
					w = power(g, f(w, k), p).multiply(w).mod(p);
				}
			}
			z = z.add(new BigInteger("1"));
		}
		return answer;
	}
}